package com.example.jetpack_multiplenavigation.photoCompressionWork

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.roundToInt

class PhotoCompressionWork(
    private val applicationContext: Context,
    private val params: WorkerParameters
) : CoroutineWorker(applicationContext, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val uriString = params.inputData.getString(URI_KEY)
            val compressionThresholdInBytes = params.inputData.getLong(
                COMPRESSION_THRESHOLD,
                0L
            )
            val uri = Uri.parse(uriString)
            val bytes = applicationContext.contentResolver.openInputStream(uri)?.use { input ->
                input.readBytes()
            } ?: return@withContext Result.failure()

            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            var outputBytes: ByteArray
            var quality = 100

            do {
                val outputStream = ByteArrayOutputStream()
                outputStream.use { output ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, output)
                    outputBytes = output.toByteArray()
                    quality -= (quality * 0.1).roundToInt()
                }
            } while (outputStream.size() > compressionThresholdInBytes && quality > 5)

            val file = File(applicationContext.cacheDir, "${params.id}.jpg")
            file.writeBytes(outputBytes)

            Result.success(
                workDataOf(
                    RESULT_PATH to file.absolutePath
                )
            )
        }
    }

    companion object {
        const val URI_KEY = "Uri_Key"
        const val COMPRESSION_THRESHOLD = "Compression_Threshold"
        const val RESULT_PATH = "Result_Path"
    }
}
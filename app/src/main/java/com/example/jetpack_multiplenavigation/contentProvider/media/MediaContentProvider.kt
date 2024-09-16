package com.example.jetpack_multiplenavigation.contentProvider.media

import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.mutableStateListOf
import com.example.jetpack_multiplenavigation.contentProvider.media.data.Image
import com.example.jetpack_multiplenavigation.contentProvider.media.presentation.MediaContentViewModel

fun mediaContentProvider(
    context: Context,
    mediaContentViewModel: MediaContentViewModel
) {
    val baseUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
    } else {
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    }
    val projections = arrayOf(
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DISPLAY_NAME
    )
    context.contentResolver.query(
        baseUri,
        projections,
        null,
        null,
        null
    ) ?.use { cursor ->
        val idColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID)
        val nameColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
        val images = mutableStateListOf<Image>()
        cursor.moveToFirst()
        while (cursor.moveToNext()) {
            val id = cursor.getLong(idColumnIndex)
            val name = cursor.getString(nameColumnIndex)
            val uri = ContentUris.withAppendedId(
                baseUri,
                id
            )
            images.add(Image(id, name, uri))
        }
        mediaContentViewModel.updateImages(images)
    }
}
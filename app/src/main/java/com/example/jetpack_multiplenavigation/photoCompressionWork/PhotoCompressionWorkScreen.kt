package com.example.jetpack_multiplenavigation.photoCompressionWork

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PhotoCompressionWorkScreen(
    uriString: String,
    navController: NavHostController
) {
    val context = LocalContext.current
    val workManager = WorkManager.getInstance(context.applicationContext)
    val photoCompressionViewModel = viewModel<PhotoCompressionViewModel>()

    val uri = Uri.parse(uriString)
    photoCompressionViewModel.updateUncompressedUri(uri)
    //val multipleRequest = PeriodicWorkRequestBuilder<>()
    val oneTimeRequest = OneTimeWorkRequestBuilder<PhotoCompressionWork>()
        .setInputData(
            workDataOf(
                PhotoCompressionWork.URI_KEY to uri.toString(),
                PhotoCompressionWork.COMPRESSION_THRESHOLD to 1024 * 25L
            )
        )
        .setConstraints(
            Constraints(
                requiresStorageNotLow = true
            )
        )
        .setInitialDelay(
            duration = Duration.ofSeconds(3)
        )
        .build()
    photoCompressionViewModel.updateWorkId(oneTimeRequest.id)
    workManager.enqueue(oneTimeRequest)

    val workInfo = photoCompressionViewModel.workId?.let { id ->
        workManager.getWorkInfoByIdLiveData(id).observeAsState().value
    }
    LaunchedEffect(key1 = workInfo?.outputData) {
        if (workInfo?.outputData != null) {
            val filePath = workInfo.outputData.getString(PhotoCompressionWork.RESULT_PATH)
            filePath?.let {
                val bitmap = BitmapFactory.decodeFile(it)
                photoCompressionViewModel.updateCompressedBitmap(bitmap)
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        photoCompressionViewModel.uncompressedUri?.let {
            Text(
                text = "Uncompressed Image",
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            AsyncImage(
                model = ImageRequest.Builder(context.applicationContext)
                    .data(it)
                    .build(),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Log.e("LLL", "${photoCompressionViewModel.compressedBitmap == null}")
        photoCompressionViewModel.compressedBitmap?.let {
            Text(
                text = "Compressed Image",
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            navController.navigateUp()
        }) {
            Text(
                text = "Home",
                fontFamily = FontFamily.Serif
            )
        }
    }
}
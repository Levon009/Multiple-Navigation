package com.example.jetpack_multiplenavigation.pickSaveImage.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.MainActivity
import com.example.jetpack_multiplenavigation.pickSaveImage.data.BitmapHelper
import com.example.jetpack_multiplenavigation.ui.theme.JetPack_MultipleNavigationTheme

class BitmapActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPack_MultipleNavigationTheme {
                val imageBitmap = BitmapHelper.getInstance().getBitmap()?.asImageBitmap()
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    imageBitmap?.let {
                        Image(
                            bitmap = it,
                            contentDescription = "Bitmap image"
                        )
                    }
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@BitmapActivity, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }
        })
    }
}
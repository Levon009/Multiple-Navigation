package com.example.jetpack_multiplenavigation.magnifierSection

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.ui.theme.LightGreen1
import com.example.jetpack_multiplenavigation.ui.theme.LightGreen2
import com.example.jetpack_multiplenavigation.ui.theme.LightGreen3

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagnifierSection(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow back",
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = LightGreen2
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightGreen3)
                .padding(paddingValues)
        ) {
            var offset: Offset by remember {
                mutableStateOf(Offset.Zero)
            }
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(true) {
                        detectDragGestures { change, dragAmount ->
                            offset = change.position
                        }
                    }
                    .magnifier(
                        sourceCenter = {
                            offset
                        },
                        magnifierCenter = {
                            offset - Offset(0f, 200f)
                        },
                        size = DpSize(100.dp, 100.dp),
                        cornerRadius = 100.dp
                    )
            ) {
                val width = constraints.maxWidth
                val height = constraints.maxHeight
                val mediumColoredPath = mediumColoredPath(width, height)
                val lightColoredPath = lightColoredPath(width, height)
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawPath(
                        path = mediumColoredPath,
                        color = LightGreen1
                    )
                    drawPath(
                        path = lightColoredPath,
                        color = LightGreen2
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.image1),
                    contentDescription = "Magnifier image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
                val painter = painterResource(id = R.drawable.image1)
                val contentDescription = "Hello World"
                val title = "Hello world in new 2024 year."
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .fillMaxSize(0.5f)
                        .padding(start = 16.dp, top = 35.dp, end = 16.dp)
                ) {
                    ImageCard(
                        painter = painter,
                        contentDescription = contentDescription,
                        title = title
                    )
                }
            }
        }
    }
}
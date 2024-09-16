package com.example.jetpack_multiplenavigation.notifications

import android.Manifest
import android.app.NotificationManager
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(navController: NavHostController) {
    val context = LocalContext.current
    val launcher = NotificationPermission(context = context)
    val channelId = NotificationsChannelId.CHANNEL_ID
    val contentTitle = context.resources.getString(R.string.test_notification_title)
    val contentText = context.resources.getString(R.string.test_notification_text)
    val contentLargeText = context.resources.getString(R.string.test_notification_big_text)
    val smallIcon: Int = R.drawable.ic_launcher_foreground
    val bigPicture = BitmapFactory.decodeResource(context.resources, R.drawable.header)

    LaunchedEffect(Unit) {
        notificationChannel(
            context = context,
            channelId = NotificationsChannelId.CHANNEL_ID,
            channelName = context.resources.getString(R.string.test_notification_channel_name),
            importance = NotificationManager.IMPORTANCE_HIGH,
            descriptionText = context.resources.getString(R.string.test_notification_description)
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notifications",
                        style = MaterialTheme.typography.headlineMedium,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Button(onClick = {
                launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }) {
                Text(text = "Get permission")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                simpleNotification(
                    context = context,
                    channelId = channelId,
                    notificationId = NotificationsChannelId.SIMPLE_NOT_ID,
                    contentTitle = contentTitle,
                    contentText = contentText,
                    smallIcon = smallIcon,
                    autoCancel = true
                )
            }) {
                Text(text = "Simple notification")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                notificationWithLargeText(
                    context = context,
                    channelId = channelId,
                    notificationId = NotificationsChannelId.LARGE_TEXT_NOT_ID,
                    contentTitle = contentTitle,
                    contentText = contentLargeText,
                    smallIcon = smallIcon,
                    autoCancel = true
                )
            }) {
                Text(text = "Large Text notification")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                notificationLargeTextBigIcon(
                    context = context,
                    channelId = channelId,
                    notificationId = NotificationsChannelId.LARGE_TEXT_BIG_ICON_NOT_ID,
                    contentTitle = contentTitle,
                    contentText = contentLargeText,
                    smallIcon = smallIcon,
                    largeIcon = bigPicture,
                    autoCancel = true
                )
            }) {
                Text(text = "LargeText BigIcon notification")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                notificationWithBigPicture(
                    context = context,
                    channelId = channelId,
                    notificationId = NotificationsChannelId.BIG_PICTURE_NOT_ID,
                    contentTitle = contentTitle,
                    contentText = contentText,
                    smallIcon = smallIcon,
                    bigPicture = bigPicture,
                    autoCancel = true
                )
            }) {
                Text(text = "Big picture notification")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
package com.example.jetpack_multiplenavigation.dialogs.alertDialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CallAlertDialog(
    title: String,
    text: String,
    icon: ImageVector,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = {
                onConfirmation()
            }) {
                Text(
                    text = "Confirm",
                    fontFamily = FontFamily.Serif
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissRequest()
            }) {
                Text(
                    text = "Dismiss",
                    fontFamily = FontFamily.Serif
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif,
            )
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = "Info"
            )
        }
    )
}
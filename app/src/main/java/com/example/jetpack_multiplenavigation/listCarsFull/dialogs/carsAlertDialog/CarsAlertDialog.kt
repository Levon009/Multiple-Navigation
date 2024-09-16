package com.example.jetpack_multiplenavigation.listCarsFull.dialogs.carsAlertDialog

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
fun CarsAlertDialog(
    title: String,
    text: String,
    icon: ImageVector,
    onConfirmation: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
          onDismissRequest()
        },
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
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        },
        text = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center
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
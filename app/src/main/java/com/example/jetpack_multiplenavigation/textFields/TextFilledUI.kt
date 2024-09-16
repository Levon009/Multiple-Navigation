package com.example.jetpack_multiplenavigation.textFields

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.core.text.isDigitsOnly

@Composable
fun TextFieldUI() {
    val context = LocalContext.current
    val localKeyboardController = LocalSoftwareKeyboardController.current
    var filledText by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }
    TextField(
        value = filledText,
        onValueChange = {
            filledText = it
        },
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Right
        ),
        placeholder = {
            Text(text = "Weight")
        },
        label = {
            Text(text = "Enter your weight")
        },
        prefix = { Text(text = "$") },
        suffix = { Text(text = "kg") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.MailOutline,
                contentDescription = "Weight",
                tint = Color.DarkGray
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Show Password",
                tint = Color.DarkGray,
                modifier = Modifier.clickable {
                    showPassword = !showPassword
                }
            )
        },
        supportingText = { Text(text = "*requires") },
        isError = !filledText.isDigitsOnly(),
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Go
        ),
        keyboardActions = KeyboardActions(
            onGo = {
                Toast.makeText(context, "GO GO GO", Toast.LENGTH_SHORT).show()
                localKeyboardController?.hide()
            },
            onDone = {
                localKeyboardController?.hide()
            }
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Yellow
        )
    )
}
package com.example.jetpack_multiplenavigation.textFields

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.jetpack_multiplenavigation.R

@Composable
fun OutlinedTextFieldUI() {
    val context = LocalContext.current
    val fontFamily = FontFamily(Font(R.font.tack_one, FontWeight.Thin))
    val localKeyboardController = LocalSoftwareKeyboardController.current
    var outlinedFilledText by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = outlinedFilledText,
        onValueChange = {
            outlinedFilledText = it
        },
        shape = RoundedCornerShape(12.dp),
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Right,
            fontFamily = fontFamily
        ),
        label = {
            Text(
                text = "Enter your weight",
                fontFamily = fontFamily
            )
        },
        placeholder = {
            Text(
                text = "Weight",
                fontFamily = fontFamily
            )
        },
        prefix = { Text(text = "$") },
        suffix = { Text(text = "kg") },
        supportingText = {
            Text(text = "*requires")
        },
        isError = !outlinedFilledText.isDigitsOnly(),
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
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
                contentDescription = "Show passord",
                tint = Color.DarkGray,
                modifier = Modifier.clickable {
                    showPassword = !showPassword
                }
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                Toast.makeText(context, "Hello world", Toast.LENGTH_SHORT).show()
                localKeyboardController?.hide()
            },
            onDone = {
                localKeyboardController?.hide()
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.LightGray,
            unfocusedContainerColor = Color.Cyan,
            cursorColor = Color.Red
        ),
    )
}
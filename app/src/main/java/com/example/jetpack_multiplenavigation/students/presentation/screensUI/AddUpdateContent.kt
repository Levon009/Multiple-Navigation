package com.example.jetpack_multiplenavigation.students.presentation.screensUI

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldContent(
    text: MutableState<String>,
    labelText: String,
    testTag: String = "",
    onValueChange: () -> Unit
) {
    val localKeyboard = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it
            onValueChange()
        },
        singleLine = true,
        label = {
            Text(
                text = labelText,
                color = Color.DarkGray,
                fontFamily = FontFamily.Serif
            )
        },
        isError = text.value.isBlank(),
        supportingText = {
            Text(
                text = "*requires",
                fontFamily = FontFamily.Serif
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Unspecified,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                localKeyboard?.hide()
            },
        ),
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Color.Red,
            focusedContainerColor = Color.LightGray,
            focusedBorderColor = Color.DarkGray
        ),
        modifier = Modifier.testTag(testTag)
    )
}

@Composable
fun ButtonContent(
    text: String,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF8FAEE),
            contentColor = Color.Black
        )
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )
    }
}
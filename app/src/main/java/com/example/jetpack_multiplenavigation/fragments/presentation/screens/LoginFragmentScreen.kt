package com.example.jetpack_multiplenavigation.fragments.presentation.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_multiplenavigation.MainActivity
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.fragments.core.util.BundleKeys
import com.example.jetpack_multiplenavigation.fragments.presentation.EntityFragment
import com.example.jetpack_multiplenavigation.fragments.presentation.FragmentsActivity

@Composable
fun LoginScreen() {
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember {
        FocusRequester()
    }
    val emailVal = remember {
        mutableStateOf("")
    }
    val passwordVal = remember {
        mutableStateOf("")
    }
    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Sign In",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = emailVal.value,
            onValueChange = {
                emailVal.value = it
            },
            label = {
                Text(
                    text = "Email",
                    fontFamily = FontFamily.Serif
                )
            },
            placeholder = {
                Text(
                    text = "Email Address",
                    fontFamily = FontFamily.Serif
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "Email Icon",
                    tint = Color.DarkGray
                )
            },
            supportingText = {
                Text(
                    text = "*requires",
                    fontFamily = FontFamily.Serif
                )
            },
            isError = emailVal.value.isBlank(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusRequester.requestFocus()
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .focusRequester(focusRequester)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = passwordVal.value,
            onValueChange = {
                passwordVal.value = it
            },
            singleLine = true,
            label = {
                Text(
                    text = "Password",
                    fontFamily = FontFamily.Serif
                )
            },
            placeholder = {
                Text(
                    text = "Input password",
                    fontFamily = FontFamily.Serif
                )
            },
            supportingText = {
                Text(
                    text = "*requires",
                    fontFamily = FontFamily.Serif
                )
            },
            isError = passwordVal.value.isEmpty(),
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        painter = painterResource(R.drawable.password_eye),
                        contentDescription = "Show password",
                        tint = if (passwordVisibility.value) {
                            Color.DarkGray
                        } else {
                            Color.Gray
                        }
                    )
                }
            },
            visualTransformation = if (passwordVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboard?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .focusRequester(focusRequester = focusRequester)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Button(
            onClick = {
                when {
                    emailVal.value.isEmpty() -> {
                        Toast.makeText(context, "Please enter email!", Toast.LENGTH_SHORT).show()
                    }
                    passwordVal.value.isEmpty() -> {
                        Toast.makeText(context, "Please enter password!", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(context, "Logged Successfully!", Toast.LENGTH_SHORT).show()
                        val args = emailVal.value.plus(" ").plus(passwordVal.value)
                        startEntityFragment(
                            args = args,
                            context = context
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)
        ) {
            Text(
                text = "Submit",
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                Intent(context, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(this)
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)
        ) {
            Text(
                text = "Home",
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
            )
        }
    }
}

private fun startEntityFragment(
    args: String,
    context: Context
) {
    val bundle = Bundle()
    bundle.putString(BundleKeys.FRAGMENT_ARGUMENTS, args)
    val entityFragment = EntityFragment()
    entityFragment.arguments = bundle
    (context as FragmentsActivity).supportFragmentManager.beginTransaction()
        .replace(R.id.main_container, entityFragment)
        .addToBackStack(null)
        .commit()
}
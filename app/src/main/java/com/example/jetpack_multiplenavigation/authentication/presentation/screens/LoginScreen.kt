package com.example.jetpack_multiplenavigation.authentication.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.Constants
import com.example.jetpack_multiplenavigation.authentication.common.UIEvents
import com.example.jetpack_multiplenavigation.authentication.data.remote.request.AuthRequest
import com.example.jetpack_multiplenavigation.authentication.presentation.AuthState
import com.example.jetpack_multiplenavigation.authentication.presentation.AuthViewModel
import com.example.jetpack_multiplenavigation.navigation.Routes
import com.example.jetpack_multiplenavigation.ui.theme.PurpleBg
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    val viewModel = hiltViewModel<AuthViewModel>()
    val authState = viewModel.authState.collectAsStateWithLifecycle().value
    var passwordVisible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = viewModel.errorToastChannel) {
        viewModel.errorToastChannel.collectLatest { show ->
            if (show) {
                viewModel.onEvent(UIEvents.ShowLoading)
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
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
        if (authState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            if (authState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.height(64.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Hello Again!",
                fontSize = 26.sp,
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Welcome Back you've been missed",
                fontSize = 19.sp,
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = authState.email,
                onValueChange = {
                    viewModel.onEvent(UIEvents.SetEmail(it))
                },
                placeholder = {
                    Text(
                        text = "Enter email",
                        fontFamily = FontFamily.Serif
                    )
                },
                supportingText = {
                    Text(
                        text = "*requires",
                        fontFamily = FontFamily.Serif
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboard?.hide()
                    }
                ),
                isError = authState.email.isBlank() || !Constants.emailRegex.matches(authState.email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = authState.password,
                onValueChange = {
                    viewModel.onEvent(UIEvents.SetPassword(it))
                },
                label = {
                    Text(
                        text = "Password",
                        fontFamily = FontFamily.Serif
                    )
                },
                supportingText = {
                    Text(
                        text = "*requires",
                        fontFamily = FontFamily.Serif
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(
                            imageVector = if (passwordVisible) {
                                Icons.Filled.Visibility
                            } else {
                                Icons.Filled.VisibilityOff
                            },
                            contentDescription = if (passwordVisible) "Hide" else "Show"
                        )
                    }
                },
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboard?.hide()
                    }
                ),
                isError = authState.password.isBlank() || !Constants.emailRegex.matches(authState.password),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = {
                    // Forgot Password Destination
                    Toast.makeText(context, "Forgot password destination", Toast.LENGTH_LONG).show()
                }) {
                    Text(
                        text = "Forgot Password",
                        color = Color.Black,
                        fontFamily = FontFamily.Serif
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        keyboard?.hide()
                        if (authState.email.isBlank() && !Constants.emailRegex.matches(authState.email)) {
                            Toast.makeText(context, "Please enter valid email address!!!", Toast.LENGTH_SHORT).show()
                        } else if(authState.password.isBlank() && !Constants.emailRegex.matches(authState.password)) {
                            Toast.makeText(context, "Please enter valid password!!!", Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.onEvent(UIEvents.LoginUser(AuthRequest(
                                email = authState.email,
                                password = authState.password
                            )))
                        }
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PurpleBg,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Log in",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
                TextButton(
                    onClick = {
                        navController.navigate(Routes.RegisterUser)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = buildAnnotatedString {
                            val blackStyle = SpanStyle(
                                color = Color.Black
                            )
                            val boldStyle = SpanStyle(
                                color = PurpleBg,
                                fontWeight = FontWeight.Bold
                            )
                            pushStyle(blackStyle)
                            append("Don't have an accout?")
                            pop()
                            append(" ")
                            pushStyle(boldStyle)
                            append("Sign up")
                            pop()
                        },
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
    }
}
package com.example.jetpack_multiplenavigation.authentication.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
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
fun RegisterScreen(navController: NavHostController) {
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
                text = "Welcome!",
                color = Color.Black,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Register an account with us!!",
                color = Color.Black,
                fontSize = 19.sp,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = authState.firstName,
                onValueChange = {
                    viewModel.onEvent(UIEvents.SetFirstName(it))
                },
                placeholder = {
                    Text(
                        text = "First name",
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
                isError = authState.firstName.isBlank(),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = authState.lastName,
                onValueChange = {
                   viewModel.onEvent(UIEvents.SetLastName(it))
                },
                placeholder = {
                    Text(
                        text = "Last name",
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
                isError = authState.lastName.isBlank(),
                modifier = Modifier.fillMaxWidth()
            )

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
                    keyboardType = KeyboardType.Email
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboard?.hide()
                    }
                ),
                isError = authState.email.isBlank() || !Constants.emailRegex.matches(authState.email),
                modifier = Modifier.fillMaxWidth()
            )

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
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboard?.hide()
                    }
                ),
                isError = authState.email.isBlank() || !Constants.emailRegex.matches(authState.email),
                trailingIcon = {
                    val imageVector = if (passwordVisible) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(
                            imageVector,
                            description
                        )
                    }
                },
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    keyboard?.hide()
                    if (authState.email.isBlank() && !Constants.emailRegex.matches(authState.email)) {
                        Toast.makeText(context, "Please enter valid email address!!!", Toast.LENGTH_SHORT).show()
                    } else if(authState.password.isBlank() && !Constants.emailRegex.matches(authState.password)) {
                        Toast.makeText(context, "Please enter valid password!!!", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.onEvent(UIEvents.RegisterUser(
                            AuthRequest(
                                email = authState.email,
                                password = authState.password
                            )
                        ))
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PurpleBg,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Register",
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            TextButton(
                onClick = {
                    keyboard?.hide()
                    navController.navigate(Routes.LoginUser)
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
                        append("Already have an account?")
                        pop()
                        append(" ")
                        pushStyle(boldStyle)
                        append("Login")
                        pop()
                    },
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
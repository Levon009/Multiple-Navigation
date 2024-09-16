package com.example.jetpack_multiplenavigation.products.retrofit.productsUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.products.retrofit.presentation.ProductsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UpdateProductScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val productsViewModel = koinViewModel<ProductsViewModel>()
    val localKeyboard = LocalSoftwareKeyboardController.current
    val result = remember {
        mutableStateOf("")
    }
    val title = remember {
        mutableStateOf(TextFieldValue())
    }
    val text = remember {
        mutableStateOf(TextFieldValue())
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Retrofit POST Request in Android",
            color = Color.Green,
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            value = title.value,
            onValueChange = {
                title.value = it
            },
            placeholder = {
                Text(text = "Enter your title")
            },
            label = {
                Text(text = "Title")
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 15.sp,
                color = Color.Black,
                fontFamily = FontFamily.Serif
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            value = text.value,
            onValueChange = {
                text.value = it
            },
            placeholder = {
                Text(text = "Enter your title")
            },
            label = {
                Text(text = "Title")
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 15.sp,
                color = Color.Black,
                fontFamily = FontFamily.Serif
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                localKeyboard?.hide()
                productsViewModel.createPost(
                    context = context,
                    title = title,
                    text = text,
                    result = result,
                    //api = api
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Post Data",
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(10.dp)
            )
        }
        Text(
            text = result.value,
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                localKeyboard?.hide()
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Go Back",
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}
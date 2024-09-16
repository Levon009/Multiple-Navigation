package com.example.jetpack_multiplenavigation.customNavArgs.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.customNavArgs.data.Dogs
import com.example.jetpack_multiplenavigation.customNavArgs.data.model.BreedSize
import com.example.jetpack_multiplenavigation.customNavArgs.data.model.Dog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogListScreen(
    navController: NavHostController,
    onItemClick: (Dog, BreedSize) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Dog List Screen",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        val dogs = Dogs
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(dogs.keys.toList()) { dog ->
                Text(
                    text = "${dog.id} -- ${dog.name}",
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(dog, dogs[dog]!!)
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}
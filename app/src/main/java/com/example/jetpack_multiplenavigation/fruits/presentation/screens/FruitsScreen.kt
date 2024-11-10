package com.example.jetpack_multiplenavigation.fruits.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.fruits.data.allFruits

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitsScreen(navController: NavHostController) {
    var fruits by remember {
        mutableStateOf(allFruits)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Fruits",
                        style = MaterialTheme.typography.headlineMedium,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
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
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = fruits,
                key = {
                    it.name
                }
            ) { fruit ->
                ListItem(
                    headlineContent = {
                        Text(
                            text = fruit.name,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    supportingContent = {
                        Text(
                            text = fruit.description,
                            fontFamily = FontFamily.Serif,
                        )
                    },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Shopping cart"
                        )
                    },
                    trailingContent = {
                        Checkbox(
                            checked = fruit.isSelected,
                            onCheckedChange = {
                                fruits = fruits.map { currentFruit ->
                                    if (currentFruit == fruit) {
                                        currentFruit.copy(
                                            isSelected = !currentFruit.isSelected
                                        )
                                    } else currentFruit
                                }
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            fruits = fruits.map { currentFruit ->
                                if (currentFruit == fruit) {
                                    currentFruit.copy(
                                        isSelected = !currentFruit.isSelected
                                    )
                                } else currentFruit
                            }
                        }
                )
                HorizontalDivider()
            }
        }
    }
}
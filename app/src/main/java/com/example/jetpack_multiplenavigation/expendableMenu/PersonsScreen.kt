package com.example.jetpack_multiplenavigation.expendableMenu

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PersonsScreen(
    id: Int,
    navController: NavHostController
) {
    val context = (LocalContext.current as? Activity)
    val persons = getAllPersons()
    val dropDownItems = allDropDownItems
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Persons List",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                        )
                    }
                    IconButton(onClick = {
                        Toast.makeText(context, "Fast Food", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Fastfood,
                            contentDescription = "Fast Food",
                        )
                    }
                    IconButton(onClick = {
                        Toast.makeText(context, "Family rest room", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.FamilyRestroom,
                            contentDescription = "Family rest room",
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(persons) { person ->
                PersonItemUI(
                    personName = person.name,
                    dropDownItems = dropDownItems
                ) { item ->
                    when(item.text) {
                        "Settings" -> Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                        "Favorite" -> Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                        "Person" -> Toast.makeText(context, "Person", Toast.LENGTH_SHORT).show()
                        "Log out" -> context?.finish()
                    }
                }
            }
        }
    }
}
package com.example.jetpack_multiplenavigation.listSwipeDropMenu

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.listSwipeDropMenu.data.allLanguages
import com.example.jetpack_multiplenavigation.listSwipeDropMenu.dropDownMenu.LanguageItemUI
import com.example.jetpack_multiplenavigation.listSwipeDropMenu.dropDownMenu.getAllDropMenuItems
import com.example.jetpack_multiplenavigation.listSwipeDropMenu.swipeToDelete.SwipeToDeleteContainer
import com.example.jetpack_multiplenavigation.scrollToTopButton.ScrollToTopButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguagesScreen(navController: NavHostController) {
    val context = (LocalContext.current as? Activity)
    val state = rememberLazyListState()
    val allLanguages = allLanguages
    val dropMenuItems = getAllDropMenuItems()
    Scaffold(
        floatingActionButton = {
            ScrollToTopButton(
                state = state
            )
        },
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(elevation = 15.dp),
                title = {
                    Text(
                        text = "Languages",
                        style = MaterialTheme.typography.titleLarge,
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
                            contentDescription = "Favorite"
                        )
                    }
                    IconButton(onClick = {
                        Toast.makeText(context, "History", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = "History"
                        )
                    }
                    IconButton(onClick = {
                        Toast.makeText(context, "Fast food", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Fastfood,
                            contentDescription = "Fast food"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            itemsIndexed(
                items = allLanguages,
                key = { _, language ->
                    language.hashCode()
                }
            ) { _, language ->
                SwipeToDeleteContainer(
                    programmingLanguage = language,
                    onDelete = {
                        allLanguages -= language
                    }
                ) { programmingLanguage ->
                    LanguageItemUI(
                        languageName = programmingLanguage.languageName,
                        dropMenuItems = dropMenuItems
                    ) { dropMenuItem ->
                        when(dropMenuItem.title) {
                            "Settings", "Favorite", "Person" -> Toast.makeText(context, "${dropMenuItem.title}", Toast.LENGTH_SHORT).show()
                            else -> context?.finish()
                        }
                    }
                }
            }
        }
    }
}
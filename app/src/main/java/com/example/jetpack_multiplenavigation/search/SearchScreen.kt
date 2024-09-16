package com.example.jetpack_multiplenavigation.search

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.meditation.data.Person
import com.example.jetpack_multiplenavigation.navigation.BackToHome

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedBoxWithConstraintsScope")
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val fontFamily = FontFamily(Font(R.font.tack_one, FontWeight.Thin))
    val searchViewModel = viewModel<SearchViewModel>()
    val searchText = searchViewModel.searchText.collectAsStateWithLifecycle()
    val isSearching = searchViewModel.isSearching.collectAsStateWithLifecycle()
    val persons = searchViewModel.persons.collectAsStateWithLifecycle()
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    var active by remember {
        mutableStateOf(false)
    }
    Scaffold(
        modifier = modifier
    ) {
        SearchBar(
            expanded = active,
            onExpandedChange = {
                active = it
            },
            inputField = {
                SearchBarDefaults.InputField(
                    query = searchText.value,
                    onQueryChange = searchViewModel::onSearchTextChange,
                    onSearch = {
                        active = false
                        softwareKeyboardController?.hide()
                    },
                    expanded = active,
                    onExpandedChange = {
                        active = it
                    },
                    placeholder = {
                        Text(
                            text = "Search",
                            fontFamily = fontFamily
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            modifier = Modifier.clickable {
                                searchViewModel.addPerson(Person(firstName = searchText.value, lastName = ""))
                                searchViewModel.onSearchTextChange("")
                            }
                        )
                    },
                    trailingIcon = {
                        if (active) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                modifier = Modifier.clickable {
                                    if (searchText.value.isNotEmpty()) {
                                        searchViewModel.onSearchTextChange("")
                                    } else {
                                        active = false
                                    }
                                }
                            )
                        }
                    }
                )
            },
            tonalElevation = SearchBarDefaults.TonalElevation,
            colors = SearchBarDefaults.colors(
                dividerColor = Color.Red,
                containerColor = Color.LightGray
            )
        ) {
            if (isSearching.value) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Red
                    )
                    LinearProgressIndicator(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        color = Color.Magenta
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    items(persons.value) { person ->
                        Row(modifier = Modifier.padding(all = 14.dp)) {
                            Icon(
                                imageVector = Icons.Default.History,
                                contentDescription = "History",
                                modifier = Modifier.padding(end = 14.dp)
                            )
                            Text(
                                text = "${person.firstName} ${person.lastName}",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .fillMaxSize()
                .padding(45.dp)
        ) {
            BackToHome(
                navController = navController,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(30.dp)
            )
        }
    }
}
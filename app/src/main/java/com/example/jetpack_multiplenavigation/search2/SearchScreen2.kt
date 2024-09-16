package com.example.jetpack_multiplenavigation.search2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen2(navController: NavHostController) {
    val fontFamily = FontFamily(Font(R.font.tack_one, FontWeight.Thin))
    val viewModel = viewModel<Search2ViewModel>()
    val searchText = viewModel.searchText.collectAsStateWithLifecycle()
    val isSearching = viewModel.isSearching.collectAsStateWithLifecycle()
    val persons = viewModel.persons.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Search Person",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontFamily = fontFamily,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            keyboardController?.hide()
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow back",
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .clip(RoundedCornerShape(10.dp)),
                value = searchText.value,
                onValueChange = viewModel::onSearchTextChange,
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Start,
                    fontFamily = fontFamily
                ),
                placeholder = {
                    Text(
                        text = "Search",
                        fontSize = 18.sp,
                        fontFamily = fontFamily
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.DarkGray,
                        modifier = Modifier.clickable {
                            keyboardController?.hide()
                            viewModel.addPerson(Person2(firstName = searchText.value, lastName = ""))
                        }
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.DarkGray,
                        modifier = Modifier.clickable {
                            keyboardController?.hide()
                            viewModel.onSearchTextChange("")
                        }
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        keyboardController?.hide()
                    },
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.DarkGray
                )
            )
            if (isSearching.value) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Magenta
                    )
                    LinearProgressIndicator(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        color = Color.Cyan
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, top = 25.dp)
                ) {
                    items(persons.value) { person ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Default.History,
                                contentDescription = "History",
                                tint = Color.DarkGray,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Text(
                                text = "${person.firstName} ${person.lastName}",
                                fontFamily = fontFamily,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}
package com.example.jetpack_multiplenavigation.notes.presentation.notes

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.navigation.Routes
import com.example.jetpack_multiplenavigation.notes.core.util.TestTags
import com.example.jetpack_multiplenavigation.notes.presentation.deleteWithSwipe.SwipeDeleteNoteContainer
import com.example.jetpack_multiplenavigation.notes.presentation.notes.components.NoteItem
import com.example.jetpack_multiplenavigation.notes.presentation.notes.components.OrderSection
import com.example.jetpack_multiplenavigation.notes.sb.ObserveSB
import com.example.jetpack_multiplenavigation.notes.sb.SBAction
import com.example.jetpack_multiplenavigation.notes.sb.SBController
import com.example.jetpack_multiplenavigation.notes.sb.SBEvent
import com.example.jetpack_multiplenavigation.scrollToTopButton.ScrollToTopButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(navController: NavHostController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val viewModel = hiltViewModel<NotesViewModel>()
    val state = viewModel.state.value
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    ObserveSB(snackBarHostState = snackBarHostState)
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        floatingActionButton = {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Routes.AddEditNotes())
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
                ScrollToTopButton(state = lazyListState)
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notes",
                        style = MaterialTheme.typography.headlineMedium,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Routes.HomeScreen)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
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
                        Toast.makeText(context, "Refresh", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                Text(
                    text = "Your note",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold
                )
                IconButton(onClick = {
                    viewModel.onEvent(NotesEvents.ToggleOrderSection)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.Sort,
                        contentDescription = "Sort"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NotesEvents.Order(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .testTag(TestTags.ORDER_SECTION)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(
                    state.notes,
                    key = {_, note ->
                        note.hashCode()
                    }
                ) { _, note ->
                    SwipeDeleteNoteContainer(
                        note = note,
                        onRemove = {
                            viewModel.onEvent(NotesEvents.DeleteNote(note))
                            deleteNoteSnackBar(
                                scope = scope,
                                viewModel = viewModel
                            )
                            navController.navigate(Routes.Notes)
                        }
                    ) {
                        NoteItem(
                            note = note,
                            onDeleteClick = {
                                viewModel.onEvent(NotesEvents.DeleteNote(note))
                                deleteNoteSnackBar(
                                    scope = scope,
                                    viewModel = viewModel
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(Routes.AddEditNotes(
                                        noteId = note.id!!,
                                        noteColor = note.color
                                    ))
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

private fun deleteNoteSnackBar(
    scope: CoroutineScope,
    viewModel: NotesViewModel
) {
    scope.launch {
        SBController.sendEvent(
            event = SBEvent(
                message = "Note deleted.",
                action = SBAction(
                    name = "Undo",
                    action = {
                        viewModel.onEvent(NotesEvents.RestoreNote)
                    }
                )
            )
        )
    }
}
package com.example.jetpack_multiplenavigation.swipeWithActions.presentation.screens

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.scrollToTopButton.ScrollToTopButton
import com.example.jetpack_multiplenavigation.swipeWithActions.data.allSwipeAbleContacts
import com.example.jetpack_multiplenavigation.swipeWithActions.data.model.SwipeAbleContacts
import com.example.jetpack_multiplenavigation.swipeWithActions.presentation.uiTools.ActionIcon
import com.example.jetpack_multiplenavigation.swipeWithActions.presentation.uiTools.SwipeItemWithAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSwipeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val state = rememberLazyListState()
    val contacts = allSwipeAbleContacts
    Scaffold(
        floatingActionButton = {
            ScrollToTopButton(state = state)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Contacts",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium,
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
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(state = state) {
                itemsIndexed(
                    items = contacts,
                    key = { _, contact ->
                        contact.id
                    }
                ) { index, contact ->
                    SwipeItemWithAction(
                        isRevealed = contact.isOptionalRevealed,
                        onExpended = {
                            contacts[index] = contact.copy(isOptionalRevealed = true)
                        },
                        onCollapsed = {
                            contacts[index] = contact.copy(isOptionalRevealed = false)
                        },
                        actions = {
                            ActionIcon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                backgroundColor = Color.Red,
                                tint = Color.White,
                                onClick = {
                                    contacts -= contact
                                    Toast.makeText(
                                        context,
                                        "Contact ${contact.name} was removed.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                            ActionIcon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                backgroundColor = Color.Yellow,
                                tint = Color.Black,
                                onClick = {
                                    sendEmail(
                                        context = context,
                                        arrayOf("levonhakobyan9@gmail.com", "levonhakobyan1989@gmail.com"),
                                        subject = contact.id.toString(),
                                        content = contact.name
                                    )
                                }
                            )
                            ActionIcon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share",
                                backgroundColor = Color.Blue,
                                tint = Color.White,
                                onClick = {
                                    shareMessage(
                                        context = context,
                                        contact = contact
                                    )
                                }
                            )
                        }
                    ) {
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black)
                        ) {
                            Text(
                                text = "Contact ${contact.name}",
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily.Serif,
                                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun shareMessage(
    context: Context,
    contact: SwipeAbleContacts
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, contact.name)
    }
    if (intent.resolveActivity(context.applicationContext.packageManager) != null) {
        context.startActivity(Intent.createChooser(intent, "Choose app"))
    }
}

private fun sendEmail(
    context: Context,
    emailReceiver: Array<String>,
    content: String,
    subject: String
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain" // " image/* "
        putExtra(Intent.EXTRA_EMAIL, emailReceiver)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, content)
    }
    if (intent.resolveActivity(context.applicationContext.packageManager) != null) {
        context.startActivity(intent)
    }
}
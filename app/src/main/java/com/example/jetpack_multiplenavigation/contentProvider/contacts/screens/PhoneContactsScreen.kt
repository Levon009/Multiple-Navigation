package com.example.jetpack_multiplenavigation.contentProvider.contacts.screens

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.contentProvider.contacts.contactsContentProvider
import com.example.jetpack_multiplenavigation.contentProvider.contacts.data.Contact
import com.example.jetpack_multiplenavigation.contentProvider.contacts.presentation.PhoneContactsViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneContactsScreen(navController: NavHostController) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val phoneContactsViewModel = viewModel<PhoneContactsViewModel>()
    val contacts = remember {
        mutableStateListOf<Contact>()
    }

    var isPermissionGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        isPermissionGranted = isGranted
    }

    LaunchedEffect(key1 = isPermissionGranted) {
        delay(1000)
        if (!isPermissionGranted) {
            launcher.launch(
                Manifest.permission.READ_CONTACTS
            )
        } else {
            contactsContentProvider(
                context = context,
                phoneContactsViewModel = phoneContactsViewModel
            )
            delay(500)
            phoneContactsViewModel.contactsDetails.forEach { contactDetails ->
                phoneContactsViewModel.phones.forEach { phone ->
                    if (contactDetails.id == phone.id) {
                        contacts.add(Contact(contactDetails, phone))
                    }
                }
            }
            phoneContactsViewModel.updateContacts(contacts)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Contacts",
                        style = MaterialTheme.typography.headlineMedium,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Arrow back",
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                items(phoneContactsViewModel.contacts) { contact ->
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .clickable {
                                clipboardManager.setText(AnnotatedString(contact.phone.phoneNumber))
                                Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
                            }
                            .padding(10.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(75.dp)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = contact.contactDetails.name,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )
                            Text(
                                text = contact.phone.phoneNumber,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 0.8.sp,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}
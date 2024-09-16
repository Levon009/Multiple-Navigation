package com.example.jetpack_multiplenavigation.optionMenu

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionMenu(
    settings: String,
    logOut: String,
    navController: NavHostController,
) {
    val context = (LocalContext.current as? Activity)
    var showMenu by remember {
        mutableStateOf(false)
    }
    TopAppBar(
        modifier = Modifier,
        title = {
            Text(
                text = "Option Menu",
                fontSize = 22.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
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
                showMenu = !showMenu
            }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More"
                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = {
                    showMenu = false
                }
            ) {
                DropMenuItem1(settings = settings) {
                    showMenu = false
                    Toast.makeText(context, settings, Toast.LENGTH_SHORT).show()
                }
                DropMenuItem2(logOut = logOut) {
                    showMenu = false
                    Toast.makeText(context, logOut, Toast.LENGTH_SHORT).show()
                    context?.finish()
                }
            }
        }
    )
}
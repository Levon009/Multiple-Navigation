package com.example.jetpack_multiplenavigation.home

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.gamePack.presentation.GameActivity
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.navigation.Routes
import com.example.jetpack_multiplenavigation.ui.theme.OrangeYellow3

@Composable
fun HomeScreenUI(navController: NavHostController) {
    val context = LocalContext.current
    val fontFamily = FontFamily(Font(R.font.tack_one, FontWeight.Thin))
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var checked by remember {
            mutableStateOf(false)
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = {
                    val intent = Intent(context, GameActivity::class.java)
                    context.startActivity(intent)
                },
                elevation = ButtonDefaults.buttonElevation(25.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Air   Fighters",
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            ElevatedButton(
                onClick = {
                    navController.navigate(Routes.Editor)
                },
                elevation = ButtonDefaults.buttonElevation(25.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Editor",
                    fontFamily = fontFamily
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            FilledTonalButton(
                onClick = {
                    navController.navigate(Routes.Products) {
                        popUpTo<Routes.HomeScreen>() {
                            inclusive = false
                        }
                    }
                },
                elevation = ButtonDefaults.buttonElevation(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Cyan
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Products Retrofit",
                    fontFamily = fontFamily
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            FilledTonalIconToggleButton(
                checked = checked,
                onCheckedChange = {
                    checked = !checked
                    if (checked) {
                        navController.navigate(Routes.StudentsListRoute)
                    }
                },
                colors = IconButtonDefaults.iconToggleButtonColors(
                    containerColor = Color(0xFFFF7F50)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedButton(
                onClick = {
                    navController.navigate(Routes.Notes) {
                        popUpTo<Routes.HomeScreen> {
                            inclusive = false
                        }
                    }
                },
                elevation = ButtonDefaults.buttonElevation(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF70665A),
                    contentColor = Color.LightGray
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.Notes,
                    contentDescription = "Notes"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Notes",
                    color = Color.White,
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.CarsListFull) {
                        popUpTo<Routes.HomeScreen>() {
                            inclusive = false
                        }
                    }
                },
                elevation = FloatingActionButtonDefaults.elevation(25.dp),
                containerColor = Color.Magenta,
                shape = CircleShape,
                modifier = Modifier.size(45.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedButton(
                onClick = {
                    navController.navigate(Routes.EncryptDecrypt)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = OrangeYellow3
                ),
                elevation = ButtonDefaults.buttonElevation(15.dp)
            ) {
                Text(
                    text = "Cipher Manager",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedButton(
                onClick = {
                    navController.navigate(Routes.TimerServiceTS) {
                        popUpTo<Routes.HomeScreen>() {
                            inclusive = false
                        }
                    }
                },
                elevation = ButtonDefaults.buttonElevation(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.LightGray
                )
            ) {
                Text(
                    text = "Outlined Button",
                    color = Color.White,
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            TextButton(
                onClick = {
                    navController.navigate(Routes.TextPrinter(0))
                },
            ) {
                Text(
                    text = "Text printer",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily,
                )
            }
        }
    }
}
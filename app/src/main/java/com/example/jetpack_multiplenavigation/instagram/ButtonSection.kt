package com.example.jetpack_multiplenavigation.instagram

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.navigation.Routes

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val minWidth = 95.dp
    val height = 30.dp

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        ActionButton(
            text = "Following",
            icon = Icons.Default.ArrowDropDown,
            modifier = Modifier
                .defaultMinSize(minWidth)
                .height(height)
        ) {}
        ActionButton(
            text = "-Coil-",
            modifier = Modifier
                .defaultMinSize(minWidth)
                .height(height)
        ) {
            navController.navigate(Routes.CoilFull(0)) {
                popUpTo<Routes.HomeScreen>() {
                    inclusive = false
                }
            }
        }
        ActionButton(
            text = "Dialogs",
            modifier = Modifier
                .defaultMinSize(minWidth)
                .height(height)
        ) {
            navController.navigate(Routes.Dialogs)
        }
        ActionButton(
            icon = Icons.Default.ArrowDropDown,
            modifier = Modifier
                .height(height)
        ) {
            navController.navigate(Routes.DogListRoute) {
                popUpTo<Routes.HomeScreen>() {
                    inclusive = false
                }
            }
        }
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(start = 6.dp, end = 6.dp, bottom = 5.dp, top = 1.5.dp)
    ) {
        if (text != null) {
            Text(
                text = text,
                fontSize = 12.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        }
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}
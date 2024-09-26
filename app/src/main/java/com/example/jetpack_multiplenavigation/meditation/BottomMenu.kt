package com.example.jetpack_multiplenavigation.meditation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.meditation.bottomMenu.BottomMenuItem
import com.example.jetpack_multiplenavigation.ui.theme.AquaBlue
import com.example.jetpack_multiplenavigation.ui.theme.ButtonBlue
import com.example.jetpack_multiplenavigation.ui.theme.DeepBlue

fun getMenuItems() : List<BottomMenuItem> {
    return listOf(
        BottomMenuItem("Home", R.drawable.ic_home),
        BottomMenuItem("Music", R.drawable.ic_music),
        BottomMenuItem("Contacts", R.drawable.ic_profile),
        BottomMenuItem("ExBox", R.drawable.ic_bubble),
        BottomMenuItem("Timer", R.drawable.ic_moon),
    )
}

@Composable
fun BottomMenu(
    modifier: Modifier = Modifier,
    menuItems: List<BottomMenuItem>,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: (Int) -> Unit
) {
    var selectedItem by remember {
        mutableIntStateOf(-1)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        menuItems.forEachIndexed { index, item ->
            BottomMenuItem(
                menuItem = item,
                isItemSelected = selectedItem == index,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItem = index
                onItemClick(index)
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    menuItem: BottomMenuItem,
    isItemSelected: Boolean,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (isItemSelected) activeHighlightColor else Color.Transparent
                )
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = menuItem.iconId),
                contentDescription = menuItem.title,
                tint = if (isItemSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = menuItem.title,
            color = if (isItemSelected) activeTextColor else inactiveTextColor,
            textAlign = TextAlign.Center
        )
    }
}
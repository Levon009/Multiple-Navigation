package com.example.jetpack_multiplenavigation.drawerNavigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerNavigationBottom(
    fontFamily: FontFamily,
    scope: CoroutineScope,
    drawerState: DrawerState,
    onItemClick: (DrawerNavItem) -> Unit
) {
    val drawerItems = getDrawerItems()
    var selectedItem by remember {
        mutableStateOf(drawerItems[0])
    }
    drawerItems.forEach { item ->
        NavigationDrawerItem(
            label = {
                Text(
                    text = item.title,
                    color = Color.DarkGray,
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.Underline,
                    fontFamily = fontFamily,
                    modifier = Modifier.padding(5.dp)
                )
            },
            selected = selectedItem == item,
            onClick = {
                selectedItem = item
                scope.launch {
                    drawerState.close()
                }
                onItemClick(item)
            },
            icon = {

            },
            badge = {
                BadgedBox(
                    badge = {
                        if (item.hasBadge && item.badgeNum > 0) {
                            Badge {
                                Text(
                                    text = item.badgeNum.toString(),
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                }
            }
        )
    }
}
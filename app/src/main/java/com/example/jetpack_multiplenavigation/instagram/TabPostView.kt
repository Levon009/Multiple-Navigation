package com.example.jetpack_multiplenavigation.instagram

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.R

@Composable
fun getTabItems() : List<ImageWithText> {
    return listOf(
        ImageWithText(
            image = painterResource(id = R.drawable.ic_grid),
            text = "Posts"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.ic_reels),
            text = "Reels"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.ic_igtv),
            text = "IGTV"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.profile),
            text = "Profile"
        )
    )
}

@Composable
fun TabPostView(
    modifier: Modifier = Modifier,
    tabItems: List<ImageWithText>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    val selectedContentColor = Color.Black
    val unselectedContentColor = Color(0xFF777777)
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier
    ) {
        tabItems.forEachIndexed { index, tabItem ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                },
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor
            ) {
                Icon(
                    painter = tabItem.image,
                    contentDescription = tabItem.text,
                    tint = if (selectedTabIndex == index) selectedContentColor else unselectedContentColor,
                    modifier = Modifier
                        .size(45.dp)
                        .padding(10.dp)
                )
            }
        }
    }
}
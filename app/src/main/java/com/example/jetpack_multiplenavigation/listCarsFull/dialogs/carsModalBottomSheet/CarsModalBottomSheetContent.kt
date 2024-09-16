package com.example.jetpack_multiplenavigation.listCarsFull.dialogs.carsModalBottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.R

@Composable
fun CarsModalBottomSheetContent() {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight(0.42f)
    ) {
        ContentTop()
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.padding(5.dp)
        )
        ContentBottom()
    }
}

@Composable
fun ContentTop() {
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        TopContentItem()
        TopContentItem()
        TopContentItem()
    }
}

@Composable
fun TopContentItem() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        CarsRoundImage(
            painter = painterResource(id = R.drawable.white_dog),
            modifier = Modifier
                .size(60.dp)
                .clickable { }
        )
        CarsRoundImage(
            painter = painterResource(id = R.drawable.red_dog),
            modifier = Modifier
                .size(60.dp)
                .clickable { }
        )
        CarsRoundImage(
            painter = painterResource(id = R.drawable.orange_dog),
            modifier = Modifier
                .size(60.dp)
                .clickable { }
        )
        CarsRoundImage(
            painter = painterResource(id = R.drawable.yellow_dog),
            modifier = Modifier
                .size(60.dp)
                .clickable { }
        )
    }
}

@Composable
fun ContentBottom() {
    val items = carsAllCardBottomSheetItems
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
        ) {
            items(items) { item ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(55.dp)
                        .clickable { }
                        .padding(5.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = Color.DarkGray,
                        modifier = Modifier
                            .size(30.dp)
                            .border(1.dp, Color.Black, CircleShape)
                            .padding(3.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }
    }
}
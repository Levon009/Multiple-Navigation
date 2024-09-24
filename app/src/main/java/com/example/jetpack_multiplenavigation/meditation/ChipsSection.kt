package com.example.jetpack_multiplenavigation.meditation

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_multiplenavigation.ui.theme.ButtonBlue
import com.example.jetpack_multiplenavigation.ui.theme.DarkerButtonBlue
import com.example.jetpack_multiplenavigation.ui.theme.TextWhite

fun getChips() : List<String> {
    return listOf(
        "Drawer",
        "Image",
        "CbRbSwitch",
        "Snapshot",
        "TextFields",
        "Produce",
        "Search 2",
        "PersonsListFull",
        "DragDropList",
        "LanguagesList",
        "PersonsList",
        "ExpendedList",
        "PickSaveImage",
        "Koin1",
        "Intents",
        "Fragments"
    )
}

@Composable
fun ChipsSection(
    chips: List<String>,
    implementChipClick: (Int) -> Unit
) {
    var selectedChip by rememberSaveable {
        mutableIntStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(145.dp)
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        selectedChip = it
                        implementChipClick(it)
                    }
                    .background(
                        if (selectedChip == it) ButtonBlue else DarkerButtonBlue
                    )
                    .padding(horizontal = 15.dp, vertical = 8.dp)
            ) {
                Text(
                    text = chips[it],
                    color = TextWhite,
                    fontSize = 12.sp,
                    maxLines = 1,
                    fontFamily = FontFamily.Serif,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.basicMarquee()
                )
            }
        }
    }
}
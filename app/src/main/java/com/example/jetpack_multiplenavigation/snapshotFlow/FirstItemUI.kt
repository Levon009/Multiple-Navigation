package com.example.jetpack_multiplenavigation.snapshotFlow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun FirstItemUI(modifier: Modifier = Modifier) {
    val persons = persons
    var num by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(key1 = Unit) {
        snapshotFlow {
            num
        }.collect { currentNum ->
            if (currentNum < persons.size){
                delay(2000L)
                num++
            } else {
                num = 0
            }
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Icon(
            imageVector = persons[num],
            contentDescription = "Person",
            modifier = modifier.size(250.dp)
        )
    }
}
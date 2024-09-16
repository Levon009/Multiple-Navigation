package com.example.jetpack_multiplenavigation.radioBCheckBSwitch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RadioButtons() {
    val radioButtons = remember {
        mutableStateListOf(
            ToggleableInfo(
                isChecked = false,
                text = "Photos"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "Videos"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "Audio"
            )
        )
    }

    radioButtons.forEach { toggleInfo ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                radioButtons.replaceAll {
                    it.copy(
                        isChecked = toggleInfo.text == it.text
                    )
                }
            }
        ) {
            RadioButton(
                selected = toggleInfo.isChecked,
                onClick = { 
                    radioButtons.replaceAll { 
                        it.copy(
                            isChecked = toggleInfo.text == it.text
                        )
                    }
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor =  Color.Green,
                    unselectedColor = Color.Black
                )
            )
            Text(text = toggleInfo.text)
        }
    }

}
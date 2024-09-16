package com.example.jetpack_multiplenavigation.radioBCheckBSwitch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CheckBoxes() {
    val checkboxes = remember {
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
    var triState by remember {
        mutableStateOf(ToggleableState.Indeterminate)
    }
    val toggleTriState = {
        triState = when (triState) {
            ToggleableState.Indeterminate -> ToggleableState.On
            ToggleableState.On -> ToggleableState.Off
            else -> ToggleableState.On
        }
        checkboxes.indices.forEach { index ->
            checkboxes[index] = checkboxes[index].copy(
                isChecked = triState == ToggleableState.On
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                toggleTriState()
            }
            .padding(end = 16.dp)
    ) {
        TriStateCheckbox(
            state = triState,
            onClick = toggleTriState
        )
        Text(
            text = "File types",
            fontWeight = FontWeight.Bold
        )
    }
    checkboxes.forEachIndexed { index, toggleableInfo ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 32.dp)
                .clickable {
                    checkboxes[index] = toggleableInfo.copy(
                        isChecked = !toggleableInfo.isChecked
                    )
                }
                .padding(end = 16.dp)
        ) {
            Checkbox(
                checked = toggleableInfo.isChecked,
                onCheckedChange = { isChecked ->
                    checkboxes[index] = toggleableInfo.copy(
                        isChecked = isChecked
                    )
                },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color.White,
                    checkedColor = Color.Green,
                    uncheckedColor = Color.LightGray
                )
            )
            Text(text = toggleableInfo.text)
        }
    }
}
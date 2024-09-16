package com.example.jetpack_multiplenavigation.radioBCheckBSwitch

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Switcher() {
    var switch by remember {
        mutableStateOf(
            ToggleableInfo(
                isChecked = false,
                text = "Dark mode"
            )
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(15.dp)
    ) {
        Text(
            text = switch.text,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = switch.isChecked,
            onCheckedChange = { isChecked ->
                switch = switch.copy(
                    isChecked = isChecked
                )
            },
            thumbContent = {
                Icon(
                    imageVector = if (switch.isChecked) Icons.Default.Check else Icons.Default.Close,
                    contentDescription = null
                )
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                uncheckedThumbColor = Color.DarkGray,
                checkedTrackColor = Color.LightGray,
                uncheckedTrackColor = Color.LightGray,
                checkedIconColor = Color.Green,
                uncheckedIconColor = Color.Red,
                checkedBorderColor = Color.DarkGray,
                uncheckedBorderColor = Color.DarkGray
            )
        )
    }
}
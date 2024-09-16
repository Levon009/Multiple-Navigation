package com.example.jetpack_multiplenavigation.expendableList

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ListItemContent(name: String) {
    var expended by remember {
        mutableStateOf(false)
    }
    val extraPadding by animateDpAsState(
        targetValue = if (expended) 25.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )
    Surface(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Row{
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Course")
                    Text(
                        text = name,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                OutlinedButton(onClick = {
                    expended = !expended
                }) {
                    Text(
                        text = if (expended) "Show less" else "Show more",
                        color = Color.White
                    )
                }
            }

            if (expended) {
                Column(
                    modifier = Modifier.padding(
                        top = extraPadding.coerceAtLeast(0.dp),
                        bottom = extraPadding.coerceAtLeast(0.dp)
                    )
                ) {
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
                    )
                }
            }
        }
    }
}
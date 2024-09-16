package com.example.jetpack_multiplenavigation.expendableMenu

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PersonItemUI(
    personName: String,
    dropDownItems: List<DropMenuItem>,
    onItemClick: (DropMenuItem) -> Unit
) {
    val localDensity = LocalDensity.current
    var showMenu by rememberSaveable {
        mutableStateOf(false)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val color = Color.Blue
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(.07f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged {
                itemHeight = with(localDensity) {
                    it.height.toDp()
                }
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .indication(interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(
                        onPress = {
                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(press))
                        },
                        onLongPress = {
                            showMenu = true
                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                        }
                    )
                }
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = personName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = {
                showMenu = false
            },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight
            )
        ) {
            dropDownItems.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.text) },
                    onClick = {
                        showMenu = false
                        onItemClick(item)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = item.leadingIcon,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = item.trailingIcon,
                            contentDescription = null
                        )
                    },
                    colors = MenuDefaults.itemColors(
                        trailingIconColor = Color.Red
                    )
                )
            }
        }
    }
}
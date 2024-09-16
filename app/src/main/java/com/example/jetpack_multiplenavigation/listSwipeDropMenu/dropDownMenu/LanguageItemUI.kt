package com.example.jetpack_multiplenavigation.listSwipeDropMenu.dropDownMenu

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun LanguageItemUI(
    languageName: String,
    dropMenuItems: List<DropMenuItem>,
    onItemClick: (DropMenuItem) -> Unit
) {
    val color = Color.Blue
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged {
                itemHeight = with(localDensity) {
                    it.height.toDp()
                }
            }
    ) {
        ListItem(
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
                .padding(vertical = 10.dp),
            headlineContent = {
                Text(
                    text = languageName,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            supportingContent = {
                Text(
                    text = "Programming language",
                    style = MaterialTheme.typography.bodySmall
                )
            },
            leadingContent = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(Color.Cyan)
                        .padding(15.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Face",
                        tint = Color.White,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                    )
                }
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite"
                )
            },
            colors = ListItemDefaults.colors(
                trailingIconColor = Color.Red
            ),
            shadowElevation = 15.dp,
            tonalElevation = 5.dp,
        )
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = {
                showMenu = false
            },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight
            )
        ) {
            dropMenuItems.forEach { menuItem ->
                DropdownMenuItem(
                    text = { Text(text = menuItem.title) },
                    onClick = {
                        showMenu = false
                        onItemClick(menuItem)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = menuItem.leadingIcon,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = menuItem.trailingIcon,
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
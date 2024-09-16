package com.example.jetpack_multiplenavigation.dragDropListItem

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListItemUI(
    text: String,
    index: Int,
    dragDropListState: DragDropListState
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
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
                    }
                )
            }
            .composed {
                val offsetOrNull = dragDropListState.itemDisplacement.takeIf {
                    index == dragDropListState.currentIndexOfDraggedItem
                }
                Modifier.graphicsLayer {
                    translationY = offsetOrNull ?: 0f
                }
            }
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(20.dp),
        headlineContent = {
            Text(
                text = text,
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
            )
        },
        supportingContent = {
            Text(
                text = "Item",
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
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
        tonalElevation = 5.dp
    )
}
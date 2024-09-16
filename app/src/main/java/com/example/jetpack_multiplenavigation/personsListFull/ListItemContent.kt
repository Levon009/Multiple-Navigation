package com.example.draf.personsListFull

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.draf.personsListFull.dragDropListItem.DragDropListState
import com.example.draf.personsListFull.dropOptionMenu.DropMenuItem
import com.example.draf.personsListFull.dropOptionMenu.DropOptionMenuItem

@Composable
fun ListItemContent(
    name: String,
    supportingText: String,
    index: Int,
    dropMenuItems: List<DropMenuItem>,
    dragDropListState: DragDropListState,
) {
    val context = (LocalContext.current as? Activity)
    val localDensity = LocalDensity.current
    val interactionSource = remember {
        MutableInteractionSource()
    }
    var showMenu by remember {
        mutableStateOf(false)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged {
                itemHeight = with(localDensity) {
                    it.height.toDp()
                }
            }
            .composed {
                val offsetOrNull = dragDropListState.itemDisplacement.takeIf {
                    index == dragDropListState.draggedItemIndex
                }
                Modifier.graphicsLayer {
                    showMenu = false
                    translationY = offsetOrNull ?: 0f
                }
            }
            .background(Color.Transparent, RoundedCornerShape(8.dp))
            .padding(20.dp)
    ) {
        ListItem(
            modifier = Modifier
                .fillMaxSize()
                .indication(interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(
                        onPress = {
                            showMenu = true
                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())

                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(press = press))
                        },
                    )
                },
            headlineContent = {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            },
            supportingContent = {
                Text(
                    text = supportingText,
                    style = MaterialTheme.typography.bodySmall,
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
            tonalElevation = 5.dp,
            shadowElevation = 15.dp
        )
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = {
                showMenu = false
            },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight / 3f
            )
        ) {
            dropMenuItems.forEach { item ->
                DropOptionMenuItem(item = item) {
                    showMenu = false
                    when(item.title) {
                        "Settings", "Favorite", "Person" -> Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
                        else -> context?.finish()
                    }
                }
            }
        }
    }
}
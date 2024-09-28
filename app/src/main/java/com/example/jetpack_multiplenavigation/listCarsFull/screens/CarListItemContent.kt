package com.example.jetpack_multiplenavigation.listCarsFull.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
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
import androidx.compose.ui.unit.sp
import com.example.jetpack_multiplenavigation.listCarsFull.carDropOptionMenu.CarDropDownMenu
import com.example.jetpack_multiplenavigation.listCarsFull.carDropOptionMenu.getAllCarDropMenuItems
import com.example.jetpack_multiplenavigation.listCarsFull.carsDragDropListItem.DragDropCarsListState
import com.example.jetpack_multiplenavigation.listCarsFull.carsExpendedList.CarsExpendedListContent

@SuppressLint("UnnecessaryComposedModifier")
@Composable
fun CarListItemContent(
    name: String,
    supportingText: String,
    index: Int,
    dragDropCarsListState: DragDropCarsListState
) {
    val context = (LocalContext.current as? Activity)
    val allOptionMenuItems = getAllCarDropMenuItems()
    val localDensity = LocalDensity.current
    val interactionSource = remember {
        MutableInteractionSource()
    }
    var showMenu by remember {
        mutableStateOf(false)
    }
    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    var isExpended by remember {
        mutableStateOf(false)
    }
    val expendedItemSizeState by animateDpAsState(
        targetValue = if (isExpended) 30.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        ),
        label = "Expend list"
    )
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged {
                itemHeight = with(localDensity) {
                    it.height.toDp()
                }
            }
            .composed {
                val offsetOrNull = dragDropCarsListState.itemDisplacement?.takeIf {
                    index == dragDropCarsListState.draggedItemIndex
                }
                Modifier.graphicsLayer {
                    showMenu = false
                    translationY = offsetOrNull ?: 0f
                }
            }
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Transparent)
            .padding(20.dp)
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

                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                        }
                    )
                },
            headlineContent = {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    letterSpacing = 0.5.sp
                )
            },
            supportingContent = {
                Text(
                    text = supportingText,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif,
                    letterSpacing = 0.5.sp
                )
            },
            leadingContent = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(Color.Magenta)
                        .padding(15.dp)
                        .clickable {
                            showMenu = true
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                    )
                }
            },
            trailingContent = {
                IconButton(
                    onClick = {
                        isExpended = !isExpended
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.DarkGray
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Info",
                        tint = Color.LightGray
                    )
                }
            },
            tonalElevation = 5.dp,
            shadowElevation = 15.dp
        )
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = {
                showMenu = false
            },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight / 4f
            )
        ) {
            allOptionMenuItems.forEach { item ->
                CarDropDownMenu(
                    carDropMenuItem = item
                ) {
                    showMenu = false
                    when(item.title) {
                        "Settings", "Favorite", "Person" -> Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
                        else -> context?.finish()
                    }
                }
            }
        }
        if (isExpended) {
            CarsExpendedListContent(
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        top = expendedItemSizeState.coerceAtLeast(0.dp),
                        end = 8.dp,
                        bottom = expendedItemSizeState.coerceAtLeast(0.dp)
                    )
            )
        }
    }
}
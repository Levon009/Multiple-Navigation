package com.example.jetpack_multiplenavigation.instagram

import android.app.Activity
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.instagram.optionMenu.optionMenuItems
import com.example.jetpack_multiplenavigation.navigation.Routes

@Composable
fun TopBarSection(
    modifier: Modifier = Modifier,
    name: String,
    age: Int,
    navHostController: NavHostController,
    onClick: () -> Unit,
) {
    val context = (LocalContext.current as? Activity)
    val density = LocalDensity.current
    val optionMenuItems = optionMenuItems
    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    var itemWidth by remember {
        mutableStateOf(0.dp)
    }
    var showMenu by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .onSizeChanged {
                itemWidth = with(density) {
                    it.width.toDp()
                }
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
                .clickable {
                    navHostController.navigateUp()
                }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = "${name}__${age}",
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
                .clickable {
                    onClick()
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bell),
                contentDescription = "Bell",
                modifier = Modifier.size(24.dp)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
                .indication(interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(
                        onPress = {
                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(press))

                            showMenu = true
                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                        }
                    )
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_dotmenu),
                contentDescription = "Menu",
                modifier = Modifier.size(24.dp)
            )
        }

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = {
                showMenu = false
            },
            offset = pressOffset.copy(
                x = pressOffset.x + itemWidth,
                y = pressOffset.y - 5.dp
            )
        ) {
            optionMenuItems.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item.title,
                            fontFamily = FontFamily.Serif
                        )
                    },
                    onClick = {
                        showMenu = false
                        when(item.title) {
                            "DownloadManager" -> {
                                navHostController.navigate(Routes.ImageDownloadManager)
                            }
                            "Employee" -> {
                                navHostController.navigate(Routes.EmployeeDI)
                            }
                            "Koin" -> {
                                navHostController.navigate(Routes.Koin1) {
                                    popUpTo<Routes.HomeScreen>() {
                                        inclusive = false
                                    }
                                }
                            }
                            "Log out" -> context?.finish()
                        }
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = item.leadingIcon,
                            contentDescription = item.title
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = item.trailingIcon,
                            contentDescription = item.title
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
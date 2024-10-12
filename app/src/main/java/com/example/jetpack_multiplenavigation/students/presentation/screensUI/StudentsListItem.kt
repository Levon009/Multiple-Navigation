package com.example.jetpack_multiplenavigation.students.presentation.screensUI

import android.app.Activity
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.students.domain.model.Student
import com.example.jetpack_multiplenavigation.students.data.StudentsEvent
import com.example.jetpack_multiplenavigation.students.presentation.components.studentExpendList.StudentExpendList
import com.example.jetpack_multiplenavigation.students.presentation.components.studentsDropDownMenu.StudentsDropDownMenu
import com.example.jetpack_multiplenavigation.students.presentation.components.studentsDropDownMenu.getAllStudentMenuItems

@Composable
fun StudentsListItem(
    student: Student,
    onEvent: (StudentsEvent) -> Unit,
    onLanguageClick: () -> Unit,
    onItemClick: (Student) -> Unit
) {
    val context = (LocalContext.current as? Activity)
    val allStudentsMenuItems = getAllStudentMenuItems()
    val density = LocalDensity.current
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
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged {
                itemHeight = with(density) {
                    it.height.toDp()
                }
            }
            .padding(15.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Transparent)
            .padding(10.dp)
    ) {
        ListItem(
            modifier = Modifier
                .indication(interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(
                        onPress = {
                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(press))

                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                        },
                    )
                }
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(12.dp)),
            headlineContent = {
                Text(
                    text = "Name: ${student.firstName} ${student.secondName}",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.basicMarquee(),
                )
            },
            overlineContent = {
                Text(
                    text = "ID: ${student.id}",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                )
            },
            supportingContent = {
                Text(
                    text = "Age: ${student.age}",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                )
            },
            leadingContent = {
                Spacer(modifier = Modifier.height(50.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(top = 20.dp)
                        .clip(CircleShape)
                        .background(Color.Magenta)
                        .clickable {
                            showMenu = true
                        }
                        .padding(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Person",
                        tint = Color.Yellow,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            trailingContent = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable {
                            isExpended = !isExpended
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play Arrow",
                    )
                }
            },
            shadowElevation = 15.dp,
            tonalElevation = 15.dp,
            colors = ListItemDefaults.colors(
                trailingIconColor = Color.Red
            )
        )
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = {
                showMenu = false
            },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight / 3
            )
        ) {
            allStudentsMenuItems.forEach { item ->
                StudentsDropDownMenu(studentDropMenuItem = item) {
                    showMenu = false
                    when (item.title) {
                        "Update" -> {
                            onItemClick(student)
                        }
                        "Languages" -> {
                            onLanguageClick()
                        }
                        "Marks" -> {
                            onEvent(StudentsEvent.ShowMarksDialog)
                        }
                        else -> context?.finish()
                    }
                }
            }
        }

        if (isExpended) {
            StudentExpendList(
                modifier = Modifier
                    .background(Color.White)
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
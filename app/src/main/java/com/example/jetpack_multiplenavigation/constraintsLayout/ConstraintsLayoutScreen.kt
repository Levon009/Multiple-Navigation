package com.example.jetpack_multiplenavigation.constraintsLayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConstraintsLayoutScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Constraints Layout",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        ConstraintLayout(
            getConstraints(),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .layoutId("redBox")
            )
            Box(
                modifier = Modifier
                    .background(Color.Green)
                    .layoutId("greenBox")
            )
        }
    }
}

private fun getConstraints() : ConstraintSet {
    val constraints = ConstraintSet {
        val redBox = createRefFor("redBox")
        val greenBox = createRefFor("greenBox")
        val guideLineTop = createGuidelineFromTop(0.15f)

        constrain(redBox) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(greenBox) {
            start.linkTo(redBox.end)
            top.linkTo(redBox.bottom)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        createHorizontalChain(redBox, greenBox, chainStyle = ChainStyle.Packed)
    }

    return constraints
}
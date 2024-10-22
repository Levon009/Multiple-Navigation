package com.example.jetpack_multiplenavigation.meditation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.gamePack.presentation.GameActivity
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.meditation.feature.Feature
import com.example.jetpack_multiplenavigation.meditation.feature.standardQuadTo
import com.example.jetpack_multiplenavigation.navigation.Routes
import com.example.jetpack_multiplenavigation.ui.theme.*

fun getFeatures() : List<Feature> {
    return listOf(
        Feature(
            title = "Sleep Meditation",
            R.drawable.ic_headphone,
            mediumColor = BlueViolet1,
            lightColor = BlueViolet2,
            darkColor = BlueViolet3
        ),
        Feature(
            title = "Tips for sleeping",
            R.drawable.ic_videocam,
            LightGreen1,
            LightGreen2,
            LightGreen3
        ),
        Feature(
            title = "Night Island",
            R.drawable.ic_headphone,
            OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3
        ),
        Feature(
            title = "Calming Sounds",
            R.drawable.ic_videocam,
            Beige1,
            Beige2,
            Beige3
        ),
        Feature(
            title = "Sleep meditation 2",
            R.drawable.ic_headphone,
            BlueViolet2,
            BlueViolet1,
            BlueViolet3
        ),
        Feature(
            title = "Tips for sleeping 2",
            R.drawable.ic_videocam,
            OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3
        ),
        Feature(
            title = "Tips for sleeping 3",
            R.drawable.ic_videocam,
            LightGreen1,
            LightGreen2,
            LightGreen3
        ),
        Feature(
            title = "Sleep meditation 3",
            R.drawable.ic_headphone,
            BlueViolet1,
            BlueViolet2,
            BlueViolet3
        ),
        Feature(
            title = "Night Island 4",
            R.drawable.ic_headphone,
            Beige1,
            Beige2,
            Beige3
        ),
        Feature(
            title = "Calming Sounds 4",
            R.drawable.ic_videocam,
            OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3
        ),
        Feature(
            title = "Night Island 5",
            R.drawable.ic_headphone,
            LightGreen1,
            LightGreen2,
            LightGreen3
        ),
        Feature(
            title = "Calming Sounds 5",
            R.drawable.ic_videocam,
            mediumColor = BlueViolet1,
            lightColor = BlueViolet2,
            darkColor = BlueViolet3
        ),
        Feature(
            title = "Sleep Meditation 6",
            R.drawable.ic_headphone,
            Beige1,
            Beige2,
            Beige3
        ),
        Feature(
            title = "Tips for sleeping 6",
            R.drawable.ic_videocam,
            OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3,
        ),
    )
}

fun getMediumColoredPath(width: Int, height: Int) : Path {
    val mediumColoredPoint1 = Offset(0f, height * 0.3f)
    val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
    val mediumColoredPoint3 = Offset(width * 0.3f, height * 0.05f)
    val mediumColoredPoint4 = Offset(width * 0.7f, height * 0.75f)
    val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

    return Path().apply {
        moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
        standardQuadTo(mediumColoredPoint1, mediumColoredPoint2)
        standardQuadTo(mediumColoredPoint2, mediumColoredPoint3)
        standardQuadTo(mediumColoredPoint3, mediumColoredPoint4)
        standardQuadTo(mediumColoredPoint4, mediumColoredPoint5)
        lineTo(width + 100f, height + 100f)
        lineTo(-100f, height + 100f)
        close()
    }
}

fun getLightColoredPath(width: Int, height: Int) : Path {
    val lightColoredPoint1 = Offset(0f, height * 0.35f)
    val lightColoredPoint2 = Offset(width * 0.1f, height * 0.4f)
    val lightColoredPoint3 = Offset(width * 0.35f, height * 0.65f)
    val lightColoredPoint4 = Offset(width * 0.65f, height.toFloat())
    val lightColoredPoint5 = Offset(width * 1.4f, -height.toFloat() / 3)

    return Path().apply {
        moveTo(lightColoredPoint1.x, lightColoredPoint1.y)
        standardQuadTo(lightColoredPoint1, lightColoredPoint2)
        standardQuadTo(lightColoredPoint2, lightColoredPoint3)
        standardQuadTo(lightColoredPoint3, lightColoredPoint4)
        standardQuadTo(lightColoredPoint4, lightColoredPoint5)
        lineTo(width + 100f, height + 100f)
        lineTo(-100f, height + 100f)
        close()
    }
}

@Composable
fun FeatureSection(
    features: List<Feature>,
    navController: NavHostController
) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text(
            text = "Features",
            color = TextWhite,
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItemUi(feature = features[it]) {
                    when (it) {
                        0 -> context.startActivity(Intent(context, GameActivity::class.java))
                        1 -> navController.navigate(Routes.Editor)
                        2 -> navController.navigate(Routes.Notes)
                        3 -> navController.navigate(Routes.CircleAnimation)
                        4 -> navController.navigate(Routes.MatrixEffect)
                        5 -> navController.navigate(Routes.CircleTouch)
                        6 -> navController.navigate(Routes.Products)
                        7 -> navController.navigate(Routes.PersonsListFull(
                            supportingText = "Person Item"
                        ))
                        8 -> navController.navigate(Routes.SimpleAnimation(0))
                        9 -> navController.navigate(Routes.OptionMenu(settings = "Settings", logOut = "Log out"))
                        10 -> navController.navigate(Routes.Authorization)
                        11 -> navController.navigate(Routes.EmailPreferences) {
                            popUpTo<Routes.HomeScreen>() {
                                inclusive = false
                            }
                        }
                        12 -> navController.navigate(Routes.MediaContents)
                        13 -> navController.navigate(Routes.DaggerCustom)
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun FeatureItemUi(
    feature: Feature,
    onItemClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .padding(7.5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
            .padding(15.dp)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val mediumColoredPath = getMediumColoredPath(width, height)
        val lightColoredPath = getLightColoredPath(width, height)

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }
        Text(
            text = feature.title,
            color = TextWhite,
            lineHeight = 26.sp,
            fontFamily = FontFamily.Serif,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.TopStart)
        )
        Text(
            text = "Start",
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(10.dp))
                .background(ButtonBlue)
                .clickable {
                    onItemClick()
                }
                .padding(horizontal = 20.dp, vertical = 2.dp)
        )
        Icon(
            painter = painterResource(id = feature.iconId),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(24.dp)
                .clickable { }
        )
    }
}
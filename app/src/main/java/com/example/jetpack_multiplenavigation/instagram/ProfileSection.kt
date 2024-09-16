package com.example.jetpack_multiplenavigation.instagram

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_multiplenavigation.R

@Composable
fun ProfileSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            RoundImage(
                painter = painterResource(id = R.drawable.lh),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            StatSection(modifier = Modifier.weight(7f))
        }
        Spacer(modifier = Modifier.height(5.dp))
        ProfileDescription(
            displayName = "Programming Mentor",
            description =  "10 years of coding experience\n" +
                    "want me to make your app? Send me an email\n" +
                    "Subscribe to my youtube channel.",
            url = "https://youtube.com/c/PhillipLackner",
            followedBy = listOf("NakOO", "miaKhalifa"),
            othersCount = 17
        )
    }
}

@Composable
fun StatSection(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        Stat(numberText = "601", text = "Posts")
        Stat(numberText = "99.8k", text = "Followers")
        Stat(numberText = "71", text = "Following")
    }
}

@Composable
fun Stat(
    modifier: Modifier = Modifier,
    numberText: String,
    text: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText,
            fontSize = 28.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
    url: String,
    followedBy: List<String>,
    othersCount: Int
) {
    val fontFamily = FontFamily(Font(R.font.gothica1_regular, FontWeight.Thin))
    val letterSpacing = 0.5.sp
    val lineHeight = 16.sp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily,
            fontSize = 15.sp,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = description,
            fontFamily = fontFamily,
            fontSize = 13.sp,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = url,
            color = Color(0xFF303091),
            fontSize = 13.sp,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        if (followedBy.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle (
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    append("Followed by ")
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop()
                        if (followedBy.size > 1 && index < followedBy.size - 1) {
                            append(", ")
                        }
                        if (othersCount > 2) {
                            append(" and ")
                            pushStyle(boldStyle)
                            append("$othersCount others")
                            pop()
                        }
                    }
                },
                fontSize = 13.sp,
                fontFamily = fontFamily,
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
        }
    }
}
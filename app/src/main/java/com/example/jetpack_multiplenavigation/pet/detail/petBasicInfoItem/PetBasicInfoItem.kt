package com.example.jetpack_multiplenavigation.pet.detail.petBasicInfoItem

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PetBasicInfoItem(
    name: String,
    gender: String,
    location: String,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            PetNameSection(
                name = name,
                animatedVisibilityScope = animatedVisibilityScope
            )
            Spacer(modifier = Modifier.height(8.dp))
            LocationSection(
                location = location,
                modifier = Modifier.padding(0.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            AdoptableSection(text = "Adoptable")
        }
        GenderSection(
            gender = gender,
            modifier = Modifier.height(80.dp)
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PetNameSection(
    name: String,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        modifier = Modifier
            .padding(end = 16.dp)
            .sharedElement(
                state = rememberSharedContentState(key = "text/$name"),
                animatedVisibilityScope = animatedVisibilityScope
            )
    )
}

@Composable
fun AdoptableSection(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        fontFamily = FontFamily.Serif,
        modifier = Modifier.padding(end = 12.dp)
    )
}

@Composable
fun LocationSection(
    modifier: Modifier = Modifier,
    location: String
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location",
            tint = Color.Red,
            modifier = Modifier.size(16.dp, 16.dp)
        )
        Text(
            text = location,
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(8.dp, 0.dp, 12.dp, 0.dp)
        )
    }
}

@Composable
fun GenderSection(
    modifier: Modifier = Modifier,
    gender: String
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        GenderTag(
            gender = gender,
            modifier = Modifier
        )
        Text(
            text = "Dog",
            fontFamily = FontFamily.Serif,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
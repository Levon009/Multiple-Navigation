package com.example.jetpack_multiplenavigation.pet.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.pet.data.Pet
import com.example.jetpack_multiplenavigation.pet.detail.petBasicInfoItem.GenderTag

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PetInfoItem(
    pet: Pet,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onPetClick: (Pet) -> Unit
) {
    with(sharedTransitionScope) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    onPetClick(pet)
                },
            elevation = CardDefaults.cardElevation(defaultElevation = 25.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row {
                    Image(
                        painter = painterResource(id = pet.image),
                        contentDescription = pet.name,
                        alignment = Alignment.CenterStart,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "image/${pet.id}"),
                                animatedVisibilityScope
                            )
                            .size(80.dp, 80.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    SecondSection(
                        pet = pet,
                        animatedVisibilityScope = animatedVisibilityScope,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                GenderSection(pet = pet)
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SecondSection(
    modifier: Modifier = Modifier,
    pet: Pet,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(modifier = modifier) {
        Text(
            text = pet.name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.sharedElement(
                state = rememberSharedContentState(key = "text/${pet.name}"),
                animatedVisibilityScope = animatedVisibilityScope
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                val boldStyle = SpanStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                append(pet.age)
                pushStyle(boldStyle)
                append(" | ")
                pop()
                append(pet.breed)
            },
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        LocationSection(pet = pet)
    }
}

@Composable
fun LocationSection(pet: Pet) {
    Row(verticalAlignment = Alignment.Bottom) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location",
            tint = Color.Red,
            modifier = Modifier.size(16.dp, 16.dp)
        )
        Text(
            text = pet.location,
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(8.dp, 0.dp, 12.dp, 0.dp)
        )
    }
}

@Composable
fun GenderSection(pet: Pet) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(80.dp)
    ) {
        GenderTag(
            gender = pet.gender,
            modifier = Modifier
        )
        Text(
            text = "Adoptable",
            fontFamily = FontFamily.Serif,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
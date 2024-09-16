package com.example.jetpack_multiplenavigation.pet.detail.petInfoItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.pet.data.Pet

@Composable
fun PetInfoItem(pet: Pet) {
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Title(title = "Pet Info")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            InfoCard(
                primaryText = pet.age,
                secondaryText = "Age",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            InfoCard(
                primaryText = pet.color,
                secondaryText = "Color",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            InfoCard(
                primaryText = pet.breed,
                secondaryText = "Breed",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start,
        fontFamily = FontFamily.Serif,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    )
}

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    primaryText: String,
    secondaryText: String
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = secondaryText,
                fontFamily = FontFamily.Serif,
            )
            Text(
                text = primaryText,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}
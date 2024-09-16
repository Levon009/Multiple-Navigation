package com.example.jetpack_multiplenavigation.listCarsFull.dialogs.carsCustomDialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.ui.theme.Beige3

@Composable
fun CarsCustomDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .border(1.dp, Beige3, RoundedCornerShape(15.dp))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                TitleContent(title = "Your selected items. Please select a payment method to continue.")
                Spacer(modifier = Modifier.height(5.dp))
                BaseInformation(modifier = Modifier.fillMaxWidth())
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DialogButton(
                        text = "Dismiss",
                        modifier = Modifier
                            .padding(10.dp)
                            .weight(1f)
                    ) {
                        onDismissRequest()
                    }
                    DialogButton(
                        text = "Confirm",
                        modifier = Modifier
                            .padding(10.dp)
                            .weight(1f)
                    ) {
                        onConfirmation()
                    }
                }
            }
        }
    }
}

@Composable
fun TitleContent(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BaseInformation(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        InformationContent(
            primaryText = "Samsung Galaxy S22",
            secondaryText = "799,00$",
            fontWeight = FontWeight.Normal
        )
        InformationContent(
            primaryText = "Galaxy S22 cover case",
            secondaryText = "32,00$",
            fontWeight = FontWeight.Normal
        )
        HorizontalDivider()
        InformationContent(
            primaryText = "Total Amount",
            secondaryText = "831,00$",
            fontWeight = FontWeight.Bold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            PaymentMethods(
                painter = painterResource(id = R.drawable.klarna),
                contentDescription = "Klarna"
            )
            PaymentMethods(
                painter = painterResource(id = R.drawable.paypal),
                contentDescription = "PayPal"
            )
        }
    }
}

@Composable
fun InformationContent(
    primaryText: String,
    secondaryText: String,
    fontWeight: FontWeight
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = primaryText,
            fontFamily = FontFamily.Serif,
            fontWeight = fontWeight
        )
        Text(
            text = secondaryText,
            fontFamily = FontFamily.Serif,
            fontWeight = fontWeight
        )
    }
}

@Composable
fun PaymentMethods(
    painter: Painter,
    contentDescription: String
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxWidth(0.3f)
            .clip(RoundedCornerShape(10.dp))
            .clickable { }
    )
}

@Composable
fun DialogButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Beige3,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
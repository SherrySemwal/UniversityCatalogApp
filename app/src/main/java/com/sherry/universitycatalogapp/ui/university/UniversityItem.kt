package com.sherry.universitycatalogapp.ui.university

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val paddingSmall = 8.dp
private val paddingMedium = 16.dp
private const val radiusValue = 40f

@Composable
fun UniversityItem(
    modifier: Modifier = Modifier,
    name: String,
    country: String,
    webpageUrl: String
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(paddingMedium)
    ) {

        Column(
            modifier = Modifier.weight(.9f),
            verticalArrangement = Arrangement.spacedBy(paddingSmall)
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                style = typography.bodyLarge,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = country,
                    style = typography.bodyMedium,
                )

               Icon(imageVector = Icons.Filled.Info, contentDescription = "more info" )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UniversityCardPreview() {
    UniversityItem(
        name = "Augustana Hochschule Neuendettelsau",
        country = "Germany",
        webpageUrl = "http://www.augustana.de/"
    )
}
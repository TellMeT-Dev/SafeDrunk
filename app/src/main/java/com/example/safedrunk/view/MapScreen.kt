package com.example.safedrunk.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safedrunk.ui.theme.SafeDrunkTheme

private data class PositionSample(
    val number: Int,
    val coordinates: String,
    val time: String
)

private val samplePositions = listOf(
    PositionSample(1, "19.4326, -99.1332", "14:30"),
    PositionSample(2, "19.4331, -99.1327", "14:35"),
    PositionSample(3, "19.4339, -99.1318", "14:40"),
    PositionSample(4, "19.4345, -99.1309", "14:45"),
    PositionSample(5, "19.4352, -99.1301", "14:50"),
    PositionSample(6, "19.4358, -99.1294", "14:55")
)

@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF080B12))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Recorrido",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Hoy, 14:30 - 14:55",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 14.sp
            )

            Spacer(Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF080B12)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Mapa del recorrido",
                    color = Color.White.copy(alpha = 0.4f),
                    fontSize = 14.sp
                )
            }

            Spacer(Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                listOf(
                    "2.4 km" to "Distancia",
                    "25 min" to "Duración",
                    "6" to "Puntos"
                ).forEach { (value, label) ->
                    Column {
                        Text(
                            text = value,
                            color = Color(0xFF00AEFF),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = label,
                            color = Color.White.copy(alpha = 0.5f),
                            fontSize = 11.sp
                        )
                    }
                }
            }

            Spacer(Modifier.height(28.dp))
            Text(
                text = "REGISTRO DE POSICIONES",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.4.sp
            )
            Spacer(Modifier.height(14.dp))

            samplePositions.forEach { sample ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Posición ${sample.number}",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = sample.time,
                        color = Color.White.copy(alpha = 0.5f),
                        fontSize = 12.sp
                    )
                }
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "${sample.coordinates} · enviado",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 12.sp
                )
                Spacer(Modifier.height(14.dp))
            }

            Spacer(Modifier.height(10.dp))
        }
    }
}

@Preview(
    name = "MapScreen",
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFF080B12
)
@Composable
fun MapScreenPreview() {
    SafeDrunkTheme(darkTheme = true, dynamicColor = false) {
        MapScreen()
    }
}

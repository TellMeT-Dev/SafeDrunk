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

@Composable
fun LockScreen(modifier: Modifier = Modifier) {
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
                text = "Bloqueo inteligente",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Configura el tiempo y el método para liberar tu dispositivo.",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(Modifier.height(28.dp))
            Text(
                text = "Duración del bloqueo",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("30m", "1h", "2h", "2.5h").forEach { option ->
                    val selected = option == "1h"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (selected) Color(0xFF00AEFF) else Color.White.copy(alpha = 0.08f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = option,
                            color = if (selected) Color.White else Color.White.copy(alpha = 0.7f),
                            fontSize = 14.sp,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }

            Spacer(Modifier.height(28.dp))
            Text(
                text = "MÉTODO DE DESBLOQUEO",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.4.sp
            )
            Spacer(Modifier.height(14.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Ejercicio",
                    color = Color(0xFF00AEFF),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Seleccionado",
                    color = Color(0xFF00AEFF),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Resuelve una operación matemática",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 12.sp
            )

            Spacer(Modifier.height(18.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Patrón",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Elegir",
                    color = Color.White.copy(alpha = 0.4f),
                    fontSize = 11.sp
                )
            }
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Dibuja tu patrón de seguridad",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 12.sp
            )

            Spacer(Modifier.height(28.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF00AEFF)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Activar bloqueo",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(24.dp))
            Text(
                text = "Ubicación compartida con María García",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 13.sp
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Contacto de confianza · +34 612 345 678",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 13.sp
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Preview(
    name = "LockScreen",
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFF080B12
)
@Composable
fun LockScreenPreview() {
    SafeDrunkTheme(darkTheme = true, dynamicColor = false) {
        LockScreen()
    }
}

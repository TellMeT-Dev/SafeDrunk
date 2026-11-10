package com.example.safedrunk.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safedrunk.ui.theme.SafeDrunkTheme

@Composable
fun ActiveLockScreen(modifier: Modifier = Modifier) {
    ScreenRoot(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            ActiveHeader()

            Spacer(Modifier.height(26.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CountdownRing()
                Spacer(Modifier.height(20.dp))
                LocationNotificationCard()
            }

            Spacer(Modifier.height(24.dp))
            SectionLabel("APPS RESTRINGIDAS")
            Spacer(Modifier.height(12.dp))

            restrictedApps.chunked(4).forEach { rowApps ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    rowApps.forEach { app ->
                        RestrictedAppTile(
                            app = app,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))
            }

            Spacer(Modifier.height(12.dp))
            ActionButton(
                text = "Resolver ejercicio para salir",
                symbol = "∑",
                containerColor = SafeLockColors.Primary,
                contentColor = Color.White,
                borderColor = SafeLockColors.PrimaryLight.copy(alpha = 0.55f),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(10.dp))
            ActionButton(
                text = "Llamada de emergencia",
                symbol = "!",
                containerColor = SafeLockColors.Red.copy(alpha = 0.10f),
                contentColor = SafeLockColors.Red,
                borderColor = SafeLockColors.Red.copy(alpha = 0.42f),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(10.dp))
            ActionButton(
                text = "Llamar a María García",
                symbol = "MG",
                containerColor = SafeLockColors.Green.copy(alpha = 0.10f),
                contentColor = SafeLockColors.Green,
                borderColor = SafeLockColors.Green.copy(alpha = 0.42f),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(28.dp))
        }
    }
}

@Composable
private fun ActiveHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SafeLockLogo(size = 38.dp)
        Spacer(Modifier.width(10.dp))
        Text(
            text = "SafeLock",
            color = SafeLockColors.TextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(SafeLockColors.Green.copy(alpha = 0.12f))
                .border(1.dp, SafeLockColors.Green.copy(alpha = 0.38f), RoundedCornerShape(50))
                .padding(horizontal = 11.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .clip(CircleShape)
                    .background(SafeLockColors.Green)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "ACTIVO",
                color = SafeLockColors.Green,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
private fun CountdownRing() {
    Box(
        modifier = Modifier.size(232.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(Modifier.fillMaxSize()) {
            val stroke = 11.dp.toPx()
            val diameter = size.minDimension
            val inset = stroke / 2f

            drawCircle(
                color = SafeLockColors.Primary.copy(alpha = 0.05f),
                radius = diameter / 2f - stroke * 1.2f,
                center = center,
                style = Stroke(stroke * 2.4f)
            )
            drawCircle(
                color = Color(0xFF172033),
                radius = diameter / 2f - inset,
                center = center,
                style = Stroke(stroke)
            )
            drawArc(
                color = SafeLockColors.Primary,
                startAngle = -90f,
                sweepAngle = 360f * 0.78f,
                useCenter = false,
                topLeft = Offset(inset, inset),
                size = Size(diameter - stroke, diameter - stroke),
                style = Stroke(width = stroke, cap = StrokeCap.Round)
            )
            drawArc(
                color = SafeLockColors.PrimaryLight.copy(alpha = 0.65f),
                startAngle = -90f,
                sweepAngle = 42f,
                useCenter = false,
                topLeft = Offset(inset, inset),
                size = Size(diameter - stroke, diameter - stroke),
                style = Stroke(width = stroke, cap = StrokeCap.Round)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "BLOQUEO",
                color = SafeLockColors.TextTertiary,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 2.sp
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "29:58",
                color = SafeLockColors.TextPrimary,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "restante",
                color = SafeLockColors.TextSecondary,
                fontSize = 13.sp
            )
        }
    }
}

@Composable
private fun LocationNotificationCard() {
    SectionCard(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconBubble(
                symbol = "◎",
                color = SafeLockColors.Green,
                size = 44.dp
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Próximo envío de ubicación",
                    color = SafeLockColors.TextSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(3.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "04:58",
                        color = SafeLockColors.Green,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "· María García",
                        color = SafeLockColors.TextPrimary,
                        fontSize = 12.sp
                    )
                }
            }
            SmallPill(
                text = "EN 5 MIN",
                color = SafeLockColors.PrimaryLight
            )
        }
    }
}

@Composable
private fun RestrictedAppTile(
    app: RestrictedApp,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(SafeLockColors.Card)
            .border(1.dp, SafeLockColors.Border, RoundedCornerShape(18.dp))
            .padding(vertical = 12.dp, horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(app.accent.copy(alpha = 0.16f))
                    .border(1.dp, app.accent.copy(alpha = 0.32f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = app.initials,
                    color = app.accent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 3.dp, y = (-3).dp)
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(SafeLockColors.Red)
                    .border(2.dp, SafeLockColors.Card, CircleShape)
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = app.name,
            color = SafeLockColors.TextPrimary,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

private data class RestrictedApp(
    val name: String,
    val initials: String,
    val accent: Color
)

private val restrictedApps = listOf(
    RestrictedApp("Instagram", "IG", SafeLockColors.Pink),
    RestrictedApp("TikTok", "TT", Color(0xFF25F4EE)),
    RestrictedApp("Twitter", "X", Color(0xFF60A5FA)),
    RestrictedApp("YouTube", "YT", SafeLockColors.Red),
    RestrictedApp("WhatsApp", "WA", Color(0xFF25D366)),
    RestrictedApp("Chrome", "C", Color(0xFF4285F4)),
    RestrictedApp("Spotify", "S", Color(0xFF1DB954)),
    RestrictedApp("Maps", "M", SafeLockColors.Orange)
)

@Preview(
    name = "ActiveLockScreen",
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFF080B12
)
@Composable
fun ActiveLockScreenPreview() {
    SafeDrunkTheme(darkTheme = true, dynamicColor = false) {
        ActiveLockScreen()
    }
}

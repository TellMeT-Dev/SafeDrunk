package com.example.safedrunk.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safedrunk.ui.theme.SafeDrunkTheme

@Composable
fun LockScreen(modifier: Modifier = Modifier) {
    ScreenRoot(modifier = modifier) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(14.dp))
            BrandRow()

            Spacer(Modifier.height(26.dp))
            Text(
                text = "Bloqueo inteligente",
                color = SafeLockColors.TextPrimary,
                fontSize = 30.sp,
                lineHeight = 35.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(7.dp))
            Text(
                text = "Configura el tiempo y el método para liberar tu dispositivo.",
                color = SafeLockColors.TextSecondary,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(Modifier.height(24.dp))
            DurationCard()

            Spacer(Modifier.height(24.dp))
            SectionLabel("MÉTODO DE DESBLOQUEO")
            Spacer(Modifier.height(11.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MethodCard(
                    title = "Ejercicio",
                    subtitle = "Resuelve una operación matemática",
                    selected = true,
                    exercise = true,
                    modifier = Modifier.weight(1f)
                )
                MethodCard(
                    title = "Patrón",
                    subtitle = "Dibuja tu patrón de seguridad",
                    selected = false,
                    exercise = false,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(26.dp))
            PrimaryButton(
                text = "Activar bloqueo",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoShortcut(
                    title = "Ubicación",
                    value = "Compartida con María García",
                    symbol = "◎",
                    accent = SafeLockColors.Green,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                InfoShortcut(
                    title = "Recorrido",
                    value = "Ver historial del mapa",
                    symbol = "↗",
                    accent = SafeLockColors.PrimaryLight,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
            }

            Spacer(Modifier.height(16.dp))
            TrustedContactCard()

            Spacer(Modifier.height(28.dp))
        }

        LockBottomBar()
    }
}

@Composable
private fun BrandRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SafeLockLogo(size = 42.dp)
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = "SafeLock",
                color = SafeLockColors.TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Protección personal",
                color = SafeLockColors.TextSecondary,
                fontSize = 11.sp
            )
        }
        SmallPill(
            text = "SEGURO",
            color = SafeLockColors.Green
        )
    }
}

@Composable
private fun DurationCard() {
    SectionCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Duración del bloqueo",
                    color = SafeLockColors.TextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = "Elige cuánto tiempo permanecerá activo",
                    color = SafeLockColors.TextSecondary,
                    fontSize = 12.sp
                )
            }
            SmallPill(
                text = "TEMPORAL",
                color = SafeLockColors.PrimaryLight
            )
        }

        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DurationChip("30m", selected = false, modifier = Modifier.weight(1f))
            DurationChip("1h", selected = true, modifier = Modifier.weight(1f))
            DurationChip("2h", selected = false, modifier = Modifier.weight(1f))
            DurationChip("2.5h", selected = false, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun DurationChip(
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(15.dp)
    Box(
        modifier = modifier
            .height(54.dp)
            .clip(shape)
            .background(if (selected) SafeLockColors.Primary else SafeLockColors.CardSoft)
            .border(
                1.dp,
                if (selected) SafeLockColors.Primary else SafeLockColors.Border,
                shape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = if (selected) Color.White else SafeLockColors.TextSecondary,
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Composable
private fun MethodCard(
    title: String,
    subtitle: String,
    selected: Boolean,
    exercise: Boolean,
    modifier: Modifier = Modifier
) {
    SectionCard(
        modifier = modifier,
        background = if (selected) SafeLockColors.Primary.copy(alpha = 0.10f) else SafeLockColors.Card,
        borderColor = if (selected) SafeLockColors.Primary.copy(alpha = 0.85f) else SafeLockColors.Border,
        padding = 16.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (exercise) {
                IconBubble(
                    symbol = "∑",
                    color = SafeLockColors.PrimaryLight,
                    size = 42.dp
                )
            } else {
                PatternBubble()
            }
            RadioDot(selected = selected)
        }
        Spacer(Modifier.height(14.dp))
        Text(
            text = title,
            color = SafeLockColors.TextPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(3.dp))
        Text(
            text = subtitle,
            color = SafeLockColors.TextSecondary,
            fontSize = 11.sp,
            lineHeight = 15.sp
        )
    }
}

@Composable
private fun PatternBubble() {
    Box(
        modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(SafeLockColors.Primary.copy(alpha = 0.14f))
            .border(1.dp, SafeLockColors.Primary.copy(alpha = 0.30f), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Canvas(Modifier.size(20.dp)) {
            val radius = size.minDimension * 0.07f
            val fractions = listOf(0.2f, 0.5f, 0.8f)
            fractions.forEach { x ->
                fractions.forEach { y ->
                    drawCircle(
                        color = SafeLockColors.PrimaryLight,
                        radius = radius,
                        center = Offset(size.width * x, size.height * y)
                    )
                }
            }
        }
    }
}

@Composable
private fun RadioDot(selected: Boolean) {
    Box(
        modifier = Modifier
            .size(22.dp)
            .clip(CircleShape)
            .border(
                1.5.dp,
                if (selected) SafeLockColors.Primary else SafeLockColors.Border,
                CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(SafeLockColors.Primary)
            )
        }
    }
}

@Composable
private fun InfoShortcut(
    title: String,
    value: String,
    symbol: String,
    accent: Color,
    modifier: Modifier = Modifier
) {
    SectionCard(
        modifier = modifier,
        padding = 14.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        IconBubble(
            symbol = symbol,
            color = accent,
            size = 38.dp
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = title,
            color = SafeLockColors.TextPrimary,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = value,
            color = SafeLockColors.TextSecondary,
            fontSize = 11.sp,
            lineHeight = 16.sp
        )
    }
}

@Composable
private fun TrustedContactCard() {
    SectionCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Avatar(
                initials = "MG",
                size = 52.dp,
                containerColor = SafeLockColors.Primary.copy(alpha = 0.16f),
                contentColor = SafeLockColors.PrimaryLight
            )
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                SectionLabel(text = "CONTACTO DE CONFIANZA")
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "María García",
                    color = SafeLockColors.TextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "+34 612 345 678",
                    color = SafeLockColors.TextSecondary,
                    fontSize = 12.sp
                )
            }
            Text(
                text = "›",
                color = SafeLockColors.TextSecondary,
                fontSize = 26.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
private fun LockBottomBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SafeLockColors.BackgroundRaised)
    ) {
        DividerLine(color = SafeLockColors.Border)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            NavItem(
                label = "Inicio",
                type = NavGlyphType.Home,
                selected = true,
                modifier = Modifier.weight(1f)
            )
            NavItem(
                label = "Mapa",
                type = NavGlyphType.Map,
                selected = false,
                modifier = Modifier.weight(1f)
            )
            NavItem(
                label = "Contacto",
                type = NavGlyphType.Contact,
                selected = false,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

private enum class NavGlyphType {
    Home,
    Map,
    Contact
}

@Composable
private fun RowScope.NavItem(
    label: String,
    type: NavGlyphType,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val tint = if (selected) SafeLockColors.PrimaryLight else SafeLockColors.TextSecondary
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (selected) SafeLockColors.Primary.copy(alpha = 0.12f) else Color.Transparent)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavGlyph(type = type, color = tint)
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            color = tint,
            fontSize = 11.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium
        )
    }
}

@Composable
private fun NavGlyph(type: NavGlyphType, color: Color) {
    Canvas(Modifier.size(22.dp)) {
        val stroke = 1.8.dp.toPx()
        when (type) {
            NavGlyphType.Home -> {
                val roof = Path().apply {
                    moveTo(size.width * 0.16f, size.height * 0.46f)
                    lineTo(size.width * 0.50f, size.height * 0.18f)
                    lineTo(size.width * 0.84f, size.height * 0.46f)
                }
                drawPath(roof, color = color, style = Stroke(stroke, cap = StrokeCap.Round))
                val outline = Path().apply {
                    moveTo(size.width * 0.25f, size.height * 0.42f)
                    lineTo(size.width * 0.25f, size.height * 0.82f)
                    lineTo(size.width * 0.75f, size.height * 0.82f)
                    lineTo(size.width * 0.75f, size.height * 0.42f)
                }
                drawPath(outline, color = color, style = Stroke(stroke, cap = StrokeCap.Round))
                drawLine(
                    color = color,
                    start = Offset(size.width * 0.43f, size.height * 0.82f),
                    end = Offset(size.width * 0.43f, size.height * 0.60f),
                    strokeWidth = stroke,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = color,
                    start = Offset(size.width * 0.43f, size.height * 0.60f),
                    end = Offset(size.width * 0.57f, size.height * 0.60f),
                    strokeWidth = stroke,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = color,
                    start = Offset(size.width * 0.57f, size.height * 0.60f),
                    end = Offset(size.width * 0.57f, size.height * 0.82f),
                    strokeWidth = stroke,
                    cap = StrokeCap.Round
                )
            }

            NavGlyphType.Map -> {
                drawCircle(
                    color = color,
                    radius = size.minDimension * 0.16f,
                    center = Offset(size.width * 0.5f, size.height * 0.36f),
                    style = Stroke(stroke)
                )
                drawLine(
                    color = color,
                    start = Offset(size.width * 0.36f, size.height * 0.48f),
                    end = Offset(size.width * 0.5f, size.height * 0.84f),
                    strokeWidth = stroke,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = color,
                    start = Offset(size.width * 0.64f, size.height * 0.48f),
                    end = Offset(size.width * 0.5f, size.height * 0.84f),
                    strokeWidth = stroke,
                    cap = StrokeCap.Round
                )
            }

            NavGlyphType.Contact -> {
                drawCircle(
                    color = color,
                    radius = size.minDimension * 0.14f,
                    center = Offset(size.width * 0.5f, size.height * 0.32f),
                    style = Stroke(stroke)
                )
                drawArc(
                    color = color,
                    startAngle = 200f,
                    sweepAngle = 140f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.18f, size.height * 0.44f),
                    size = Size(size.width * 0.64f, size.height * 0.52f),
                    style = Stroke(stroke, cap = StrokeCap.Round)
                )
            }
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

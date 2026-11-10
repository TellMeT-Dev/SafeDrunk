package com.example.safedrunk.view

import android.graphics.Paint
import android.graphics.Typeface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safedrunk.ui.theme.SafeDrunkTheme

@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    ScreenRoot(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            ScreenHeader(
                title = "Recorrido",
                subtitle = "Hoy, 14:30 - 14:55"
            )

            Spacer(Modifier.height(20.dp))
            RouteCard()

            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatisticCard(
                    label = "Distancia",
                    value = "2.4 km",
                    accent = SafeLockColors.PrimaryLight,
                    modifier = Modifier.weight(1f)
                )
                StatisticCard(
                    label = "Duración",
                    value = "25 min",
                    accent = SafeLockColors.Green,
                    modifier = Modifier.weight(1f)
                )
                StatisticCard(
                    label = "Puntos",
                    value = "6",
                    accent = SafeLockColors.Orange,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(24.dp))
            SectionLabel("REGISTRO DE POSICIONES")
            Spacer(Modifier.height(12.dp))

            samplePositions.forEach { sample ->
                PositionCard(sample = sample)
                Spacer(Modifier.height(10.dp))
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun RouteCard() {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        padding = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Ruta del recorrido",
                    color = SafeLockColors.TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = "Trayecto registrado sin incidentes",
                    color = SafeLockColors.TextSecondary,
                    fontSize = 11.sp
                )
            }
            SmallPill(
                text = "COMPLETADO",
                color = SafeLockColors.Green
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(228.dp)
        ) {
            Canvas(Modifier.fillMaxSize()) {
                val gridColor = Color.White.copy(alpha = 0.055f)
                val columns = 6
                val rows = 4
                for (index in 0..columns) {
                    val x = size.width * index / columns
                    drawLine(
                        color = gridColor,
                        start = Offset(x, 0f),
                        end = Offset(x, size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }
                for (index in 0..rows) {
                    val y = size.height * index / rows
                    drawLine(
                        color = gridColor,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = 1.dp.toPx()
                    )
                }

                val fractions = listOf(
                    0.12f to 0.80f,
                    0.28f to 0.64f,
                    0.40f to 0.71f,
                    0.55f to 0.47f,
                    0.72f to 0.53f,
                    0.87f to 0.26f
                )
                val points = fractions.map { (x, y) ->
                    Offset(size.width * x, size.height * y)
                }

                val routePath = Path().apply {
                    moveTo(points.first().x, points.first().y)
                    points.drop(1).forEach { point ->
                        lineTo(point.x, point.y)
                    }
                }

                drawPath(
                    path = routePath,
                    color = SafeLockColors.Primary.copy(alpha = 0.18f),
                    style = Stroke(
                        width = 16.dp.toPx(),
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
                drawPath(
                    path = routePath,
                    color = SafeLockColors.Primary,
                    style = Stroke(
                        width = 4.dp.toPx(),
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )

                val timeLabels = listOf("14:30", "14:35", "14:40", "14:45", "14:50", "14:55")
                val labelPaint = Paint().apply {
                    isAntiAlias = true
                    textSize = 9.sp.toPx()
                    textAlign = Paint.Align.CENTER
                    typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                }
                val labelDistance = 14.dp.toPx()

                points.forEachIndexed { index, point ->
                    when (index) {
                        0 -> {
                            drawCircle(
                                color = SafeLockColors.Green.copy(alpha = 0.20f),
                                radius = 13.dp.toPx(),
                                center = point
                            )
                            drawCircle(
                                color = SafeLockColors.Green,
                                radius = 7.dp.toPx(),
                                center = point
                            )
                            drawCircle(
                                color = SafeLockColors.Background,
                                radius = 2.5.dp.toPx(),
                                center = point
                            )
                            labelPaint.color = SafeLockColors.Green.toArgb()
                        }

                        points.lastIndex -> {
                            drawCircle(
                                color = SafeLockColors.Orange.copy(alpha = 0.20f),
                                radius = 13.dp.toPx(),
                                center = point
                            )
                            drawCircle(
                                color = SafeLockColors.Orange,
                                radius = 7.dp.toPx(),
                                center = point
                            )
                            drawCircle(
                                color = SafeLockColors.Background,
                                radius = 2.5.dp.toPx(),
                                center = point
                            )
                            labelPaint.color = SafeLockColors.Orange.toArgb()
                        }

                        else -> {
                            drawCircle(
                                color = SafeLockColors.CardSoft,
                                radius = 7.dp.toPx(),
                                center = point
                            )
                            drawCircle(
                                color = SafeLockColors.PrimaryLight,
                                radius = 7.dp.toPx(),
                                center = point,
                                style = Stroke(width = 2.dp.toPx())
                            )
                            drawCircle(
                                color = SafeLockColors.PrimaryLight,
                                radius = 2.5.dp.toPx(),
                                center = point
                            )
                            labelPaint.color = SafeLockColors.TextSecondary.toArgb()
                        }
                    }

                    drawContext.canvas.nativeCanvas.drawText(
                        timeLabels[index],
                        point.x,
                        point.y - labelDistance,
                        labelPaint
                    )
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(14.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SafeLockColors.Background.copy(alpha = 0.80f))
                    .border(1.dp, SafeLockColors.Border, RoundedCornerShape(12.dp))
                    .padding(horizontal = 10.dp, vertical = 7.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LegendItem(label = "Inicio", color = SafeLockColors.Green)
                LegendItem(label = "Fin", color = SafeLockColors.Orange)
            }

            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(14.dp)
                    .clip(RoundedCornerShape(50))
                    .background(SafeLockColors.Background.copy(alpha = 0.72f))
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(SafeLockColors.PrimaryLight)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "RUTA GPS",
                    color = SafeLockColors.PrimaryLight,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.8.sp
                )
            }
        }
    }
}

@Composable
private fun LegendItem(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(7.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = label,
            color = SafeLockColors.TextSecondary,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun PositionCard(sample: PositionSample) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        padding = 14.dp,
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(SafeLockColors.Primary.copy(alpha = 0.12f))
                    .border(1.dp, SafeLockColors.Primary.copy(alpha = 0.30f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = sample.number.toString(),
                    color = SafeLockColors.PrimaryLight,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Posición ${sample.number}",
                        color = SafeLockColors.TextPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = sample.time,
                        color = SafeLockColors.TextSecondary,
                        fontSize = 11.sp
                    )
                }
                Spacer(Modifier.height(3.dp))
                Text(
                    text = sample.coordinates,
                    color = SafeLockColors.TextSecondary,
                    fontSize = 12.sp,
                    letterSpacing = 0.3.sp
                )
                Spacer(Modifier.height(7.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(SafeLockColors.Green)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "Ubicación enviada a María García",
                        color = SafeLockColors.Green,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

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

package com.example.safedrunk.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safedrunk.ui.theme.SafeDrunkTheme

@Composable
fun UnlockScreen(modifier: Modifier = Modifier) {
    ScreenRoot(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            ScreenHeader(
                title = "Ejercicio matemático",
                subtitle = "Confirma que estás en condiciones de conducir"
            )

            Spacer(Modifier.height(22.dp))
            ExerciseCard()

            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SectionLabel("TECLADO NUMÉRICO")
                Spacer(Modifier.weight(1f))
                Text(
                    text = "3 dígitos",
                    color = SafeLockColors.TextTertiary,
                    fontSize = 10.sp
                )
            }
            Spacer(Modifier.height(12.dp))

            keypadRows.forEach { rowKeys ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    rowKeys.forEach { key ->
                        KeypadButton(
                            key = key,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))
            }

            Spacer(Modifier.height(8.dp))
            Text(
                text = "Las llamadas de emergencia siguen disponibles",
                modifier = Modifier.fillMaxWidth(),
                color = SafeLockColors.TextSecondary,
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ExerciseCard() {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        background = SafeLockColors.Card,
        padding = 22.dp,
        shape = RoundedCornerShape(24.dp)
    ) {
        Text(
            text = "RESUELVE PARA DESBLOQUEAR",
            modifier = Modifier.fillMaxWidth(),
            color = SafeLockColors.PrimaryLight,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.4.sp,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(20.dp))
        Text(
            text = "50 + 16",
            modifier = Modifier.fillMaxWidth(),
            color = SafeLockColors.TextPrimary,
            fontSize = 48.sp,
            lineHeight = 54.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(14.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(14.dp))
                .background(SafeLockColors.CardSoft)
                .border(1.dp, SafeLockColors.Border, RoundedCornerShape(14.dp))
                .padding(horizontal = 24.dp, vertical = 9.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "= ?",
                color = SafeLockColors.PrimaryLight,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(20.dp))
        DividerLine()
        Spacer(Modifier.height(16.dp))

        Text(
            text = "Tu respuesta",
            modifier = Modifier.fillMaxWidth(),
            color = SafeLockColors.TextSecondary,
            fontSize = 11.sp,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            repeat(3) {
                AnswerSlot(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun AnswerSlot(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(52.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(SafeLockColors.Background)
            .border(
                1.dp,
                SafeLockColors.Primary.copy(alpha = 0.30f),
                RoundedCornerShape(14.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "—",
            color = SafeLockColors.TextTertiary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
private fun KeypadButton(
    key: KeypadKey,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(18.dp)
    val background = when (key.kind) {
        KeypadKind.Number -> SafeLockColors.Card
        KeypadKind.Delete -> SafeLockColors.Red.copy(alpha = 0.10f)
        KeypadKind.Confirm -> SafeLockColors.Primary
    }
    val borderColor = when (key.kind) {
        KeypadKind.Number -> SafeLockColors.Border
        KeypadKind.Delete -> SafeLockColors.Red.copy(alpha = 0.38f)
        KeypadKind.Confirm -> SafeLockColors.PrimaryLight.copy(alpha = 0.60f)
    }
    val contentColor = when (key.kind) {
        KeypadKind.Number -> SafeLockColors.TextPrimary
        KeypadKind.Delete -> SafeLockColors.Red
        KeypadKind.Confirm -> Color.White
    }

    Box(
        modifier = modifier
            .height(58.dp)
            .clip(shape)
            .then(
                if (key.kind == KeypadKind.Confirm) {
                    Modifier.background(
                        Brush.linearGradient(
                            listOf(SafeLockColors.Primary, Color(0xFF4C8BFF))
                        )
                    )
                } else {
                    Modifier.background(background)
                }
            )
            .border(1.dp, borderColor, shape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = key.label,
            color = contentColor,
            fontSize = when (key.kind) {
                KeypadKind.Number -> 20.sp
                KeypadKind.Delete -> 12.sp
                KeypadKind.Confirm -> 22.sp
            },
            fontWeight = if (key.kind == KeypadKind.Number) FontWeight.Medium else FontWeight.Bold
        )
    }
}

private enum class KeypadKind {
    Number,
    Delete,
    Confirm
}

private data class KeypadKey(
    val label: String,
    val kind: KeypadKind = KeypadKind.Number
)

private val keypadRows = listOf(
    listOf(
        KeypadKey("1"),
        KeypadKey("2"),
        KeypadKey("3")
    ),
    listOf(
        KeypadKey("4"),
        KeypadKey("5"),
        KeypadKey("6")
    ),
    listOf(
        KeypadKey("7"),
        KeypadKey("8"),
        KeypadKey("9")
    ),
    listOf(
        KeypadKey("DEL", KeypadKind.Delete),
        KeypadKey("0"),
        KeypadKey("✓", KeypadKind.Confirm)
    )
)

@Preview(
    name = "UnlockScreen",
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFF080B12
)
@Composable
fun UnlockScreenPreview() {
    SafeDrunkTheme(darkTheme = true, dynamicColor = false) {
        UnlockScreen()
    }
}

package com.example.safedrunk.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safedrunk.ui.theme.SafeDrunkTheme

@Composable
fun ContactsScreen(modifier: Modifier = Modifier) {
    ScreenRoot(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            ScreenHeader(
                title = "Contactos",
                subtitle = "Selecciona el contacto de confianza"
            )

            Spacer(Modifier.height(22.dp))
            CurrentContactCard()

            Spacer(Modifier.height(22.dp))
            SectionLabel("TODOS LOS CONTACTOS")
            Spacer(Modifier.height(11.dp))

            sampleContacts.forEach { contact ->
                ContactItem(contact = contact)
                Spacer(Modifier.height(10.dp))
            }

            Spacer(Modifier.height(26.dp))
        }
    }
}

@Composable
private fun CurrentContactCard() {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        background = SafeLockColors.Primary.copy(alpha = 0.10f),
        borderColor = SafeLockColors.Primary.copy(alpha = 0.80f),
        padding = 18.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconBubble(
                symbol = "✓",
                color = SafeLockColors.PrimaryLight,
                size = 44.dp
            )
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                SectionLabel(
                    text = "CONTACTO ACTUAL",
                    color = SafeLockColors.PrimaryLight
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "María García",
                    color = SafeLockColors.TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = "Recibirá tu ubicación durante el bloqueo",
                    color = SafeLockColors.TextSecondary,
                    fontSize = 11.sp,
                    lineHeight = 15.sp
                )
            }
            Avatar(
                initials = "MG",
                size = 46.dp,
                containerColor = SafeLockColors.Primary.copy(alpha = 0.20f),
                contentColor = SafeLockColors.PrimaryLight
            )
        }
    }
}

@Composable
private fun ContactItem(contact: TrustedContact) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        background = if (contact.selected) {
            SafeLockColors.Primary.copy(alpha = 0.08f)
        } else {
            SafeLockColors.Card
        },
        borderColor = if (contact.selected) {
            SafeLockColors.Primary
        } else {
            SafeLockColors.Border
        },
        padding = 14.dp,
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Avatar(
                initials = contact.initials,
                size = 48.dp,
                containerColor = contact.accent.copy(alpha = 0.16f),
                contentColor = contact.accent
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = contact.name,
                    color = SafeLockColors.TextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = contact.phone,
                    color = SafeLockColors.TextSecondary,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }
            Spacer(Modifier.width(8.dp))
            if (contact.selected) {
                SmallPill(
                    text = "Actual",
                    color = SafeLockColors.Primary,
                    filled = true
                )
            } else {
                SmallPill(
                    text = "Cambiar contacto",
                    color = SafeLockColors.PrimaryLight
                )
            }
        }
    }
}

private data class TrustedContact(
    val name: String,
    val initials: String,
    val phone: String,
    val accent: Color,
    val selected: Boolean = false
)

private val sampleContacts = listOf(
    TrustedContact(
        name = "María García",
        initials = "MG",
        phone = "+34 612 345 678",
        accent = SafeLockColors.PrimaryLight,
        selected = true
    ),
    TrustedContact(
        name = "Carlos López",
        initials = "CL",
        phone = "+34 623 456 789",
        accent = SafeLockColors.Green
    ),
    TrustedContact(
        name = "Ana Martínez",
        initials = "AM",
        phone = "+34 634 567 890",
        accent = SafeLockColors.Orange
    ),
    TrustedContact(
        name = "Roberto Sánchez",
        initials = "RS",
        phone = "+34 645 678 901",
        accent = SafeLockColors.Purple
    ),
    TrustedContact(
        name = "Laura Hernández",
        initials = "LH",
        phone = "+34 656 789 012",
        accent = SafeLockColors.Pink
    ),
    TrustedContact(
        name = "Diego Ramírez",
        initials = "DR",
        phone = "+34 667 890 123",
        accent = SafeLockColors.Teal
    ),
    TrustedContact(
        name = "Sofía Torres",
        initials = "ST",
        phone = "+34 678 901 234",
        accent = SafeLockColors.PrimaryLight
    )
)

@Preview(
    name = "ContactsScreen",
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFF080B12
)
@Composable
fun ContactsScreenPreview() {
    SafeDrunkTheme(darkTheme = true, dynamicColor = false) {
        ContactsScreen()
    }
}

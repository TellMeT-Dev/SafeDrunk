package com.example.safedrunk.view

import androidx.compose.foundation.background
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

private data class TrustedContact(
    val name: String,
    val phone: String,
    val selected: Boolean = false
)

private val sampleContacts = listOf(
    TrustedContact("María García", "+34 612 345 678", selected = true),
    TrustedContact("Carlos López", "+34 623 456 789"),
    TrustedContact("Ana Martínez", "+34 634 567 890"),
    TrustedContact("Roberto Sánchez", "+34 645 678 901"),
    TrustedContact("Laura Hernández", "+34 656 789 012"),
    TrustedContact("Diego Ramírez", "+34 667 890 123"),
    TrustedContact("Sofía Torres", "+34 678 901 234")
)

@Composable
fun ContactsScreen(modifier: Modifier = Modifier) {
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
                text = "Contactos",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Selecciona el contacto de confianza",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 14.sp
            )

            Spacer(Modifier.height(28.dp))
            Text(
                text = "CONTACTO ACTUAL",
                color = Color(0xFF00AEFF),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.4.sp
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "María García",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(3.dp))
            Text(
                text = "Recibirá tu ubicación durante el bloqueo",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 13.sp
            )

            Spacer(Modifier.height(28.dp))
            Text(
                text = "TODOS LOS CONTACTOS",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.4.sp
            )
            Spacer(Modifier.height(14.dp))

            sampleContacts.forEach { contact ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = contact.name,
                            color = if (contact.selected) Color(0xFF00AEFF) else Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = contact.phone,
                            color = Color.White.copy(alpha = 0.5f),
                            fontSize = 12.sp,
                            maxLines = 1
                        )
                    }
                    Text(
                        text = if (contact.selected) "Actual" else "Cambiar",
                        color = if (contact.selected) Color(0xFF00AEFF) else Color.White.copy(alpha = 0.4f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
        }
    }
}

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

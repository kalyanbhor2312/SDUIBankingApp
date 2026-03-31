package com.kb.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kb.designsystem.ui.theme.ArchitectPrimary
import com.kb.designsystem.ui.theme.ArchitectSurface
import com.kb.designsystem.ui.theme.ArchitectTeal
import com.kb.designsystem.ui.theme.ArchitectTextSecondary

@Composable
fun AccountCard(
    title: String,
    balance: String,
    subText: String? = null,
    modifier: Modifier = Modifier,
    manageAction: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = ArchitectSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    if (subText != null) {
                        Text(text = subText, style = MaterialTheme.typography.bodySmall, color = ArchitectTextSecondary)
                    }
                }
                Box(modifier = Modifier.size(40.dp).background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp)))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = balance, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

            if (manageAction != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = manageAction,
                    modifier = Modifier.fillMaxWidth().height(40.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ArchitectTeal, contentColor = ArchitectPrimary)
                ) {
                    Text("Manage", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

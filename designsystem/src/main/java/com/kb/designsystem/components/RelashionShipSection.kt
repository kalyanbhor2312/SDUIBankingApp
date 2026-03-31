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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kb.designsystem.ui.theme.ArchitectTextPrimary
import com.kb.designsystem.ui.theme.ArchitectTextSecondary


data class RelationshipModel(
    val title: String,
    val amount: String,
    val subtitle: String,
    val maskedNumber: String,
    val icon: String
)
@Composable
fun RelationshipsSection(
    title: String,
    actionText: String,
    items: List<RelationshipModel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = ArchitectTextPrimary,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = actionText,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF6C83F5),
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
        items.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = item.title.uppercase(),
                            style = MaterialTheme.typography.labelMedium,
                            color = ArchitectTextSecondary,
                            letterSpacing = 1.5.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = item.amount,
                            style = MaterialTheme.typography.headlineMedium,
                            color = ArchitectTextPrimary,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "${item.subtitle} • • • • ${item.maskedNumber}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = ArchitectTextSecondary
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF0F3F7)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = getRelationshipIcon(item.icon),
                            contentDescription = null,
                            tint = if (item.icon == "piggy") Color(0xFFD6EDED) else Color(0xFFE2E4E8),
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            }
        }
    }
}
private fun getRelationshipIcon(name: String): ImageVector {
    return when (name) {
        "wallet" -> Icons.Default.AccountBalanceWallet
        "piggy" -> Icons.Default.Savings
        else -> Icons.Default.AccountBalanceWallet
    }
}

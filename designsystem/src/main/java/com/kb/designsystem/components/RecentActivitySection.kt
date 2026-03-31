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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShowChart
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
import com.kb.designsystem.ui.theme.ArchitectPrimary
import com.kb.designsystem.ui.theme.ArchitectTextPrimary
import com.kb.designsystem.ui.theme.ArchitectTextSecondary

data class ActivityModel(
    val icon: String,
    val title: String,
    val accountName: String,
    val category: String,
    val date: String,
    val amount: String,
    val isPositive: Boolean
)
@Composable
fun RecentActivitySection(
    title: String,
    subtitle: String,
    actionText: String,
    items: List<ActivityModel>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F6F8)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = ArchitectPrimary,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyLarge,
                        color = ArchitectTextSecondary
                    )
                }
                Text(
                    text = actionText,
                    style = MaterialTheme.typography.titleMedium,
                    color = ArchitectPrimary,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(14.dp))
            items.forEach { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(activityIconBackground(item.icon)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = getActivityIcon(item.icon),
                                contentDescription = null,
                                tint = if (item.icon == "market") Color.White else ArchitectPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = ArchitectTextPrimary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${item.accountName.uppercase()} • ${item.category.uppercase()} • ${item.date.uppercase()}",
                                style = MaterialTheme.typography.labelMedium,
                                color = ArchitectTextSecondary,
                                lineHeight = 16.sp
                            )
                        }
                        Text(
                            text = item.amount,
                            style = MaterialTheme.typography.headlineSmall,
                            color = if (item.isPositive) Color(0xFF58B966) else ArchitectTextPrimary,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
    }
}private fun getActivityIcon(name: String): ImageVector {
    return when (name) {
        "shopping" -> Icons.Default.ShoppingBag
        "market" -> Icons.Default.ShowChart
        "deposit" -> Icons.Default.Payments
        "dining" -> Icons.Default.Restaurant
        "service" -> Icons.Default.ReceiptLong
        "transport" -> Icons.Default.DirectionsCar
        else -> Icons.Default.Payments
    }
}

private fun activityIconBackground(name: String): Color {
    return when (name) {
        "market" -> Color(0xFF0A5A1B)
        "deposit" -> Color(0xFF0A5A1B)
        else -> Color(0xFFF0F2F5)
    }
}


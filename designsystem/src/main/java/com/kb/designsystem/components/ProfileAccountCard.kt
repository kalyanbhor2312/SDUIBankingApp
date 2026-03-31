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
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import com.kb.designsystem.ui.theme.ArchitectTeal
import com.kb.designsystem.ui.theme.ArchitectTextSecondary

@Composable
fun PortfolioAccountCard(
    icon: String,
    maskedNumber: String,
    title: String,
    subtitle: String,
    primaryMetricLabel: String,
    primaryMetricValue: String,
    secondaryMetricLabel: String,
    secondaryMetricValue: String,
    actionText: String,
    badgeText: String? = null,
    footnotes: List<Pair<String, String>> = emptyList(),
    variant: String = "LIGHT",
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isDark = variant == "DARK"
    val background = if (isDark) ArchitectPrimary else Color.White
    val titleColor = if (isDark) Color.White else ArchitectPrimary
    val subtitleColor = if (isDark) Color(0xFF8D9BB7) else ArchitectTextSecondary
    val labelColor = if (isDark) Color(0xFF7D8BA7) else Color(0xFF8F96A3)
    val dividerColor = if (isDark) Color(0xFF21345F) else Color(0xFFE7EBF0)
    val actionBg = if (isDark) Color.White else ArchitectTeal

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = background),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isDark) 0.dp else 2.dp)
    ) {
        Column(modifier = Modifier.padding(28.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isDark) Color(0xFF2B3D67) else Color(0xFF0E8A8A)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = getAccountCardIcon(icon),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (badgeText != null) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF0A5A1B)
                        ) {
                            Text(
                                text = badgeText,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                color = Color(0xFF90E58D),
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    Text(
                        text = "**** $maskedNumber",
                        style = MaterialTheme.typography.labelMedium,
                        color = labelColor,
                        letterSpacing = 2.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = titleColor,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyLarge,
                color = subtitleColor
            )

            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = primaryMetricLabel.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = labelColor,
                letterSpacing = 1.5.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = primaryMetricValue,
                style = MaterialTheme.typography.headlineLarge,
                color = titleColor,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(18.dp))
            HorizontalDivider(color = dividerColor)
            Spacer(modifier = Modifier.height(18.dp))

            if (footnotes.isEmpty()) {
                Text(
                    text = secondaryMetricLabel.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = labelColor,
                    letterSpacing = 1.5.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = secondaryMetricValue,
                    style = MaterialTheme.typography.headlineSmall,
                    color = if (secondaryMetricValue.startsWith("+")) Color(0xFF58B966) else titleColor,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Row(modifier = Modifier.fillMaxWidth()) {
                    footnotes.forEachIndexed { index, footnote ->
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = footnote.first.uppercase(),
                                style = MaterialTheme.typography.labelMedium,
                                color = labelColor,
                                letterSpacing = 1.5.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    text = footnote.second.substringBefore(" "),
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = titleColor,
                                    fontWeight = FontWeight.Bold
                                )
                                footnote.second.substringAfter(" ", "").takeIf { it.isNotBlank() }?.let { suffix ->
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = suffix,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = subtitleColor
                                    )
                                }
                            }
                        }
                        if (index == 0 && footnotes.size > 1) {
                            Spacer(modifier = Modifier.width(20.dp))
                            VerticalDivider(
                                modifier = Modifier.height(44.dp),
                                color = dividerColor,
                                thickness = 1.dp
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onActionClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = actionBg,
                    contentColor = ArchitectPrimary
                )
            ) {
                Text(
                    text = "$actionText →",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
private fun getAccountCardIcon(name: String): ImageVector {
    return when (name) {
        "bank" -> Icons.Default.AccountBalance
        "savings" -> Icons.Default.Savings
        "wallet" -> Icons.Default.AccountBalanceWallet
        else -> Icons.Default.AccountBalanceWallet
    }
}
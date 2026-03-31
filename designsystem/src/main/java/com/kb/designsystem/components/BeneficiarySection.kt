package com.kb.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kb.designsystem.ui.theme.ArchitectPrimary
import com.kb.designsystem.ui.theme.ArchitectTeal
import com.kb.designsystem.ui.theme.ArchitectTextPrimary
import com.kb.designsystem.ui.theme.ArchitectTextSecondary

data class BeneficiaryModel(
    val id: String,
    val name: String,
    val subtitle: String,
    val initials: String,
    val isSelected: Boolean
)

@Composable
fun BeneficiarySection(
    title: String,
    actionText: String,
    items: List<BeneficiaryModel>,
    addNewLabel: String,
    onItemClick: (String) -> Unit,
    onAddNew: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 8.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = ArchitectTextPrimary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = actionText.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = ArchitectPrimary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.8.sp
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        val cards = items + BeneficiaryModel(
            id = "__new",
            name = addNewLabel,
            subtitle = "",
            initials = "+",
            isSelected = false
        )

        cards.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { item ->
                    val isAddNew = item.id == "__new"
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .clickable {
                                if (isAddNew) onAddNew() else onItemClick(item.id)
                            },
                        shape = RoundedCornerShape(14.dp),
                        border = if (isAddNew) BorderStroke(1.dp, Color(0xFFC7CCD4)) else null,
                        colors = CardDefaults.cardColors(
                            containerColor = if (item.isSelected) ArchitectPrimary else Color.White
                        )
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (isAddNew) Color(0xFFE9ECF1) else Color(
                                            0xFF37414E
                                        )
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = item.initials,
                                    color = if (item.isSelected) ArchitectTeal else Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (item.isSelected) Color.White else ArchitectTextPrimary
                            )
                            if (!isAddNew) {
                                Text(
                                    text = item.subtitle.uppercase(),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (item.isSelected) Color(0xFFB8C3D5) else ArchitectTextSecondary
                                )
                            }
                        }
                    }
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
package com.kb.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PromoBanner(
    overline: String,
    title: String,
    actionText: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF1B143A), Color(0xFF2F2A75), Color(0xFF291246))
                    )
                )
                .padding(18.dp)
        ) {
            Canvas(
                modifier = Modifier.matchParentSize()
            ) {
                drawCircle(
                    color = Color(0x335986FF),
                    radius = size.minDimension * 0.58f,
                    center = Offset(size.width * 0.75f, size.height * 0.3f)
                )
                drawCircle(
                    color = Color(0x223D8BFF),
                    radius = size.minDimension * 0.42f,
                    center = Offset(size.width * 0.55f, size.height * 0.65f)
                )
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = overline.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFFD6DAF9),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = "$actionText →",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFFD6DAF9),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

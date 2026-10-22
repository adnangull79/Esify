package com.example.esify.ui.onboarding.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.ui.theme.DarkForestGreen
import com.example.esify.ui.theme.LeafGreen
import com.example.esify.ui.theme.LightBackground
import com.example.esify.ui.theme.LightMint
import com.example.esify.ui.theme.LightOrange
import com.example.esify.ui.theme.MidGreen
import com.example.esify.ui.theme.OrangeAccent
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.TrophyOrange
import com.example.esify.ui.theme.WhiteCards

@Composable
fun OnboardingIllustration(
    pageNumber: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.Center
    ) {
        when (pageNumber) {
            1 -> PageOneIllustration()
            2 -> PageTwoIllustration()
            3 -> PageThreeIllustration()
            else -> PageOneIllustration()
        }
    }
}

@Composable
private fun PageOneIllustration() {
    Box(
        modifier = Modifier.size(170.dp),
        contentAlignment = Alignment.Center
    ) {
        // Outer soft background glow
        Box(
            modifier = Modifier
                .size(140.dp)
                .background(LightMint, shape = CircleShape)
        )

        // Center card with Trophy and graduation cap
        Box(
            modifier = Modifier
                .size(96.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(DarkForestGreen, MidGreen)
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.EmojiEvents,
                contentDescription = null,
                tint = TrophyOrange,
                modifier = Modifier.size(52.dp)
            )
        }

        // Floating Top-Right Badge: "Class 9 & 10"
        Card(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 4.dp, end = 2.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = WhiteCards),
            border = BorderStroke(1.dp, OutlineDivider),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.School,
                    contentDescription = null,
                    tint = PrimaryGreen,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Class 9 & 10",
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                    fontWeight = FontWeight.Bold,
                    color = PrimaryGreen
                )
            }
        }

        // Floating Bottom-Left Badge: "Grade A+"
        Card(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 6.dp, start = 4.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = WhiteCards),
            border = BorderStroke(1.dp, OutlineDivider),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = TrophyOrange,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "A+ Target",
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                    fontWeight = FontWeight.Bold,
                    color = OrangeAccent
                )
            }
        }
    }
}

@Composable
private fun PageTwoIllustration() {
    Box(
        modifier = Modifier.size(170.dp),
        contentAlignment = Alignment.Center
    ) {
        // Outer soft background glow
        Box(
            modifier = Modifier
                .size(140.dp)
                .background(LightMint, shape = CircleShape)
        )

        // Center card with Open Book and Notes
        Card(
            modifier = Modifier.size(105.dp),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = WhiteCards),
            border = BorderStroke(1.5.dp, PrimaryGreen.copy(alpha = 0.35f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .background(LightMint, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.MenuBook,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Simulated text note lines
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(4.dp)
                        .background(OutlineDivider, RoundedCornerShape(2.dp))
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(4.dp)
                        .background(LightMint, RoundedCornerShape(2.dp))
                )
            }
        }

        // Floating Top-Right Badge: "Solved Notes"
        Card(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 2.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = WhiteCards),
            border = BorderStroke(1.dp, OutlineDivider),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = LeafGreen,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Solved Qs",
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                    fontWeight = FontWeight.Bold,
                    color = PrimaryGreen
                )
            }
        }

        // Floating Bottom-Left Badge: "Past Papers"
        Card(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 6.dp, start = 4.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = LightOrange),
            border = BorderStroke(1.dp, OrangeAccent.copy(alpha = 0.3f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Text(
                text = "5-Year Papers",
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                fontWeight = FontWeight.Bold,
                color = OrangeAccent,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
            )
        }
    }
}

@Composable
private fun PageThreeIllustration() {
    Box(
        modifier = Modifier.size(170.dp),
        contentAlignment = Alignment.Center
    ) {
        // Outer soft background glow
        Box(
            modifier = Modifier
                .size(140.dp)
                .background(LightMint, shape = CircleShape)
        )

        // Center Phone / MCQ Mockup
        Card(
            modifier = Modifier
                .width(108.dp)
                .height(138.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = WhiteCards),
            border = BorderStroke(2.dp, MidGreen),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Speaker slit
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height(3.dp)
                        .background(OutlineDivider, RoundedCornerShape(1.5.dp))
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Quiz Question header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Quiz,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "MCQ 01",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Option 1: Selected (Green + Checkmark)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(LightMint)
                        .border(BorderStroke(1.dp, PrimaryGreen), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "A. Correct",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                            fontWeight = FontWeight.Bold,
                            color = PrimaryGreen,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            tint = PrimaryGreen,
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Option 2: Neutral
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(LightBackground)
                        .border(BorderStroke(1.dp, OutlineDivider), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "B. Option",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                        color = SecondaryText
                    )
                }
            }
        }

        // Floating Top-Right Badge: "100% Score"
        Card(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 4.dp, end = 2.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = WhiteCards),
            border = BorderStroke(1.dp, OutlineDivider),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = TrophyOrange,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = "100%",
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                    fontWeight = FontWeight.Bold,
                    color = PrimaryGreen
                )
            }
        }

        // Floating Bottom-Left Badge: "Instant Result"
        Card(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 6.dp, start = 2.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = LightMint),
            border = BorderStroke(1.dp, PrimaryGreen.copy(alpha = 0.3f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Text(
                text = "Instant Feedback",
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

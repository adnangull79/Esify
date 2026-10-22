package com.example.esify.ui.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.ui.profile.model.DefaultProfileStats
import com.example.esify.ui.profile.model.ProfileStatItem
import com.example.esify.ui.theme.LightMint
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.WhiteCards

@Composable
fun StudentProfileCard(
    modifier: Modifier = Modifier,
    displayName: String = "Student",
    email: String = "student@esify.com",
    classAndBoardText: String = "Class 9 • Board not selected",
    stats: List<ProfileStatItem> = DefaultProfileStats,
    onEditProfileClick: () -> Unit = {},
    onClassAndBoardClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = WhiteCards),
        border = BorderStroke(1.dp, OutlineDivider),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // 1. Profile information row: Avatar, Name, Email, Edit Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Student Avatar with Camera affordance
                Box(
                    modifier = Modifier.size(60.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(LightMint, shape = CircleShape)
                            .border(BorderStroke(1.5.dp, PrimaryGreen.copy(alpha = 0.25f)), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Student Avatar",
                            tint = PrimaryGreen,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    // Camera affordance badge
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .background(PrimaryGreen, shape = CircleShape)
                            .border(BorderStroke(1.5.dp, WhiteCards), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CameraAlt,
                            contentDescription = "Edit Avatar",
                            tint = Color.White,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = displayName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = email,
                        style = MaterialTheme.typography.bodySmall,
                        color = SecondaryText
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Edit Profile Button
                OutlinedButton(
                    onClick = onEditProfileClick,
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, PrimaryGreen),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryGreen)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit Profile",
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Edit Profile",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 2. Class and board row
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LightMint, shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(),
                        onClick = onClassAndBoardClick
                    )
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.School,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = classAndBoardText,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Select Class and Board",
                        tint = SecondaryText,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 3. Four Student-Statistic Tiles
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                stats.forEach { stat ->
                    StatTile(
                        stat = stat,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun StatTile(
    stat: ProfileStatItem,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = stat.backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(vertical = 10.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = stat.icon,
                contentDescription = null,
                tint = stat.iconTint,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stat.value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = stat.label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp
                ),
                color = SecondaryText,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

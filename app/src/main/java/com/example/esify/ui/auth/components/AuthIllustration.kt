package com.example.esify.ui.auth.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.esify.ui.theme.AuthCardBorder
import com.example.esify.ui.theme.AuthDarkGreen
import com.example.esify.ui.theme.AuthMidGreen
import com.example.esify.ui.theme.AuthMintSurface
import com.example.esify.ui.theme.EsifyTheme
import com.example.esify.ui.theme.MintAccent
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.TrophyOrange
import com.example.esify.ui.theme.WhiteCards

@Composable
fun AuthIllustration(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(180.dp),
        contentAlignment = Alignment.Center
    ) {
        // Outer soft mint glow circle
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(AuthMintSurface, shape = CircleShape)
        )

        // Floating Educational Icon: Top-Left (Gold Star)
        Card(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 10.dp, y = 14.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = WhiteCards),
            border = BorderStroke(1.dp, AuthCardBorder),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Box(
                modifier = Modifier.size(34.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = TrophyOrange,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        // Floating Educational Icon: Top-Right (Verified / Excellence badge)
        Card(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-10).dp, y = 14.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = WhiteCards),
            border = BorderStroke(1.dp, AuthCardBorder),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Box(
                modifier = Modifier.size(34.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Verified,
                    contentDescription = null,
                    tint = PrimaryGreen,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        // Floating Educational Icon: Bottom-Left (Open Book)
        Card(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = 12.dp, y = (-12).dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = WhiteCards),
            border = BorderStroke(1.dp, AuthCardBorder),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Box(
                modifier = Modifier.size(34.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.MenuBook,
                    contentDescription = null,
                    tint = AuthMidGreen,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        // Floating Educational Icon: Bottom-Right (AutoStories)
        Card(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-12).dp, y = (-12).dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = WhiteCards),
            border = BorderStroke(1.dp, AuthCardBorder),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Box(
                modifier = Modifier.size(34.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.AutoStories,
                    contentDescription = null,
                    tint = PrimaryGreen,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        // Central Stacked Books + Graduation Cap Assembly
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            // Graduation Cap
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(54.dp)
            ) {
                // Graduation diamond cap base
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .rotate(45f)
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(4.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(AuthDarkGreen, AuthMidGreen)
                            ),
                            shape = RoundedCornerShape(4.dp)
                        )
                )
                // Gold button on top of cap
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(TrophyOrange, shape = CircleShape)
                )
                // Graduation cap icon overlay for crisp outline & tassel
                Icon(
                    imageVector = Icons.Filled.School,
                    contentDescription = "Graduation Cap",
                    tint = Color.White.copy(alpha = 0.9f),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            // Top Book (Mint accent)
            Box(
                modifier = Modifier
                    .width(82.dp)
                    .height(13.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(4.dp))
                    .background(MintAccent, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                // Book spine highlight
                Box(
                    modifier = Modifier
                        .width(10.dp)
                        .height(2.dp)
                        .background(AuthDarkGreen.copy(alpha = 0.4f), shape = RoundedCornerShape(1.dp))
                )
            }

            Spacer(modifier = Modifier.height(3.dp))

            // Middle Book (Primary Green)
            Box(
                modifier = Modifier
                    .width(96.dp)
                    .height(14.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(4.dp))
                    .background(PrimaryGreen, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier = Modifier
                        .width(12.dp)
                        .height(2.dp)
                        .background(Color.White.copy(alpha = 0.5f), shape = RoundedCornerShape(1.dp))
                )
            }

            Spacer(modifier = Modifier.height(3.dp))

            // Bottom Book (Dark Forest Green base)
            Box(
                modifier = Modifier
                    .width(112.dp)
                    .height(15.dp)
                    .shadow(elevation = 3.dp, shape = RoundedCornerShape(4.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(AuthDarkGreen, AuthMidGreen)
                        ),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier = Modifier
                        .width(16.dp)
                        .height(2.dp)
                        .background(TrophyOrange.copy(alpha = 0.7f), shape = RoundedCornerShape(1.dp))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthIllustrationPreview() {
    EsifyTheme {
        Box(
            modifier = Modifier
                .size(240.dp)
                .background(Color(0xFFF4FAF6)),
            contentAlignment = Alignment.Center
        ) {
            AuthIllustration()
        }
    }
}

package com.project.dishnary.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


val DishnaryLogo = TextStyle(
    color = Color(0xFFFF6347), // Tomato
    fontSize = 95.sp,
    fontWeight = FontWeight.Bold,
    fontStyle = FontStyle.Italic,
    fontFamily = FontFamily.Cursive,
    shadow = Shadow(
        color = Color(0xFFF4A460), // Sandy brown
        offset = Offset(10f, 5f),
        blurRadius = 0.8f
    ),
    textAlign = TextAlign.Center
)

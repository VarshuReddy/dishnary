package com.project.dishnary.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.project.dishnary.R

// Set of Material typography styles to start with
val DishnaryTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

//     Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.delius_swashcaps_regular)),
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )
    ,
    labelSmall = TextStyle(
        fontFamily =  FontFamily(Font(R.font.delius_swashcaps_regular)),
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
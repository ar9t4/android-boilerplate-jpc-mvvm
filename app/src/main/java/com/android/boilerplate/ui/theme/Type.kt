package com.android.boilerplate.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.boilerplate.R

/**
 * Created by Abdul Rahman on 02/05/2024
 */
val robotoThinFontFamily =
    FontFamily(Font(R.font.roboto_thin, FontWeight.Thin, FontStyle.Normal))
val robotoLightFontFamily =
    FontFamily(Font(R.font.roboto_light, FontWeight.Light, FontStyle.Normal))
val robotoRegularFontFamily =
    FontFamily(Font(R.font.roboto_regular, FontWeight.Normal, FontStyle.Normal))
val robotoRegularItalicFontFamily =
    FontFamily(Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic))
val robotoMediumFontFamily =
    FontFamily(Font(R.font.roboto_medium, FontWeight.Medium, FontStyle.Normal))
val robotoSemiBoldFontFamily =
    FontFamily(Font(R.font.roboto_semi_bold, FontWeight.SemiBold, FontStyle.Normal))
val robotoBlackFontFamily =
    FontFamily(Font(R.font.roboto_black, FontWeight.Black, FontStyle.Normal))

// set of Material Typography TextStyles
val typography = Typography(
    displayLarge = TextStyle(
        fontFamily = robotoRegularFontFamily,
        fontSize = 57.sp,
        lineHeight = 64.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = robotoRegularFontFamily,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),
    displaySmall = TextStyle(
        fontFamily = robotoRegularFontFamily,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = robotoRegularFontFamily,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = robotoRegularFontFamily,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = robotoRegularFontFamily,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleLarge = TextStyle(
        fontFamily = robotoMediumFontFamily,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = robotoMediumFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = robotoMediumFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = robotoRegularFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = robotoRegularFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = robotoRegularFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = robotoMediumFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = robotoMediumFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = robotoMediumFontFamily,
        fontSize = 11.sp,
        lineHeight = 16.sp
    )
)
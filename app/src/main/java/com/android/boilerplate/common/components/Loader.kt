package com.android.boilerplate.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Created by Abdul Rahman on 04/06/2024
 */
@Composable
internal fun Loader(modifier: Modifier, color: Color, trackColor: Color) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = modifier, color = color, trackColor = trackColor)
    }
}
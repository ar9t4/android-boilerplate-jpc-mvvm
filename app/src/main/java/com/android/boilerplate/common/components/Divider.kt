package com.android.boilerplate.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by Abdul Rahman on 06/06/2024
 */
@Composable
fun Divider(horizontalPadding: Dp = 0.dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding)
            .height(height = 0.25.dp)
            .background(color = MaterialTheme.colorScheme.onSurface)
    )
}
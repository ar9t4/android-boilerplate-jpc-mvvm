package com.android.boilerplate.common.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage

/**
 * Created by Abdul Rahman on 04/06/2024
 */
@Composable
internal fun CircularImage(modifier: Modifier, url: String?, contentDescription: String?) {
    AsyncImage(
        modifier = modifier.clip(shape = CircleShape),
        model = url,
        contentDescription = contentDescription
    )
}
package com.android.boilerplate.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.boilerplate.R

/**
 * Created by Abdul Rahman on 09/07/2024
 */
@Composable
fun CircleArrowRight() {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(color = MaterialTheme.colorScheme.surfaceContainer, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = stringResource(id = R.string.navigate_to_list_item),
            modifier = Modifier
                .width(width = 24.dp)
                .height(height = 24.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}
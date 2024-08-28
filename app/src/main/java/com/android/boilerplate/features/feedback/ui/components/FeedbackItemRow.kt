package com.android.boilerplate.features.feedback.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.boilerplate.features.feedback.domain.model.Feedback

/**
 * Created by Abdul Rahman on 12/06/2024
 */
@Composable
internal fun FeedbackItemRow(item: Feedback, onItemClick: (id: Int) -> Unit) {
    Row(
        modifier = Modifier
            .padding(start = 6.dp, top = 6.dp, end = 6.dp, bottom = 6.dp)
            .fillMaxWidth()
            .background(
                color = if (item.selected) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.surfaceContainer,
                shape = MaterialTheme.shapes.small
            )
            .clip(MaterialTheme.shapes.small)
            .clickable { onItemClick(item.id) }
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.name,
            modifier = Modifier
                .width(100.dp)
                .height(40.dp)
                .wrapContentHeight(align = Alignment.CenterVertically),
            color = if (item.selected) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
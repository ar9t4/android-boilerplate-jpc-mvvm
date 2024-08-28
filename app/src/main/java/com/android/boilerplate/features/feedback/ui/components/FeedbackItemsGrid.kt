package com.android.boilerplate.features.feedback.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.boilerplate.features.feedback.domain.model.Feedback

/**
 * Created by Abdul Rahman on 12/06/2024
 */
@Composable
internal fun FeedbackItemsGrid(items: List<Feedback>, onItemClick: (id: Int) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(count = 2), Modifier.height(height = 200.dp)) {
        items(
            count = items.size,
            key = { items[it].id },
            itemContent = { index ->
                FeedbackItemRow(item = items[index], onItemClick = { onItemClick(it) })
            }
        )
    }
}
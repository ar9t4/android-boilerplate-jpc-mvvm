package com.android.boilerplate.features.more.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.boilerplate.common.components.Divider
import com.android.boilerplate.features.more.domain.model.More

/**
 * Created by Abdul Rahman on 06/06/2024
 */
@Composable
internal fun MoreItemsList(items: List<More>, onItemClick: (item: More) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            count = items.size,
            key = { items[it].id },
            itemContent = { index ->
                MoreItemRow(item = items[index], onItemClick = onItemClick)
                if (index != items.size.minus(1)) {
                    Divider(horizontalPadding = 16.dp)
                }
            }
        )
    }
}
package com.android.boilerplate.features.themes.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.boilerplate.common.components.Divider
import com.android.boilerplate.features.themes.domain.model.Theme

/**
 * Created by Abdul Rahman on 07/06/2024
 */
@Composable
internal fun ThemeItemsList(
    modifier: Modifier,
    items: List<Theme>,
    onItemClick: (id: Int) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(
            count = items.size,
            key = { items[it].id },
            itemContent = { index ->
                ThemeItemRow(item = items[index], onItemClick = onItemClick)
                if (index != items.size.minus(1)) {
                    Divider(horizontalPadding = 16.dp)
                }
            }
        )
    }
}
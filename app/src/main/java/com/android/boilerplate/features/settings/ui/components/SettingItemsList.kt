package com.android.boilerplate.features.settings.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.boilerplate.common.components.Divider
import com.android.boilerplate.features.settings.domain.model.Setting

/**
 * Created by Abdul Rahman on 07/06/2024
 */
@Composable
internal fun SettingItemsList(items: List<Setting>, onSettingClick: (settingId: Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            count = items.size,
            key = { items[it].id },
            itemContent = { index ->
                SettingItemRow(item = items[index], onSettingClick = { onSettingClick(it) })
                if (index != items.size.minus(1)) {
                    Divider(horizontalPadding = 16.dp)
                }
            }
        )
    }
}
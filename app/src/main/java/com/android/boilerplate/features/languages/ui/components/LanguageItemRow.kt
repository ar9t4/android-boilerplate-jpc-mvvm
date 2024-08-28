package com.android.boilerplate.features.languages.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.boilerplate.R
import com.android.boilerplate.features.languages.domain.model.Language

/**
 * Created by Abdul Rahman on 07/06/2024
 */
@Composable
internal fun LanguageItemRow(item: Language, onItemClick: (item: Language) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable { onItemClick(item) }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.name,
            modifier = Modifier.weight(weight = 1f),
            style = MaterialTheme.typography.bodyLarge
        )
        if (item.selected) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = stringResource(id = R.string.selected_list_item),
                modifier = Modifier.size(size = 32.dp)
            )
        }
    }
}
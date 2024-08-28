package com.android.boilerplate.features.more.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.boilerplate.BuildConfig
import com.android.boilerplate.R
import com.android.boilerplate.common.components.CircleArrowRight
import com.android.boilerplate.features.more.domain.model.More

/**
 * Created by Abdul Rahman on 06/06/2024
 */
@Composable
internal fun MoreItemRow(item: More, onItemClick: (item: More) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = stringResource(id = R.string.item_icon, item.name)
        )
        Text(
            text = item.name,
            modifier = Modifier.weight(weight = 1f),
            style = MaterialTheme.typography.bodyLarge
        )
        if (item.name == stringResource(id = R.string.version)) {
            Text(text = BuildConfig.VERSION_NAME, style = MaterialTheme.typography.bodyMedium)
        } else {
            CircleArrowRight()
        }
    }
}
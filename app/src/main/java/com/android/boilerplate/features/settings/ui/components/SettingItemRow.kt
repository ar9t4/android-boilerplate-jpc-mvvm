package com.android.boilerplate.features.settings.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.boilerplate.R
import com.android.boilerplate.common.components.CircleArrowRight
import com.android.boilerplate.features.settings.domain.model.Setting

/**
 * Created by Abdul Rahman on 07/06/2024
 */
@Composable
internal fun SettingItemRow(item: Setting, onSettingClick: (settingId: Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSettingClick(item.id) }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(weight = 1f)) {
            Text(
                text = item.key,
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = item.value,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        if (item.key == stringResource(id = R.string.notifications)) {
            val value = item.value == stringResource(id = R.string.setting_on)
            Switch(checked = value, onCheckedChange = {})
        } else {
            CircleArrowRight()
        }
    }
}
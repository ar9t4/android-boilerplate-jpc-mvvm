package com.android.boilerplate.features.userdetails.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import com.android.boilerplate.R

/**
 * Created by Abdul Rahman on 20/06/2024
 */
@Composable
fun UserDetailRow(@DrawableRes icon: Int, title: String) {
    Row(
        modifier = Modifier.padding(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = R.string.user_gender_icon)
        )
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
    }
}
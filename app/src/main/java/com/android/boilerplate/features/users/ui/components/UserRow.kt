package com.android.boilerplate.features.users.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.boilerplate.R
import com.android.boilerplate.common.components.CircularImage
import com.android.boilerplate.core.data.local.database.entities.RandomUser

/**
 * Created by Abdul Rahman on 04/06/2024
 */
@Composable
internal fun UserRow(user: RandomUser, onUserClick: (userId: String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = MaterialTheme.shapes.small
            )
            .clip(MaterialTheme.shapes.small)
            .clickable { onUserClick(user.id.toString()) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularImage(
            modifier = Modifier.size(64.dp),
            url = user.picture?.medium,
            contentDescription = stringResource(id = R.string.user_profile_picture)
        )
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "${user.name?.title} ${user.name?.first} ${user.name?.last}")
            Text(text = user.email ?: "")
            Text(text = user.phone ?: "")
        }
    }
}
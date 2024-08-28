package com.android.boilerplate.features.users.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.boilerplate.core.data.local.database.entities.RandomUser

/**
 * Created by Abdul Rahman on 04/06/2024
 */
@Composable
internal fun UsersList(users: List<RandomUser>, onUserClick: (userId: String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 8.dp)
    ) {
        items(
            count = users.size,
            key = { users[it].phone ?: "" },
            itemContent = { index ->
                UserRow(user = users[index], onUserClick = onUserClick)
            }
        )
    }
}
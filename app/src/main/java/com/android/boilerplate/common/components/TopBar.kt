package com.android.boilerplate.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.boilerplate.R

/**
 * Created by Abdul Rahman on 21/06/2024
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, onNavigateBack: () -> Unit) {
    TopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.titleLarge) },
        navigationIcon = {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .size(size = 40.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = CircleShape
                    )
                    .clip(MaterialTheme.shapes.extraLarge)
                    .clickable { onNavigateBack() },
                contentAlignment = Alignment.Center,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = stringResource(id = R.string.navigate_back_icon),
                        modifier = Modifier.size(size = 24.dp)
                    )
                })
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}
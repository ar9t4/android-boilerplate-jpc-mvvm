package com.android.boilerplate.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.boilerplate.R

/**
 * Created by Abdul Rahman on 21/08/2024
 */
@Composable
internal fun ErrorView(onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.no_internet),
                contentDescription = stringResource(id = R.string.no_internet_error),
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = stringResource(id = R.string.no_internet_error),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            FilledButton(
                onClick = { onRetry() },
                modifier = Modifier.wrapContentSize(),
                content = {
                    Text(
                        text = stringResource(id = R.string.retry),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}
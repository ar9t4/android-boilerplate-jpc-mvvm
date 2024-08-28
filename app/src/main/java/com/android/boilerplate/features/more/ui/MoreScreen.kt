package com.android.boilerplate.features.more.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.boilerplate.R
import com.android.boilerplate.common.components.Loader
import com.android.boilerplate.common.utils.BrowserUtils
import com.android.boilerplate.common.utils.IntentUtils
import com.android.boilerplate.features.feedback.navigation.FEEDBACK_ROUTE
import com.android.boilerplate.features.more.domain.model.More
import com.android.boilerplate.features.more.ui.components.MoreItemsList
import com.android.boilerplate.features.settings.navigation.SETTINGS_ROUTE

/**
 * Created by Abdul Rahman on 08/05/2024
 */
@Composable
internal fun MoreRoute(
    navigateTo: (route: String) -> Unit,
    viewModel: MoreViewModel = hiltViewModel()
) {
    val events = viewModel.event.collectAsStateWithLifecycle().value
    val uiState = viewModel.state.collectAsStateWithLifecycle().value
    when (events) {
        is MoreEvents.Default -> {}
        is MoreEvents.NavigateToSettings -> navigateTo(SETTINGS_ROUTE)
        is MoreEvents.NavigateToFeedback -> navigateTo(FEEDBACK_ROUTE)
        is MoreEvents.NavigateToPrivacyPolicy -> BrowserUtils.openUri(
            uriHandler = LocalUriHandler.current,
            uri = stringResource(id = R.string.privacy_policy_link)
        )

        is MoreEvents.NavigateToShare -> {
            val context = LocalContext.current
            IntentUtils.launchIntent(
                context = context,
                title = stringResource(id = R.string.share),
                message = stringResource(
                    id = R.string.share_message,
                    stringResource(id = R.string.app_name),
                    context.packageName
                )
            )
        }

        is MoreEvents.NavigateToRateUs -> BrowserUtils.launchRateUsUri(
            context = LocalContext.current,
            uriHandler = LocalUriHandler.current
        )

        is MoreEvents.NavigateToMoreApps -> BrowserUtils.launchMoreAppsUri(
            context = LocalContext.current,
            uriHandler = LocalUriHandler.current
        )

        is MoreEvents.NavigateToVersion -> {}
    }
    LaunchedEffect(key1 = viewModel) {
        viewModel.processAction(MoreActions.GetMoreItems)
    }
    MoreScreen(
        uiState = uiState,
        onItemClick = { viewModel.processAction(MoreActions.OnItemClick(item = it)) }
    )
}

@Composable
private fun MoreScreen(uiState: MoreUiState, onItemClick: (item: More) -> Unit) {
    when (uiState) {
        is MoreUiState.Default -> {}
        is MoreUiState.Loading -> {
            Loader(
                modifier = Modifier,
                color = MaterialTheme.colorScheme.onSurface,
                trackColor = MaterialTheme.colorScheme.surface
            )
        }

        is MoreUiState.Error -> {}
        is MoreUiState.Success -> {
            Column {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more_filled),
                        contentDescription = stringResource(
                            id = R.string.item_icon,
                            stringResource(id = R.string.more)
                        ),
                        modifier = Modifier
                            .width(width = 36.dp)
                            .height(height = 36.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.more),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
                MoreItemsList(items = uiState.data, onItemClick = { onItemClick(it) })
            }
        }
    }
}
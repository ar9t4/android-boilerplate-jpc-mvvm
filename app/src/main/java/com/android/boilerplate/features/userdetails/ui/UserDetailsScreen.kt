package com.android.boilerplate.features.userdetails.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.boilerplate.R
import com.android.boilerplate.common.components.CircularImage
import com.android.boilerplate.common.components.Loader
import com.android.boilerplate.common.components.TopBar
import com.android.boilerplate.features.userdetails.ui.components.UserDetailRow

/**
 * Created by Abdul Rahman on 08/05/2024
 */
@Composable
internal fun UserDetailsRoute(
    onNavigateBack: () -> Unit,
    viewModel: UserDetailsViewModel = hiltViewModel()
) {
    val events = viewModel.event.collectAsStateWithLifecycle().value
    val uiState = viewModel.state.collectAsStateWithLifecycle().value
    when (events) {
        is UserDetailsEvents.Default -> {}
    }
    LaunchedEffect(key1 = viewModel) {
        viewModel.processAction(UserDetailsActions.GetUser)
    }
    UserDetailsScreen(uiState = uiState, onNavigateBack = onNavigateBack)
}

@Composable
private fun UserDetailsScreen(uiState: UserDetailsUiState, onNavigateBack: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = stringResource(id = R.string.user_details),
                onNavigateBack = onNavigateBack
            )
        }
    ) {
        when (uiState) {
            is UserDetailsUiState.Default -> {}
            is UserDetailsUiState.Loading -> {
                Loader(
                    modifier = Modifier.padding(top = it.calculateTopPadding()),
                    color = MaterialTheme.colorScheme.onSurface,
                    trackColor = MaterialTheme.colorScheme.surface
                )
            }

            is UserDetailsUiState.Error -> {}
            is UserDetailsUiState.Success -> {
                val user = uiState.data
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = it.calculateTopPadding())
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colorScheme.surfaceContainer),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularImage(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .size(128.dp),
                            url = user.picture?.large,
                            contentDescription = stringResource(id = R.string.user_profile_picture)
                        )
                        Text(
                            text = "${user.name?.title} ${user.name?.first} ${user.name?.last}",
                            modifier = Modifier.padding(vertical = 16.dp),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.more_details),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    UserDetailRow(icon = R.drawable.ic_email, title = user.email ?: "")
                    UserDetailRow(icon = R.drawable.ic_gender, title = user.gender ?: "")
                    UserDetailRow(icon = R.drawable.ic_calendar, title = user.dob?.date ?: "")
                    UserDetailRow(
                        icon = R.drawable.ic_location,
                        title = user.location?.country ?: ""
                    )
                    UserDetailRow(icon = R.drawable.ic_phone, title = user.phone ?: "")
                }
            }
        }
    }
}
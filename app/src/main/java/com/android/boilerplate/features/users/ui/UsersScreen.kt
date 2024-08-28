package com.android.boilerplate.features.users.ui

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.boilerplate.R
import com.android.boilerplate.common.components.AlertDialog
import com.android.boilerplate.common.components.ErrorView
import com.android.boilerplate.common.components.Loader
import com.android.boilerplate.common.utils.Utils
import com.android.boilerplate.core.data.local.database.entities.RandomUser
import com.android.boilerplate.features.users.ui.components.UsersList
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

/**
 * Created by Abdul Rahman on 08/05/2024
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun UsersRoute(
    onUserClick: (userId: String) -> Unit,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val events = viewModel.event.collectAsStateWithLifecycle().value
    val uiState = viewModel.state.collectAsStateWithLifecycle().value
    val permissionNotGrantedDialog = remember { mutableStateOf(false) }
    val notificationsPermissionState = if (Utils.isTiramisuPlus()) {
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    } else {
        null
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        if (!it) {
            permissionNotGrantedDialog.value = true
        }
    }
    val appSettingsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        notificationsPermissionState?.let {
            if (!it.status.isGranted) {
                Toast.makeText(context, R.string.n_p_not_granted, Toast.LENGTH_SHORT).show()
            }
        }
    }
    when (events) {
        is UsersEvents.Default -> {}
        is UsersEvents.NavigateToUserDetails -> onUserClick(events.userId)
    }
    LaunchedEffect(key1 = viewModel) {
        viewModel.processAction(UsersActions.GetUsers)
    }
    LaunchedEffect(key1 = uiState) {
        if (uiState is UsersUiState.Success && Utils.isTiramisuPlus()) {
            notificationsPermissionState?.let {
                if (!it.status.isGranted || it.status.shouldShowRationale) {
                    if (Utils.isTiramisuPlus()) {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }
            }
        }
    }
    if (permissionNotGrantedDialog.value) {
        AlertDialog(
            onDismiss = { permissionNotGrantedDialog.value = false },
            dismissText = stringResource(id = R.string.cancel),
            onConfirm = {
                permissionNotGrantedDialog.value = false
                try {
                    val packageName = context.packageName
                    appSettingsLauncher.launch(
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).also {
                            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            it.data = Uri.fromParts("package", packageName, null)
                        })
                } catch (exception: Exception) {
                    Log.e("UsersRoute", exception.toString())
                }
            },
            confirmText = stringResource(id = R.string.open_app_settings),
            icon = ImageVector.vectorResource(id = R.drawable.ic_notifications),
            iconDescription = stringResource(
                id = R.string.item_icon,
                stringResource(id = R.string.notifications)
            ),
            title = stringResource(id = R.string.n_p_title),
            text = stringResource(id = R.string.n_p_text)
        )
    }
    UsersScreen(
        uiState = uiState,
        onUserClick = { viewModel.processAction(UsersActions.OnItemClick(userId = it)) },
        onRefresh = { viewModel.processAction(UsersActions.OnRefresh(results = 20)) },
        onRetry = {  viewModel.processAction(UsersActions.GetUsers) }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun UsersScreen(
    uiState: UsersUiState,
    onUserClick: (userId: String) -> Unit,
    onRefresh: () -> Unit,
    onRetry: () -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState is UsersUiState.Refresh,
        onRefresh = { onRefresh() }
    )
    when (uiState) {
        is UsersUiState.Default -> {}
        is UsersUiState.Loading -> {
            Loader(
                modifier = Modifier,
                color = MaterialTheme.colorScheme.onSurface,
                trackColor = MaterialTheme.colorScheme.surface
            )
        }

        is UsersUiState.Error -> {
            ErrorView(onRetry = { onRetry() })
        }

        is UsersUiState.Success, is UsersUiState.Refresh -> {
            Column {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_users_filled),
                        contentDescription = stringResource(
                            id = R.string.item_icon,
                            stringResource(id = R.string.users)
                        ),
                        modifier = Modifier
                            .width(width = 36.dp)
                            .height(height = 36.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.users),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pullRefresh(pullRefreshState)
                ) {
                    val users: List<RandomUser>? = when (uiState) {
                        is UsersUiState.Success -> uiState.data
                        is UsersUiState.Refresh -> uiState.currentData
                        else -> null
                    }
                    users?.let { UsersList(users = users, onUserClick = onUserClick) }
                    PullRefreshIndicator(
                        refreshing = uiState is UsersUiState.Refresh,
                        state = pullRefreshState,
                        modifier = Modifier.align(Alignment.TopCenter),
                        backgroundColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
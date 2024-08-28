package com.android.boilerplate.common.components

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Abdul Rahman on 05/07/2024
 */
@Composable
fun AlertDialog(
    onDismiss: () -> Unit,
    dismissText: String,
    onConfirm: () -> Unit,
    confirmText: String,
    icon: ImageVector,
    iconDescription: String,
    title: String,
    text: String,
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = { TextButton(onClick = { onConfirm() }) { Text(text = confirmText) } },
        modifier = Modifier,
        dismissButton = { TextButton(onClick = { onDismiss() }) { Text(text = dismissText) } },
        icon = { Icon(imageVector = icon, contentDescription = iconDescription) },
        title = { Text(text = title) },
        text = { Text(text = text) },
    )
}
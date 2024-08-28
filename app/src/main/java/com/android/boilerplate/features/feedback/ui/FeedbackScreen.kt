package com.android.boilerplate.features.feedback.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.boilerplate.R
import com.android.boilerplate.common.components.FilledButton
import com.android.boilerplate.common.components.Loader
import com.android.boilerplate.common.components.TextInputField
import com.android.boilerplate.features.feedback.domain.model.Feedback
import com.android.boilerplate.features.feedback.ui.components.FeedbackItemsGrid
import com.android.boilerplate.ui.theme.robotoBlackFontFamily

/**
 * Created by Abdul Rahman on 08/05/2024
 */
@Composable
internal fun FeedbackRoute(viewModel: FeedbackViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val feedback = rememberSaveable { mutableStateOf("") }
    val events = viewModel.event.collectAsStateWithLifecycle().value
    val uiState = viewModel.state.collectAsStateWithLifecycle().value
    val sendEmailActivityResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        // reset feedback screen state
        feedback.value = ""
        viewModel.processAction(FeedbackActions.ResetFeedbackItems)
    }
    when (events) {
        is FeedbackEvents.Default -> {}
        is FeedbackEvents.InvalidFeedback -> {
            Toast.makeText(context, R.string.invalid_feedback, Toast.LENGTH_SHORT).show()
        }
        is FeedbackEvents.FeedbackValidated -> {
            context.apply {
                var description = ""
                events.items.forEach { if (it.selected) description += "${it.name} \n\n" }
                if (events.feedback.trim().isNotEmpty()) {
                    description += "\n\n ${getString(R.string.detailed_feedback)}"
                    description += "\n\n ${events.feedback}"
                }
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "message/rfc822"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.email_address)))
                    putExtra(
                        Intent.EXTRA_SUBJECT,
                        "${getString(R.string.feedback)} - ${getString(R.string.app_name)}"
                    )
                    putExtra(Intent.EXTRA_TEXT, description)
                }
                try {
                    sendEmailActivityResult.launch(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, R.string.no_email_app_installed, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
    LaunchedEffect(key1 = viewModel) {
        viewModel.processAction(FeedbackActions.GetFeedbackItems)
    }
    FeedbackScreen(
        uiState = uiState,
        feedback = feedback,
        focusManager = focusManager,
        onItemClick = { viewModel.processAction(FeedbackActions.OnItemClick(id = it)) },
        validateFeedback = { p1, p2 ->
            viewModel.processAction(FeedbackActions.ValidateFeedback(items = p1, feedback = p2))
        }
    )
}

@Composable
private fun FeedbackScreen(
    uiState: FeedbackUiState,
    feedback: MutableState<String>,
    focusManager: FocusManager,
    onItemClick: (id: Int) -> Unit,
    validateFeedback: (items: List<Feedback>, feedback: String) -> Unit
) {
    when (uiState) {
        is FeedbackUiState.Default -> {}
        is FeedbackUiState.Loading -> {
            Loader(
                modifier = Modifier,
                color = MaterialTheme.colorScheme.onSurface,
                trackColor = MaterialTheme.colorScheme.surface
            )
        }

        is FeedbackUiState.Error -> {}
        is FeedbackUiState.Success -> {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_feedback_filled),
                        contentDescription = stringResource(
                            id = R.string.item_icon,
                            stringResource(id = R.string.feedback)
                        ),
                        modifier = Modifier
                            .width(width = 36.dp)
                            .height(height = 36.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.feedback),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_feedback_artwork),
                    contentDescription = stringResource(
                        id = R.string.item_icon,
                        stringResource(id = R.string.feedback)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(ratio = 2f / 0.75f)
                )
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                Text(
                    text = stringResource(id = R.string.give_feedback),
                    style = MaterialTheme.typography.headlineMedium
                        .copy(fontFamily = robotoBlackFontFamily)
                )
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
                Text(
                    text = stringResource(id = R.string.how_we_can_improve),
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                FeedbackItemsGrid(items = uiState.data, onItemClick = { onItemClick(it) })
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    text = stringResource(id = R.string.briefly_describe_feedback),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                TextInputField(
                    value = feedback.value,
                    onValueChange = { feedback.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.type_here_),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    minLines = 5
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                FilledButton(
                    onClick = {
                        focusManager.clearFocus()
                        validateFeedback(uiState.data, feedback.value)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        Text(
                            text = stringResource(id = R.string.submit_feedback),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}
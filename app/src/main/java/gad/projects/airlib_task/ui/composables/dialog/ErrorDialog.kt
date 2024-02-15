package pithsoft.space.core.composable.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import gad.projects.airlib_task.R
import pithsoft.space.core.businessmodel.BusinessError
import pithsoft.space.core.composable.button.DialogButton


@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    error: BusinessError?,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    error?.let {
        AlertDialog(
            modifier = modifier.fillMaxWidth(),
            title = {
                androidx.compose.material3.Text(
                    text = error.title,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                androidx.compose.material3.Text(
                    text = error.message,
                    textAlign = TextAlign.Center
                )
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                DialogButton(
                    text = stringResource(id = R.string.action_ok),
                    onClick = onConfirm
                )
            }
        )
    }
}

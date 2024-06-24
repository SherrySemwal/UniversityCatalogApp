package com.sherry.universitycatalogapp.ui.core.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sherry.universitycatalogapp.R


@Composable
fun InternetConnectionAlertDialog(
    errorMessage: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        text = {
            Text(text = errorMessage)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            ElevatedButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(R.string.ok), color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    )
}
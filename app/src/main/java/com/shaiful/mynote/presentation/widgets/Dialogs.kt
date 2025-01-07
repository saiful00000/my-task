package com.shaiful.mynote.presentation.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun  WarningDialog(message: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Warning") },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(text = "Ok")
            }
        }
    )
}

@Composable
fun  ErrorDialog(message: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Error") },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(text = "Ok")
            }
        }
    )
}


@Composable
fun  ConfirmationDialog(title: String, message: String, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = title) },
        text = { Text(text = message) },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "No")
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = "Yes")
            }
        }
    )
}
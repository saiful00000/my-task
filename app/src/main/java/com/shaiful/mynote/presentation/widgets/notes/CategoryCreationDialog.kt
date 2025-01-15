package com.shaiful.mynote.presentation.widgets.notes

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.shaiful.mynote.presentation.utility_widgets.HorizontalSpace
import com.shaiful.mynote.presentation.widgets.ThinButton

@Composable
fun CategoryCreationDialog(onSave: (String) -> Unit, onDismiss: () -> Unit) {
    var textInput by remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                ThinButton(onClick = onDismiss, text = "Cancel")
//                HorizontalSpace(width = 16)
                ThinButton(
                    onClick = { if (textInput.isNotBlank()) onSave(textInput.trim()) },
                    text = "Save",
                )
            }
        },

        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        title = { Text(text = "Enter Category Name") },
        text = {
            BasicTextField(
                value = textInput,
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                onValueChange = {
                    textInput = it
                },
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
                    .padding(16.dp)
                    .focusRequester(focusRequester = remember {
                        FocusRequester()
                    }),
            )
        },
    )
}
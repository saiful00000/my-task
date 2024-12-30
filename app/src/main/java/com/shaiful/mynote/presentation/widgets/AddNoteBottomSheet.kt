package com.shaiful.mynote.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.shaiful.mynote.domain.entities.AddNoteItem
import com.shaiful.mynote.ui.theme.getPriorityColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteBottomSheet(
    sheetTitle: String,
    onSave: (AddNoteItem) -> Unit,
    onDismiss: () -> Unit,
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("Low") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = sheetTitle)
            }

            Text(text = "Create Note")

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") },
                maxLines = 1,
            )

            Text(text = "Priority", style = MaterialTheme.typography.bodyMedium)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                listOf("Low", "Medium", "High").forEach { priorityOpt ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = priority == priorityOpt,
                            onClick = { priority = priorityOpt },
                        )
                        Text(
                            text = priorityOpt,
                            style = TextStyle(
                                color = getPriorityColor(priorityOpt),
                            )
                        )
                    }
                }
            }

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Description") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3,
            )
            Box(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    onClick = {
                        val note =
                            AddNoteItem(
                                title = title,
                                description = description,
                                priority = priority
                            )
                        onSave(note)
                    },
                ) {
                    Text(text = "Save")
                }
            }
            Box(modifier = Modifier.height(16.dp))
        }
    }
}
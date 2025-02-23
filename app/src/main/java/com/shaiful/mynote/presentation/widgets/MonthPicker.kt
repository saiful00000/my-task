package com.shaiful.mynote.presentation.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shaiful.mynote.presentation.utility_widgets.HorizontalSpace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerButton(
    onMonthPicked: (Int, Int) -> Unit,
) {
    var showMonthPicker by remember {
        mutableStateOf(false)
    }

    if (showMonthPicker) {
        AlertDialog(
            onDismissRequest = {
                showMonthPicker = false
            },
            confirmButton = {
                Button(onClick = { showMonthPicker = true }) {
                    Text(text = "Cancel")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showMonthPicker = true
                }) {
                    Text(text = "Ok")
                }
            },
            title = {
                Text(text = "Select year and month")
            },
            text = {
                Row (
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row (
                        modifier = Modifier.weight(1F)
                    ) {
                        Text(text = "Year")
                        HorizontalSpace(width = 8)
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Year arrow down",
                        )
                    }

                    Row (
                        modifier = Modifier.weight(1F)
                    ) {
                        Text(text = "Month")
                        HorizontalSpace(width = 8)
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Year arrow down",
                        )
                    }
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                showMonthPicker = true
            }
    ) {
        Text(text = "Pick Date")
    }

}
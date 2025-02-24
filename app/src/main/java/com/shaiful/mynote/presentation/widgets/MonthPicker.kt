package com.shaiful.mynote.presentation.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaiful.mynote.domain.DayType
import com.shaiful.mynote.presentation.utility_widgets.HorizontalSpace
import com.shaiful.mynote.presentation.utility_widgets.VerticalSpace
import java.time.LocalDate

@Composable
fun DatePickerButton(
    onMonthPicked: (Int, Int) -> Unit,
) {
    val months = listOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    );

    val now = LocalDate.now();
    val years = listOf(now.year - 2, now.year - 1, now.year);

    var showMonthPicker by remember {
        mutableStateOf(false)
    }

    var monthMenuExpanded by remember {
        mutableStateOf(false)
    }

    var yearMenuExpanded by remember {
        mutableStateOf(false)
    }

    var selectedMonth by remember {
        mutableStateOf(now.monthValue - 1)
    }

    var selectedYear by remember {
        mutableStateOf(now.year)
    }

    if (showMonthPicker) {
        AlertDialog(
            onDismissRequest = {
                showMonthPicker = false
            },
            confirmButton = {
                Button(onClick = {
                    showMonthPicker = false
                    onMonthPicked(selectedYear, selectedMonth)
                }) {
                    Text(text = "Ok")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showMonthPicker = false
                        selectedYear = now.year
                        selectedMonth = now.monthValue - 1;
                    }
                ) {
                    Text(text = "Cancel")
                }
            },
            title = {
                Text(text = "Select year and month")
            },
            text = {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = selectedYear.toString())
                        HorizontalSpace(width = 8)
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Year arrow down",
                        )
                    }
                    VerticalSpace(height = 16)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        itemsIndexed(months) { index, item ->

                            val isSelected = index == selectedMonth

                            Box (
                                modifier = Modifier
                                    .padding(4.dp)
                                    .border(
                                        shape = RoundedCornerShape(5.dp),
                                        width = 1.dp,
                                        color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else Color.Transparent
                                    )
                                    .clickable {
                                        selectedMonth = index
                                    }
                                    .background(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = .4F))

                            ) {
                                Text(
                                    modifier = Modifier.padding(
                                        vertical = 8.dp, horizontal = 8.dp
                                    ),
                                    text = item,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
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
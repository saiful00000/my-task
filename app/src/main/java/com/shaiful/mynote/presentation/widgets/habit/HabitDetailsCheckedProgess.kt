package com.shaiful.mynote.presentation.widgets.habit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.shaiful.mynote.presentation.utility_widgets.VerticalSpace

@Composable
fun HabitDetailsCheckedProgress(daysInMonth: Int, daysChecked: Int) {
    val percentage = (daysChecked.toFloat() / daysInMonth.toFloat()) * 100
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "$daysChecked/$daysInMonth",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Text(
                text = "${"%.2f".format(percentage)}%",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
        VerticalSpace(height = 4)
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = { percentage / 100 }
        )
    }
}
package com.shaiful.mynote.presentation.widgets.habit

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Pending
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.shaiful.mynote.domain.DayType
import com.shaiful.mynote.ui.theme.BrightGreen
import com.shaiful.mynote.ui.theme.Orange
import com.shaiful.mynote.ui.theme.PriorityHigh
import com.shaiful.mynote.ui.theme.PriorityMedium

@Composable
fun HabitStatusIcon(dayType: DayType, isChecked: Boolean) {

    var imageVector: ImageVector
    var iconColor: Color

    when (dayType) {
        DayType.Current, DayType.Previous -> {
            if (isChecked) {
                imageVector = Icons.Default.Check
                iconColor = BrightGreen
            } else {
                imageVector = Icons.Outlined.Cancel
                iconColor = Orange
            }
        }

        DayType.Forward -> {
            imageVector = Icons.Outlined.MoreHoriz
            iconColor = Color.Gray
        }
    }


    Icon(
        imageVector = imageVector,
        contentDescription = "Habit Status Icon",
        tint = iconColor,
        modifier = Modifier.size(16.dp),
    )
}
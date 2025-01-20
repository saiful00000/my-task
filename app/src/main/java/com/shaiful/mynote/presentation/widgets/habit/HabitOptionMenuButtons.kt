package com.shaiful.mynote.presentation.widgets.notes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shaiful.mynote.presentation.utility_widgets.HorizontalSpace
import com.shaiful.mynote.ui.theme.PriorityMedium

@Composable
fun HabitOptionMenuButton(
    onDetails: () -> Unit,
    onDelete: () -> Unit,
) {
    var menuExpanded by remember {
        mutableStateOf(false)
    }

    Box() {
        IconButton(
            onClick = { menuExpanded = !menuExpanded },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.MoreHoriz,
                contentDescription = "Note Menu",
                Modifier.size(18.dp),
                tint = Color.Gray
            )
        }

        DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
            DropdownMenuItem(
                text = {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.Red,
                        )
                        HorizontalSpace(width = 4)
                        Text(text = "Delete")
                    }
                },
                onClick = {
                    menuExpanded = false
                    onDelete()
                },
            )
            DropdownMenuItem(
                text = {
                    Row {
                        Icon(
                            imageVector = Icons.Rounded.Info,
                            contentDescription = "Details",
                        )
                        Box(modifier = Modifier.width(4.dp))
                        Text(text = "Details")
                    }
                },
                onClick = {
                    menuExpanded = false
                    onDetails()
                },
            )
        }
    }
}
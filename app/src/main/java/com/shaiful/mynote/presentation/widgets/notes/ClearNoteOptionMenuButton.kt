package com.shaiful.mynote.presentation.widgets.notes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.ClearAll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.shaiful.mynote.ui.theme.Orange
import com.shaiful.mynote.ui.theme.PriorityMedium

@Composable
fun ClearNoteOptionMenuButton(
    onClearAll: () -> Unit,
    onClearOnlyDone: () -> Unit,
) {
    var menuExpanded by remember {
        mutableStateOf(false)
    }

    Box() {

        IconButton(
            onClick = { menuExpanded = !menuExpanded },
        ) {
            Icon(
                imageVector = Icons.Outlined.ClearAll,
                contentDescription = "Clear Notes",
                tint = Orange
            )
        }

        DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
            DropdownMenuItem(
                text = {
                    Row {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.FormatListBulleted,
                            contentDescription = "Clear All",
                            tint = Orange,
                        )
                        Box(modifier = Modifier.width(4.dp))
                        Text(text = "Clear All")
                    }
                },
                onClick = {
                    menuExpanded = false
                    onClearAll()
                },
            )
            DropdownMenuItem(
                text = {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Checklist,
                            contentDescription = "Clear Only Completed",
                            tint = Orange,
                        )
                        HorizontalSpace(width = 4)
                        Text(text = "Only Completed")
                    }
                },
                onClick = {
                    menuExpanded = false
                    onClearOnlyDone()
                },
            )
        }
    }
}
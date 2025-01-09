package com.shaiful.mynote.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EventRepeat
import androidx.compose.material.icons.outlined.EventRepeat
import androidx.compose.material.icons.outlined.Recycling
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shaiful.mynote.presentation.models.MenuItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBottomSheet(onDismiss: () -> Unit) {
    val menuItems = listOf(
        MenuItem(
            name = "Habits",
            icon = Icons.Outlined.EventRepeat,
            callback = { /*TODO*/ }
        ),
        MenuItem(
            name = "Routine",
            icon = Icons.Outlined.Recycling,
            callback = { /*TODO*/ }
        ),
        MenuItem(
            name = "Stopwatch",
            icon = Icons.Outlined.WatchLater,
            callback = { /*TODO*/ }
        ),
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        shape = RoundedCornerShape(10.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // Number of columns in the grid
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(menuItems) {
                MenuItemCard(menuItem = it)
            }
        }
    }
}

@Composable
fun MenuItemCard(menuItem: MenuItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = menuItem.icon,
            contentDescription = menuItem.name,
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
        )
        Text(
            text = menuItem.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
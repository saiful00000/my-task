package com.shaiful.mynote.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.EventRepeat
import androidx.compose.material.icons.sharp.Recycling
import androidx.compose.material.icons.sharp.Timer
import androidx.compose.material.icons.sharp.WatchLater
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shaiful.mynote.presentation.models.MenuItem
import com.shaiful.mynote.presentation.navigation.RouteNames
import com.shaiful.mynote.presentation.utility_widgets.VerticalSpace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBottomSheet(onDismiss: () -> Unit, navController: NavController) {
    val menuItems = listOf(
        MenuItem(
            name = "Habits",
            icon = Icons.Sharp.EventRepeat,
            onTap = {
                onDismiss()
                navController.navigate(RouteNames.habitTrackerScreen)
            }
        ),
        MenuItem(
            name = "Routine",
            icon = Icons.Sharp.Recycling,
            onTap = {
                onDismiss()
            }
        ),
        MenuItem(
            name = "Stopwatch",
            icon = Icons.Sharp.Timer,
            onTap = {
                onDismiss()
                navController.navigate(RouteNames.stopwatchScreen)
            }
        ),
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3), // Number of columns in the grid
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(menuItems) {
                    MenuItemCard(menuItem = it)
                }
            }
            VerticalSpace(height = 16)
        }
    }
}

@Composable
fun MenuItemCard(menuItem: MenuItem) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                menuItem.onTap()
            }
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = menuItem.icon,
                contentDescription = menuItem.name,
            )
            VerticalSpace(height = 8)
            Text(
                text = menuItem.name,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Thin
            )
        }
    }
}
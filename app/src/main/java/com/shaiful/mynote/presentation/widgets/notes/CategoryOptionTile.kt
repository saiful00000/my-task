package com.shaiful.mynote.presentation.widgets.notes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.shaiful.mynote.data.tables.Category
import com.shaiful.mynote.presentation.widgets.ConfirmationDialog
import com.shaiful.mynote.ui.theme.Orange
import com.shaiful.mynote.ui.theme.PriorityMedium

@Composable
fun CategoryOptionTile(
    category: Category,
    onAdd: () -> Unit = {},
    onDelete: () -> Unit,
    onFavorite: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }

    // Toggle visibility on some user action, or trigger automatically
    LaunchedEffect(Unit) {
        isVisible = true // Set to true to animate icons appearing
    }

    var showConfirmationDialog by remember {
        mutableStateOf(false)
    }

    Box {
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { /*TODO*/ }) {

            }
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(durationMillis = 700) // Slower animation
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(durationMillis = 700) // Slower exit
                )
            ) {
                IconButton(onClick = {
                    showConfirmationDialog = true
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete Category",
                        tint = Color.Red
                    )
                }
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(durationMillis = 700)
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(durationMillis = 700)
                )
            ) {
                IconButton(onClick = onFavorite) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite Category",
                        tint = Orange
                    )
                }
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(durationMillis = 700)
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(durationMillis = 700)
                )
            ) {
                IconButton(onClick = onAdd) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "Add Category",
                        tint = PriorityMedium
                    )
                }
            }
        }
    }

    if (showConfirmationDialog) {
        ConfirmationDialog(
            title = "Are you sure?",
            message = "Category ${category.name} and all task in this category will be deleted.",
            onDismiss = {
                showConfirmationDialog = false
            },
            onConfirm = {
                onDelete()
                showConfirmationDialog = false
            },
        )
    }
}

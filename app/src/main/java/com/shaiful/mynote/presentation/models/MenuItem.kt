package com.shaiful.mynote.presentation.models

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem (
    val name: String,
    val callback: () -> Unit,
    val icon: ImageVector
)
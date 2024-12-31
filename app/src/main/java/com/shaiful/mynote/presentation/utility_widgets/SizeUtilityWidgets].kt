package com.shaiful.mynote.presentation.utility_widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpace(height: Number) {
    Box(modifier = Modifier.height(height.toDouble().dp))
}

@Composable
fun HorizontalSpace(width: Number) {
    Box(modifier = Modifier.width(width.toDouble().dp))
}
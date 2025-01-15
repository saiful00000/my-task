package com.shaiful.mynote.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ThinButton(onClick: () -> Unit, text: String, fillMaxWidth: Boolean = false) {

    val modifier = if (fillMaxWidth) Modifier
        .padding(horizontal = 8.dp, vertical = 0.dp)
        .fillMaxWidth() else Modifier
        .padding(horizontal = 8.dp, vertical = 0.dp)

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
    ) {
        Text(text = text, modifier = Modifier.padding(vertical = 0.dp))
    }
}
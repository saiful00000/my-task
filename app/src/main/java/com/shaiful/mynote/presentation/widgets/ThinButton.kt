package com.shaiful.mynote.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shaiful.mynote.presentation.utility_widgets.HorizontalSpace

@Composable
fun ThinButton(
    onClick: () -> Unit,
    text: String,
    fillMaxWidth: Boolean = false,
) {

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


@Composable
fun ThinIconButton(
    onClick: () -> Unit,
    text: String,
    fillMaxWidth: Boolean = false,
    icon: @Composable () -> Unit,
    iconCentered: Boolean = false,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding
) {

    val modifier = if (fillMaxWidth) Modifier
        .padding(horizontal = 0.dp, vertical = 0.dp)
        .fillMaxWidth() else Modifier
        .padding(horizontal = 0.dp, vertical = 0.dp)

    val innerModifier = if (fillMaxWidth) Modifier.fillMaxWidth() else Modifier

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        contentPadding = contentPadding,
        ) {
        Row(
//            modifier = modifier,
            modifier = innerModifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (iconCentered) Arrangement.Center else Arrangement.SpaceBetween
        ) {
            Text(text = text)
            if (iconCentered) HorizontalSpace(width = 4)
            icon()
        }
    }
}
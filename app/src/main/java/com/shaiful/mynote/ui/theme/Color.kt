package com.shaiful.mynote.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val PriorityLow = Color(0xFF3F51B5)
val PriorityMedium = Color(0xFF0B7510)
val PriorityHigh = Color(0xFFD81305)
fun getPriorityColor(priority: String): Color {
    return if (priority == "Low") PriorityLow else (if (priority == "Medium") PriorityMedium else PriorityHigh)
}

val LightGray = Color(0xFFD6CCCC)
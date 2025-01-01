package com.shaiful.mynote.presentation.widgets

import CategoryOptionTile
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shaiful.mynote.domain.entities.AddNoteItem
import com.shaiful.mynote.domain.entities.NoteCategory
import com.shaiful.mynote.presentation.utility_widgets.VerticalSpace
import com.shaiful.mynote.ui.theme.Orange
import com.shaiful.mynote.ui.theme.PriorityMedium

@Composable
fun NoteCategoryListWidget(innerPadding: PaddingValues, isDarkTheme: Boolean) {

    val itemsList = List(25) {
        AddNoteItem(
            title = "Lorem ipsum dolor sit amet,",
            priority = if (it < 6) "Low" else (if (it < 11) "Medium" else "High"),
            description = "",
//            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
        )
    }

    val categoryList = List(50) {
        NoteCategory(
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit,",
            itemList = itemsList,
        )
    }

    val expandedState = remember { mutableStateListOf(*Array(categoryList.size) { false }) }

    LazyColumn(
        modifier = Modifier
            .padding(innerPadding),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        itemsIndexed(categoryList) { index, category ->

            var isExpanded by remember { mutableStateOf(expandedState[index]) }

            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                if (index == 0) {
                    VerticalSpace(height = 10)
                }

                // Animate rotation based on expanded state
                val rotationAngle by animateFloatAsState(
                    targetValue = if (isExpanded) 180f else 0f,
                    label = "Rotate 180 degrees when expanded" // Rotate 180 degrees when expanded
                )

                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp))
                        .clickable {
                            isExpanded = !isExpanded
                            expandedState[index] = isExpanded
                        }
                        .padding(8.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = category.title, style = TextStyle(fontWeight = FontWeight(700)), modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Expand",
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .rotate(rotationAngle) // Apply rotation
                        )
                    }
                }

                if (isExpanded) {
                    CategoryOptionTile()
                }

                if (isExpanded) {
                    NoteListWidget(itemsList = category.itemList, isDarkTheme = isDarkTheme)
                }
//                VerticalSpace(height = 32)
            }
        }
    }
}
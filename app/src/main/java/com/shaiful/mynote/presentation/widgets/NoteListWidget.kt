package com.shaiful.mynote.presentation.widgets

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
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaiful.mynote.domain.entities.AddNoteItem
import com.shaiful.mynote.domain.entities.NoteCategory
import com.shaiful.mynote.ui.theme.LightGray
import com.shaiful.mynote.ui.theme.Pink80
import com.shaiful.mynote.ui.theme.PurpleGrey80
import com.shaiful.mynote.ui.theme.getPriorityColor

@Composable
fun NoteCategoryListWidget(innerPadding: PaddingValues) {

    val itemsList = List(5) {
        AddNoteItem(
            title = "Lorem ipsum dolor sit amet,",
            priority = if (it < 6) "Low" else (if (it < 11) "Medium" else "High"),
            description = "",
//            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
        )
    }

    val categoryList = List(5) {
        NoteCategory(
            title = "Lorem Ipsum",
            itemList = itemsList,
        )
    }

    val expandedState = remember { mutableStateListOf(*Array(categoryList.size) { false }) }

    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .padding(bottom = 75.dp)
    ) {
        itemsIndexed(categoryList) { index, category ->

            var isExpanded by remember { mutableStateOf(expandedState[index]) }

            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        // Animate rotation based on expanded state
                        val rotationAngle by animateFloatAsState(
                            targetValue = if (isExpanded) 180f else 0f // Rotate 180 degrees when expanded
                        )

                        Text(text = category.title, style = TextStyle(fontWeight = FontWeight(700)))
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Expand",
                            modifier = Modifier
                                .clickable {
                                    isExpanded = !isExpanded
                                    expandedState[index] = isExpanded
                                }
                                .padding(horizontal = 10.dp)
                                .rotate(rotationAngle) // Apply rotation
                        )
                    }
                }
                if (isExpanded) {
                    NoteListWidget(itemsList = category.itemList)
                }
                Box(modifier = Modifier.height(18.dp))
            }
        }
    }
}

@Composable
fun NoteListWidget(itemsList: List<AddNoteItem>) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 8.dp)
    ) {
        itemsList.forEach { item ->
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .border(
                                    color = getPriorityColor(item.priority),
                                    width = 1.dp,
                                    shape = RoundedCornerShape(2.dp)
                                )
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = item.priority,
                                style = TextStyle(
                                    color = getPriorityColor(item.priority),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight(700)
                                )
                            )
                        }

                        NoteOptionMenuButton(onDone = { /*TODO*/ }, onDelete = {})
                    }
                    Text(text = item.title, style = TextStyle(fontWeight = FontWeight(500)))
                    Box(modifier = Modifier.height(4.dp))
                    if (item.description.isNotBlank()) {
                        Text(
                            text = item.description,
                            style = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant),
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Box(modifier = Modifier.height(8.dp))
                    HorizontalDivider(color = LightGray)
                }
            }
        }
    }
}
package com.shaiful.mynote.presentation.widgets

import android.provider.CalendarContract.Colors
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaiful.mynote.domain.entities.AddNoteItem
import com.shaiful.mynote.domain.entities.NoteCategory
import com.shaiful.mynote.ui.theme.getPriorityColor

@Composable
fun NoteCategoryListWidget(innerPadding: PaddingValues) {

    val itemsList = List(5) {
        AddNoteItem(
            title = "Lorem ipsum dolor sit amet,",
            priority = if (it < 6) "Low" else (if (it < 11) "Medium" else "High"),
//            description = "",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
        )
    }

    val categoryList = List(5) {
        NoteCategory(
            title = "Lorem Ipsum",
            itemList = itemsList,
        )
    }

    val expandedState = remember { mutableStateListOf(*Array(categoryList.size) { false }) }

    LazyColumn(modifier = Modifier.padding(innerPadding)) {
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
                        Text(text = category.title, style = TextStyle(fontWeight = FontWeight(700)))
                        Icon(
                            imageVector = if (!isExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                            contentDescription = "Expand",
                            modifier = Modifier
                                .clickable {
                                    isExpanded = !isExpanded
                                    expandedState[index] = isExpanded
                                }
                                .padding(10.dp)
                        )
                    }
                }
                if (isExpanded) {
                    NoteListWidget(itemsList = category.itemList)
                }
                if (index == (categoryList.size - 1)) {
                    Box(modifier = Modifier.height(75.dp))
                }
            }
        }
    }
}

@Composable
fun NoteListWidget(itemsList: List<AddNoteItem>) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .animateContentSize(
                animationSpec = tween(durationMillis = 500)
            )
    ) {
        itemsList.forEach { item ->
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
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
                    Text(
                        text = item.description,
                        style = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Box(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
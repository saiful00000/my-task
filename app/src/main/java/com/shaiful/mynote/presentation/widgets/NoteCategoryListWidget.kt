package com.shaiful.mynote.presentation.widgets

import CategoryOptionTile
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shaiful.mynote.data.tables.Note
import com.shaiful.mynote.domain.entities.AddNoteItem
import com.shaiful.mynote.domain.entities.NoteCategory
import com.shaiful.mynote.presentation.utility_widgets.VerticalSpace
import com.shaiful.mynote.presentation.viewmodels.NoteViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCategoryListWidget(
    innerPadding: PaddingValues,
    isDarkTheme: Boolean,
    noteViewModel: NoteViewmodel
) {

    val categoryList by noteViewModel.allCategories.collectAsState()

    if (categoryList.isEmpty()) {
        Text(text = "Empty Category")
    } else {
        val expandedState =
            remember(categoryList) { mutableStateListOf(*Array(categoryList.size) { false }) }


        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            itemsIndexed(categoryList) { index, category ->

                Log.i("Recomposing", "Category item recomposing id -> ${category.id}")

                var isExpanded by remember { mutableStateOf(expandedState[index]) }

                /// note creation related fields
                val sheetState = rememberModalBottomSheetState()
                var showSheet by remember {
                    mutableStateOf(false)
                }

                if (showSheet) {
                    AddNoteBottomSheet(
                        sheetTitle = "Group Name",
                        onSave = { noteItem ->
                            println("Note item = $noteItem")
                            noteViewModel.addNote(
                                Note(
                                    title = noteItem.title,
                                    description = noteItem.description,
                                    categoryId = category.id,
                                    priority = noteItem.priority,
                                )
                            )
                            showSheet = false
                        },
                        onDismiss = {
                            showSheet = false
                        }
                    )
                }

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
                            .padding(top = 8.dp, bottom = 8.dp, start = 8.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = category.name,
                                style = TextStyle(fontWeight = FontWeight(700)),
                                modifier = Modifier.weight(1f)
                            )
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
                        CategoryOptionTile(
                            onDelete = {},
                            onFavorite = {},
                            onAdd = {
                                showSheet = true
                            },
                        )
                    }

                    Column {
                        VerticalSpace(height = 24)
                        if (isExpanded) {
                            NoteListWidget(isDarkTheme = isDarkTheme, noteViewmodel = noteViewModel, category = category)
                        }
                    }
                }
            }
        }
    }
}
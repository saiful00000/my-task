package com.shaiful.mynote.presentation.widgets.notes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.shaiful.mynote.data.tables.Category
import com.shaiful.mynote.data.tables.Note
import com.shaiful.mynote.presentation.utility_widgets.HorizontalSpace
import com.shaiful.mynote.presentation.utility_widgets.VerticalSpace
import com.shaiful.mynote.presentation.viewmodels.NoteViewmodel
import com.shaiful.mynote.ui.theme.OnDarkBorder
import com.shaiful.mynote.ui.theme.OnLightBorder
import com.shaiful.mynote.ui.theme.getPriorityColorById

@Composable
fun NoteListWidget(isDarkTheme: Boolean, noteViewmodel: NoteViewmodel, category: Category) {

    val itemsList by noteViewmodel.getNotesByCategory(category.id).collectAsState()

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 8.dp)
    ) {
        itemsList.forEachIndexed { index, item ->

            Log.i("Recomposing", "Note item recomposing id -> ${item.id}")

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    VerticalSpace(height = 4)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(color = getPriorityColorById(item.priority))
                        )
                        HorizontalSpace(width = 4)
                        Text(
                            text = item.title,
                            style = TextStyle(
                                fontWeight = FontWeight(500),
                                textDecoration = if (item.isDone) TextDecoration.LineThrough else TextDecoration.None
                            ),
                            modifier = Modifier.weight(1f),
                        )
                        NoteOptionMenuButton(onDone = {
                            val updatedItem = Note(
                                id = item.id,
                                title = item.title,
                                description = item.description,
                                priority = item.priority,
                                categoryId = item.categoryId,
                                createdAt = item.createdAt,
                                isDone = true,
                            )
                            noteViewmodel.update(updatedItem)
                        }, onDelete = {
                            noteViewmodel.deleteNote(item)
                        })
                    }
                    VerticalSpace(height = 4)
                    if (item.description.isNotBlank()) {
                        Row {
                            HorizontalSpace(width = 12)
                            HorizontalSpace(width = 4)
                            Text(
                                text = item.description,
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textDecoration = if (item.isDone == true) TextDecoration.LineThrough else TextDecoration.None,
                                ),
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                    VerticalSpace(height = 8)
                    HorizontalDivider(color = if (isDarkTheme) OnDarkBorder else OnLightBorder)
                    VerticalSpace(height = 8)

                    if (index == itemsList.size - 1) {
                        VerticalSpace(height = 24)
                    }
                }
            }
        }
    }
}
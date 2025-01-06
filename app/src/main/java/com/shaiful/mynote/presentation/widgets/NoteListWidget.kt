package com.shaiful.mynote.presentation.widgets

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaiful.mynote.data.tables.Category
import com.shaiful.mynote.data.tables.Note
import com.shaiful.mynote.domain.entities.AddNoteItem
import com.shaiful.mynote.presentation.utility_widgets.VerticalSpace
import com.shaiful.mynote.presentation.viewmodels.NoteViewmodel
import com.shaiful.mynote.ui.theme.OnDarkBorder
import com.shaiful.mynote.ui.theme.OnLightBorder
import com.shaiful.mynote.ui.theme.getPriorityColor

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

                        NoteOptionMenuButton(onDone = { /*TODO*/ }, onDelete = {
                            noteViewmodel.deleteNote(item)
                        })
                    }
                    VerticalSpace(height = 4)
                    Text(text = item.title, style = TextStyle(fontWeight = FontWeight(500)))
                    VerticalSpace(height = 4)
                    if (item.description.isNotBlank()) {
                        Text(
                            text = item.description,
                            style = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant),
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    VerticalSpace(height = 8)
                    HorizontalDivider(color = if (isDarkTheme) OnDarkBorder else OnLightBorder)
                    if (index == itemsList.size - 1) {
                        VerticalSpace(height = 24)
                    }
                }
            }
        }
    }
}
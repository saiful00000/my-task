package com.shaiful.mynote.domain.entities

data class NoteCategory (
    val title: String,
    val itemList: List<AddNoteItem>
)
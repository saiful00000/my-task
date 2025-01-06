package com.shaiful.mynote.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaiful.mynote.data.tables.Category
import com.shaiful.mynote.data.tables.Note
import com.shaiful.mynote.domain.repositories.CategoryRepository
import com.shaiful.mynote.domain.repositories.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewmodel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val categoryRepository: CategoryRepository
): ViewModel() {


    val allCategories = categoryRepository.getAllCategories().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList(),
    )

    fun addCategory(categoryName: String) = viewModelScope.launch {
        val category = Category(name = categoryName)
        categoryRepository.insert(category)
    }

    fun deleteCategory(category: Category) = viewModelScope.launch {
        categoryRepository.delete(category)
    }

    // Cache for notes by category
    private val notesByCategoryCache = mutableMapOf<Int, StateFlow<List<Note>>>()

    // Fetch notes for a category, ensuring caching
    fun getNotesByCategory(categoryId: Int): StateFlow<List<Note>> {
        return notesByCategoryCache.getOrPut(categoryId) {
            noteRepository.getNotesByCategory(categoryId).stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )
        }
    }

    fun addNote(note: Note) = viewModelScope.launch {
        noteRepository.insert(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.delete(note)
    }

}
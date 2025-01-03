package com.shaiful.mynote.domain.repositories

import com.shaiful.mynote.data.tables.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun insert(category: Category)
    suspend fun delete(category: Category)
    fun getAllCategories(): Flow<List<Category>>
}
package com.shaiful.mynote.data.repository

import com.shaiful.mynote.data.daos.CategoryDao
import com.shaiful.mynote.data.tables.Category
import com.shaiful.mynote.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {
    override suspend fun insert(category: Category) = categoryDao.insert(category)

    override suspend fun delete(category: Category) = categoryDao.delete(category)

    override fun getAllCategories(): Flow<List<Category>> = categoryDao.getAllCategories()
}
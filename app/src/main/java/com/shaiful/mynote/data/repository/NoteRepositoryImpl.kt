package com.shaiful.mynote.data.repository

import com.shaiful.mynote.data.daos.NoteDao
import com.shaiful.mynote.data.tables.Note
import com.shaiful.mynote.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImp @Inject constructor(
    private val noteDao: NoteDao,
): NoteRepository {
    override suspend fun insert(note: Note) = noteDao.insert(note)

    override suspend fun delete(note: Note) = noteDao.delete(note)

    override fun getNotesByCategory(categoryId: Int): Flow<List<Note>> = noteDao.getNotesByCategory(categoryId)

    override suspend fun update(note: Note) = noteDao.update(note)

}
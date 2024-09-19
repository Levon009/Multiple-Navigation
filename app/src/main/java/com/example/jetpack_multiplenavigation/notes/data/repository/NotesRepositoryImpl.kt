package com.example.jetpack_multiplenavigation.notes.data.repository

import com.example.jetpack_multiplenavigation.notes.data.data_source.NoteDao
import com.example.jetpack_multiplenavigation.notes.domain.model.Note
import com.example.jetpack_multiplenavigation.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(
    private val dao: NoteDao
) : NotesRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}
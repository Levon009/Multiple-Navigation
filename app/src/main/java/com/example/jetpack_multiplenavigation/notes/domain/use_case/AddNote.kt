package com.example.jetpack_multiplenavigation.notes.domain.use_case

import com.example.jetpack_multiplenavigation.notes.domain.model.InvalidNoteException
import com.example.jetpack_multiplenavigation.notes.domain.model.Note
import com.example.jetpack_multiplenavigation.notes.domain.repository.NotesRepository

class AddNote(
    private val repository: NotesRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of note can't be empty!")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of note can't be empty!")
        }
        repository.insertNote(note)
    }
}
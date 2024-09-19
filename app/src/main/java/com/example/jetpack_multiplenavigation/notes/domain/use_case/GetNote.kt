package com.example.jetpack_multiplenavigation.notes.domain.use_case

import com.example.jetpack_multiplenavigation.notes.domain.model.Note
import com.example.jetpack_multiplenavigation.notes.domain.repository.NotesRepository

class GetNote(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: Int) : Note? {
        return repository.getNoteById(id)
    }
}
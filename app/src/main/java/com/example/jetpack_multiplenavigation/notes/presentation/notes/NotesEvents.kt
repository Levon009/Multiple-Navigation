package com.example.jetpack_multiplenavigation.notes.presentation.notes

import com.example.jetpack_multiplenavigation.notes.domain.model.Note
import com.example.jetpack_multiplenavigation.notes.domain.util.NoteOrder

sealed class NotesEvents {
    data class Order(val noteOrder: NoteOrder) : NotesEvents()
    data class DeleteNote(val note: Note) : NotesEvents()
    data object RestoreNote : NotesEvents()
    data object ToggleOrderSection : NotesEvents()
}
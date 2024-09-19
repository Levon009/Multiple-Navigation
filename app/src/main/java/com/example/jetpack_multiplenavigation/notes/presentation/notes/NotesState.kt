package com.example.jetpack_multiplenavigation.notes.presentation.notes

import com.example.jetpack_multiplenavigation.notes.domain.model.Note
import com.example.jetpack_multiplenavigation.notes.domain.util.NoteOrder
import com.example.jetpack_multiplenavigation.notes.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Data(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)

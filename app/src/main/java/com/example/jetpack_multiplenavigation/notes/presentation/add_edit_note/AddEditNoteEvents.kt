package com.example.jetpack_multiplenavigation.notes.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvents {
    data class EnteredTitle(val value: String) : AddEditNoteEvents()
    data class EnteredContent(val value: String) : AddEditNoteEvents()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteEvents()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteEvents()
    data class ChangeColor(val color: Int) : AddEditNoteEvents()
    object SaveNote : AddEditNoteEvents()
}
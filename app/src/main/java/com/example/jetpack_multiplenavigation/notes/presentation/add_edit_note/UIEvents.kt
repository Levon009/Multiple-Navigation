package com.example.jetpack_multiplenavigation.notes.presentation.add_edit_note

sealed class UIEvents {
    data class ShowSnackBar(val message: String) : UIEvents()
    object SaveNote : UIEvents()
}
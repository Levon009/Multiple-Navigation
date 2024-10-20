package com.example.jetpack_multiplenavigation.notes.domain.use_case

import com.example.jetpack_multiplenavigation.notes.data.repository.TestNotesRepositoryImpl
import com.example.jetpack_multiplenavigation.notes.domain.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddMessageTest {
    private lateinit var addNote: AddNote
    private lateinit var testNotesRepositoryImpl: TestNotesRepositoryImpl

    @Before
    fun setUp() {
        testNotesRepositoryImpl = TestNotesRepositoryImpl()
        addNote = AddNote(testNotesRepositoryImpl)
    }

    @Test
    fun `Add note and check If note added correctly`(): Unit = runBlocking {
        val note = Note(
            title = "",
            content = "",
            timestamp = System.currentTimeMillis(),
            color = 0
        )

        addNote(note)

        val notes = testNotesRepositoryImpl.getNotes().first()

        assertThat(notes).contains(note)
    }
}
package com.example.jetpack_multiplenavigation.notes.di

import android.content.Context
import com.example.jetpack_multiplenavigation.notes.data.data_source.NotesDatabase
import com.example.jetpack_multiplenavigation.notes.data.repository.NotesRepositoryImpl
import com.example.jetpack_multiplenavigation.notes.domain.repository.NotesRepository
import com.example.jetpack_multiplenavigation.notes.domain.use_case.AddNote
import com.example.jetpack_multiplenavigation.notes.domain.use_case.DeleteNote
import com.example.jetpack_multiplenavigation.notes.domain.use_case.GetNote
import com.example.jetpack_multiplenavigation.notes.domain.use_case.GetNotes
import com.example.jetpack_multiplenavigation.notes.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(@ApplicationContext context: Context) : NotesDatabase {
        return NotesDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideNotesRepository(db: NotesDatabase) : NotesRepository {
        return NotesRepositoryImpl(db.notesDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NotesRepository) : NoteUseCases {
        return NoteUseCases(
            addNote = AddNote(repository),
            deleteNote = DeleteNote(repository),
            getNote = GetNote(repository),
            getNotes = GetNotes(repository)
        )
    }
}
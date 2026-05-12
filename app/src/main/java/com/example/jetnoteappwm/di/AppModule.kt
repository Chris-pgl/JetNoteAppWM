package com.example.jetnoteappwm.di

import android.content.Context
import androidx.room.Room
import com.example.jetnoteappwm.data.NoteDatabase
import com.example.jetnoteappwm.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    /**
     * I moduli sono usati per aggiungere vincoli a Hilt.
     * per dire a Hilt come fornire istanze di tipi diversi per creare un database o una rete.
     */

    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDatabaseDao
    = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): NoteDatabase
    = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "notes_db",
        ).fallbackToDestructiveMigration()
        .build(
    )


}
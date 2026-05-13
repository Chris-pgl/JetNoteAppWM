package com.example.jetnoteappwm.repository

import com.example.jetnoteappwm.data.NoteDatabaseDao
import com.example.jetnoteappwm.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao){

    suspend fun addNote(note: Notes) = noteDatabaseDao.insertNote(note)
    suspend fun updateNote(note: Notes) = noteDatabaseDao.updateNote(note)
    suspend fun deleteNote(note: Notes) = noteDatabaseDao.deleteNote(note)
    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()
    fun getAllNotes(): Flow<List<Notes>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()



}
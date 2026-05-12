package com.example.jetnoteappwm.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetnoteappwm.data.NotesDataSource
import com.example.jetnoteappwm.model.Notes

class NoteViewModel : ViewModel() {
    private var noteList = mutableStateListOf<Notes>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }


    fun addNote(notes: Notes){
        noteList.add(notes)
    }

    fun removeNote(notes: Notes){
        noteList.remove(notes)
    }

    fun getAllnotes(): List<Notes>{
        return noteList
    }




}
package com.example.jetnoteappwm.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnoteappwm.data.NotesDataSource
import com.example.jetnoteappwm.model.Notes
import com.example.jetnoteappwm.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val repository: NoteRepository): ViewModel() {

    // con Room è difficile usare mutableState
    //private var noteList = mutableStateListOf<Notes>()
    private val _noteList = MutableStateFlow<List<Notes>>(emptyList())
    val noteList = _noteList.asStateFlow()





    init {
        //noteList.addAll(NotesDataSource().loadNotes())
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect {
                listOfNotes ->
                if (listOfNotes.isNullOrEmpty()) {
                    _noteList.value = emptyList()
                } else {
                    _noteList.value = listOfNotes
                }
            }
        }
    }

     fun addNote(note: Notes) { viewModelScope.launch { repository.addNote(note) }}

     fun updateNote(note: Notes) = viewModelScope.launch { repository.updateNote(note) }

     fun removeNote(note: Notes) = viewModelScope.launch { repository.deleteNote(note) }







}
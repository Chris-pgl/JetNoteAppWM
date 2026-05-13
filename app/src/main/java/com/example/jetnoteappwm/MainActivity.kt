package com.example.jetnoteappwm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetnoteappwm.data.NotesDataSource
import com.example.jetnoteappwm.model.Notes
import com.example.jetnoteappwm.screen.NoteScreen
import com.example.jetnoteappwm.screen.NoteViewModel
import com.example.jetnoteappwm.ui.theme.JetNoteAppWMTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNoteAppWMTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    //TODO finish app for now.
                    //TODO for next implement
                    //TODO - oldpress on note for update
                    //TODO - swipe for delete note
                    //passo il viewModel in due modi diversi
                    //val noteViewModel = viewModel<NoteViewModel>()
                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(modifier = Modifier.padding(innerPadding), noteViewModel)
                }
            }
        }
    }
}


@Composable
fun NotesApp(
    modifier: Modifier = Modifier, noteViewModel: NoteViewModel
) {
    val notesList = noteViewModel.noteList.collectAsState().value

    NoteScreen(
        notes = notesList,
        onAddNote = { noteViewModel.addNote(it) },
        onRemoveNote = { noteViewModel.removeNote(it) })
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetNoteAppWMTheme {
        NoteScreen(notes = emptyList(), onAddNote = {}, onRemoveNote = {})
    }
}
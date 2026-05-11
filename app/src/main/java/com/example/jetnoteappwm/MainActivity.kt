package com.example.jetnoteappwm

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnoteappwm.data.NotesDataSource
import com.example.jetnoteappwm.model.Notes
import com.example.jetnoteappwm.screen.NoteScreen
import com.example.jetnoteappwm.ui.theme.JetNoteAppWMTheme
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNoteAppWMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                ){ innerPadding ->
                    NoteScreen(
                        modifier = Modifier.padding(innerPadding),
                        notes = NotesDataSource().loadNotes(),
                        onAddNote = {},
                        onRemoveNote = {})

                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetNoteAppWMTheme {
        NoteScreen(notes = emptyList(), onAddNote = {}, onRemoveNote = {})
    }
}
package com.example.jetnoteappwm.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnoteappwm.R
import com.example.jetnoteappwm.components.NoteButton
import com.example.jetnoteappwm.components.NoteInputText
import com.example.jetnoteappwm.data.NotesDataSource
import com.example.jetnoteappwm.model.Notes
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    notes: List<Notes>,
    onAddNote: (Notes) -> Unit,
    onRemoveNote: (Notes) -> Unit
){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .padding(6.dp)) {
        TopAppBar(title = {
            // Text(stringResource(id = R.string.app_name))  //prendo il nome dal res. app string name
            Text(text = "JetNoteByChris", color = Color.White)
        }, actions = {
            Icon(Icons.Rounded.Edit, contentDescription = "App Icon", tint = Color.White)
        },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color(0xFF9FC2E3)
            ))
        // context per Toast message
        val context = LocalContext.current
        //TextField
        Column(modifier = modifier.fillMaxWidth().padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,) {
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title,
                label = "Titolo",
                maxLine = 1,
                onTextChange = {
                    /*
                    if ( it.all {
                        char -> char.isLetter() || char.isWhitespace()
                    }) title = it
                }*/
                    title = it
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description,
                label = "Descrizione",
                maxLine = 1,
                onTextChange = {
                    /*
                    if ( it.all {
                                char -> char.isLetter() || char.isWhitespace()
                        }) description = it
                }*/
                    description = it
                }
            )

            NoteButton(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                text = "Save",

                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        //save/add to list
                        onAddNote(Notes(title = title, description = description))
                        //and after clear text
                        title = ""
                        description = ""
                        Toast.makeText(context, "Nota aggiunta", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
        Divider(modifier = Modifier.padding(10.dp))
        //list of notes
        LazyColumn {
            items(notes){ note ->
                NoteRow(note = note,
                    onNoteClicked =
                        { onRemoveNote(note) }
                )
            }
        }
    }
}


@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Notes,
    onNoteClicked: (Notes) -> Unit
){
    Surface(modifier.padding(4.dp)
        .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
        .fillMaxWidth(),
        color = Color(0xFF506D79),
        shadowElevation = 7.dp
    ){
        Column(modifier
            .clickable { onNoteClicked(note) }
            .padding(horizontal = 14.dp, vertical = 6.dp)
        ){
            Text(text = note.title, style = MaterialTheme.typography.titleMedium)
            Text(text = note.description, style = MaterialTheme.typography.titleSmall)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Text(text = note.entryDate.format(
                    DateTimeFormatter.ofPattern("EEE d MMM")))
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}
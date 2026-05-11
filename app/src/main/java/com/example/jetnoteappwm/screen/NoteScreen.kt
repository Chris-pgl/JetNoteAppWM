package com.example.jetnoteappwm.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnoteappwm.R
import com.example.jetnoteappwm.components.NoteButton
import com.example.jetnoteappwm.components.NoteInputText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .padding(6.dp)) {
        TopAppBar(title = {
            Text(stringResource(id = R.string.app_name))
        }, actions = {
            Icon(Icons.Rounded.Notifications, contentDescription = "App Icon")
        },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color(0xFF9DB5CE)))

        //TextField
        Column(modifier = Modifier.fillMaxWidth().padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,) {
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title,
                label = "Title",
                maxLine = 1,
                onTextChange = {
                    if ( it.all {
                        char -> char.isLetter() || char.isWhitespace()
                    }) title = it
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description,
                label = "Description",
                maxLine = 1,
                onTextChange = {
                    if ( it.all {
                                char -> char.isLetter() || char.isWhitespace()
                        }) description = it
                }
            )

            NoteButton(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                text = "Save",
                onClick = { /*TODO*/ })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen()
}
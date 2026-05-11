package com.example.jetnoteappwm.data

import com.example.jetnoteappwm.model.Notes

class NotesDataSource{
    fun loadNotes(): List<Notes>{
        return listOf(
            Notes(title = "Un buongiorno!", description = "Scrivi la prima nota del giorno!"),
            Notes(title = "Android Compose", description = "Applicazione by Chris con Android Compose! :D"),

        )
    }
}
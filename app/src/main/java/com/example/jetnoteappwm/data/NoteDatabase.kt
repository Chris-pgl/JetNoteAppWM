package com.example.jetnoteappwm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetnoteappwm.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao

}
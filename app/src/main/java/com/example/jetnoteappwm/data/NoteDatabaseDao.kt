package com.example.jetnoteappwm.data

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetnoteappwm.model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {
     @Query("Select * from notes_tbl")
     // quando si parla di room, il miglior metodo consigliato e di usare Flow a posto di MutableState
     fun getNotes(): Flow<List<Notes>>

     @Query("Select * from notes_tbl where id =:id")
     suspend fun getNoteById(id: String): Notes

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertNote(note: Notes)

     @Update(onConflict = OnConflictStrategy.REPLACE)
     suspend fun updateNote(note: Notes)

     @Query("Delete from notes_tbl where id =:id")
     suspend fun deleteNoteById(id: String)

     @Query("Delete from notes_tbl")
     suspend fun deleteAll()

     @Delete
     suspend fun deleteNote(note: Notes)





}

package com.example.noteapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.model.Note
@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note:Note)
    @Query("Select * from Note")
    fun getallNotewithLiveData() : LiveData<List<Note>>
    @Update
    suspend fun updateNote(note:Note)
    @Delete
    suspend fun deleteNote(note:Note)
    @Query("SELECT COUNT(*) FROM Note")
    fun getDataCount(): LiveData<Int>
    @Query("SELECT * FROM Note WHERE noteTitle LIKE '%' || :search || '%'")
    fun loadNote(search: String?): LiveData<List<Note>>
}
package com.example.noteapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.model.Note

@Database(entities = [Note::class], exportSchema = false, version = 1)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun getNoteDao() : NoteDao
    companion object {
        private var instance: NoteDatabase? = null
        fun getInstance(context: Context): NoteDatabase = instance ?: synchronized(this) {
            Room.databaseBuilder(context, NoteDatabase::class.java, "note.db").build()
        }
    }
}
package com.example.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo
    val noteTitle: String,
    @ColumnInfo
    val noteDescription: String,
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}

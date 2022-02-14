package com.example.noteapp.adapter.callback

import com.example.noteapp.model.Note

interface NoteItemClick {
    fun onClick(note:Note)
}
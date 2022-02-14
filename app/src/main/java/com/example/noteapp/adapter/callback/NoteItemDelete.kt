package com.example.noteapp.adapter.callback

import com.example.noteapp.model.Note

interface NoteItemDelete {
    fun onDelete(note: Note)
}
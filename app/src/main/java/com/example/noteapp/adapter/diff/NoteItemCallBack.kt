package com.example.noteapp.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.noteapp.model.Note

object NoteItemCallBack : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}
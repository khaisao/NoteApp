package com.example.noteapp.vm

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.db.NoteDatabase
import com.example.noteapp.model.Note
import kotlinx.coroutines.launch

class NoteViewModel(private val application: Application) : ViewModel() {
    private val noteDao = NoteDatabase.getInstance(application).getNoteDao()
    val notes = noteDao.getallNotewithLiveData()
    var searchNote = MutableLiveData<Note>()
    val notesize = noteDao.getDataCount()
    fun addNote(note: Note) {
        viewModelScope.launch {
            noteDao.insertNote(note)
        }
    }
    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteDao.updateNote(note)
        }
    }
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteDao.deleteNote(note)
        }
    }
     fun noteLoad(search : String): LiveData<List<Note>> {
        return noteDao.loadNote(search)
    }

}
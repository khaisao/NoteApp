package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.noteapp.databinding.ActivityAddNoteBinding
import com.example.noteapp.model.Note
import com.example.noteapp.vm.NoteViewModel
import com.example.noteapp.vm.NoteViewModelFactory

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddNoteBinding
    private val viewModel: NoteViewModel by viewModels() {
        NoteViewModelFactory(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("des")
        val noteid = intent.getIntExtra("id",0)
        binding.edtNoteTitle.setText(title)
        binding.edtNoteDes.setText(description)
        binding.tvBack.setOnClickListener {
            finish()
        }
        binding.tvSave.setOnClickListener {
            var notetitle = binding.edtNoteTitle.text.toString()
            var notedescript = binding.edtNoteDes.text.toString()
            if(noteid == -1){
                viewModel.addNote(Note(notetitle,notedescript))
                val intent = Intent(this,MainActivity::class.java )
                startActivity(intent)
                finish()
            }
            else{
                val updateNote = Note(notetitle,notedescript)
                updateNote.id = noteid
                viewModel.updateNote(updateNote)
                val intent = Intent(this,MainActivity::class.java )
                startActivity(intent)
                finish()
            }

        }

    }
}
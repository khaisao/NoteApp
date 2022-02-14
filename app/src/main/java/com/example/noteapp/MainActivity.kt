package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.adapter.NoteListAdapter
import com.example.noteapp.adapter.callback.NoteItemClick
import com.example.noteapp.adapter.callback.NoteItemDelete
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.model.Note
import com.example.noteapp.vm.NoteViewModel
import com.example.noteapp.vm.NoteViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),NoteItemClick,NoteItemDelete {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteListAdapter
    private val viewModel: NoteViewModel by viewModels() {
        NoteViewModelFactory(application)
    }
    val id_default = -1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = NoteListAdapter(this,this)
        binding.rvNote.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNote.adapter = adapter
        viewModel.notes.observe(this) {
            adapter.submitList(it)
        }
        viewModel.notesize.observe(this) {
            binding.noteCount.text = it.toString()
        }
        binding.ivCreateNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            intent.putExtra("id",id_default)
            startActivity(intent)
        }
        var job: Job? = null
        binding.edtSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if(editable.toString() != "") {
                        viewModel.noteLoad(editable.toString()).observe(this@MainActivity){
                            adapter.submitList(it)
                        }
                    }
                    else{
                        viewModel.notes.observe(this@MainActivity) {
                            adapter.submitList(it)
                        }
                    }
                }
            }
        }



    }

    override fun onClick(note: Note) {
        val intent = Intent(this, AddNoteActivity::class.java)
        intent.putExtra("title",note.noteTitle)
        intent.putExtra("des",note.noteDescription)
        intent.putExtra("id",note.id)
        startActivity(intent)
    }

    override fun onDelete(note: Note) {
        viewModel.deleteNote(note)
    }
}
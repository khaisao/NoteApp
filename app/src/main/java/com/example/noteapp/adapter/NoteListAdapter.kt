package com.example.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.adapter.callback.NoteItemClick
import com.example.noteapp.adapter.diff.NoteItemCallBack
import com.example.noteapp.databinding.ItemNoteBinding
import com.example.noteapp.model.Note
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.example.noteapp.adapter.callback.NoteItemDelete


class NoteListAdapter(private val callback:NoteItemClick, private val cb_delete:NoteItemDelete) : ListAdapter<Note, NoteListAdapter.ViewHolder>(NoteItemCallBack) {
    private val viewBinderHelper = ViewBinderHelper()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

        // bỏ ghi chú dòng bên dưới nếu bạn chỉ muốn mở một hàng tại một thời điểm


    inner class ViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            viewBinderHelper.setOpenOnlyOne (true);
            binding.tvNoteTitle.text = note.noteTitle
            binding.tvNoteDes.text = note.noteDescription
            binding.itemNote.setOnClickListener {
                callback.onClick(note)
            }
            if(position==0){
                binding.itemNote.setBackgroundResource(R.drawable.item_border_top)
            }
            if(position==itemCount-1){
                binding.itemNote.setBackgroundResource(R.drawable.item_border_bottom)
            }
            viewBinderHelper.bind(binding.swLayout,note.id.toString())
            binding.llDelete.setOnClickListener {
                cb_delete.onDelete(note)
            }
        }
    }
}
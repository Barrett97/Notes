package com.example.notes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.room.Note
import kotlinx.android.synthetic.main.layout_note_list_item.view.*


class NoteRecyclerAdapter  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var notes: List<Note> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_note_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is NoteViewHolder -> {
                holder.bind(notes[position])
            }
        }
    }

    internal fun setNote(notes: List<Note>) {
        this.notes = notes
    }

    override fun getItemCount() = notes.size

    class NoteViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.textViewTitle
        private val body: TextView = itemView.textViewBody

        fun bind(note: Note) {
            title.text = note.title
            body.text = note.body
        }


    }

}
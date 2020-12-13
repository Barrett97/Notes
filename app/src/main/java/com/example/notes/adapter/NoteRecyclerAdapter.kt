package com.example.notes.adapter


import android.view.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.LayoutNoteListItemBinding
import com.example.notes.room.Note
import com.example.notes.navigation.NavListener


class NoteRecyclerAdapter(private val navListener: NavListener?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    private lateinit var touchHelper: ItemTouchHelper
    var notes: ArrayList<Note> = ArrayList()
    var listener: NavListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return NoteViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.layout_note_list_item, parent, false)
//        )
//        binding = LayoutNoteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)

        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder -> {
                holder.bind(navListener, notes[position])
            }
        }
    }

    fun submitList(noteList: List<Note>) {
        val oldList = this.notes
        val diffResult : DiffUtil.DiffResult = DiffUtil.calculateDiff(
            NoteItemDiffCallBack(
                oldList,
                noteList
            )
        )
        this.notes = noteList as ArrayList<Note>
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = notes.size

    class ViewHolder private constructor(private val binding: LayoutNoteListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(navListener: NavListener?, note: Note) {
            binding.listener = navListener
            binding.note = note
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutNoteListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }

    class NoteItemDiffCallBack(
        var oldNoteList: List<Note>,
        var newNoteList: List<Note>
    ): DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldNoteList.size
        }

        override fun getNewListSize(): Int {
            return newNoteList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldNoteList[oldItemPosition].id == newNoteList[newItemPosition].id)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldNoteList[oldItemPosition] == newNoteList[newItemPosition]
        }

    }
}
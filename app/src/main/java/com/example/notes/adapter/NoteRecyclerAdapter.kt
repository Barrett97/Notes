package com.example.notes.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.DataBindingViewHolder
import com.example.notes.R
import com.example.notes.databinding.LayoutNoteListItemBinding
import com.example.notes.room.Note
import kotlinx.android.synthetic.main.layout_note_list_item.view.*
import com.example.notes.BR.note
import com.example.notes.EditNoteActivity
import kotlinx.coroutines.withContext


class NoteRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {

//    private lateinit var touchHelper: ItemTouchHelper
    var notes: ArrayList<Note> = ArrayList()
    lateinit var binding: LayoutNoteListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return NoteViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.layout_note_list_item, parent, false)
//        )
        binding = LayoutNoteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder -> {
                holder.onBind(notes[position])
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

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        val fromNote = notes[fromPosition]
        notes.remove(fromNote)
        notes.add(toPosition, fromNote)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwiped(position: Int) {
        notes.removeAt(position)
        notifyItemRemoved(position)
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
            return oldNoteList[oldItemPosition].equals(newNoteList[newItemPosition])
        }

    }

    class ViewHolder(dataBinding: LayoutNoteListItemBinding): DataBindingViewHolder<Note>(dataBinding) {

        override fun onBind(t: Note): Unit = with(t) {
            dataBinding.setVariable(note, t)
        }

    }

//        fun setTouchHelper(myItemTouchHelper: ItemTouchHelper) {
//        touchHelper = myItemTouchHelper
//    }
//    inner class NoteViewHolder constructor(itemView: View):
//            RecyclerView.ViewHolder(itemView) {
////            View.OnTouchListener,
////            GestureDetector.OnGestureListener {
//
//        private val title: TextView = itemView.textViewTitle
//        private val body: TextView = itemView.textViewBody
//
//        fun bind(note: Note) {
//            title.text = note.title
//            body.text = note.body
//        }
////        var gestureDetector: GestureDetector = GestureDetector(itemView.context, this)
////        init {
////            itemView.setOnTouchListener(this)
////        }
////
////        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
////            gestureDetector.onTouchEvent(event)
////            return true
////        }
////
////        override fun onDown(e: MotionEvent?): Boolean {
////            return false
////        }
////
////        override fun onShowPress(e: MotionEvent?) {
////
////        }
////
////        override fun onSingleTapUp(e: MotionEvent?): Boolean {
////            return true
////        }
////
////        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
////            return false
////        }
////
////        override fun onLongPress(e: MotionEvent?) {
////              touchHelper.startDrag(this)
////        }
////
////        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
////            return true
////        }
//    }

}

//class NoteViewHolder(binding: LayoutNoteListItemBinding): RecyclerView.ViewHolder(binding.root) {
//
//    fun onBind(note: Note) {
//
//    }
//
//}
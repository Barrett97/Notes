package com.example.notes.adapter

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.room.Note
import kotlinx.android.synthetic.main.layout_note_list_item.view.*


class NoteRecyclerAdapter  : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {

    private lateinit var touchHelper: ItemTouchHelper
    var notes: ArrayList<Note> = ArrayList()
    private lateinit var noteRecyclerAdapter: NoteRecyclerAdapter

    init {

    }

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
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    override fun getItemCount() = notes.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        val fromNote = notes.get(fromPosition)
        notes.remove(fromNote)
        notes.add(toPosition, fromNote)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwiped(position: Int) {
        notes.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setTouchHelper(myItemTouchHelper: ItemTouchHelper) {
        touchHelper = myItemTouchHelper
    }

    inner class NoteViewHolder constructor(itemView: View):
            RecyclerView.ViewHolder(itemView),
            View.OnTouchListener,
            GestureDetector.OnGestureListener {

        private val title: TextView = itemView.textViewTitle
        private val body: TextView = itemView.textViewBody

        var gestureDetector: GestureDetector = GestureDetector(itemView.context, this)

        init {
            itemView.setOnTouchListener(this)
        }

        fun bind(note: Note) {
            title.text = note.title
            body.text = note.body
        }

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            gestureDetector.onTouchEvent(event)
            return true
        }

        override fun onDown(e: MotionEvent?): Boolean {
            return false
        }

        override fun onShowPress(e: MotionEvent?) {

        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return true
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            return false
        }

        override fun onLongPress(e: MotionEvent?) {
//            touchHelper.startDrag(this)
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            return true
        }
    }

}

package com.example.notes.adapter


import android.view.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.LayoutNoteListItemBinding
import com.example.notes.room.Note
import com.example.notes.navigation.NavListener


class NoteRecyclerAdapter(private val navListener: NavListener?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {

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
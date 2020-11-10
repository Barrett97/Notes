package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.adapter.NoteRecyclerAdapter
import com.example.notes.adapter.MyItemTouchHelper
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.navigation.NavListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_note.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 *
 * This fragment displays the notes stored in the NoteViewModel and
 * allows for new notes to be created or existing notes to be edited.
 */
class NoteFragment : Fragment() {

    private val noteViewModel by viewModels<NoteViewModel> { defaultViewModelProviderFactory }
//    private val noteRecyclerAdapter: NoteRecyclerAdapter by lazy { NoteRecyclerAdapter(arrayListOf(), navListener) }
    private lateinit var noteRecyclerAdapter: NoteRecyclerAdapter
    private var navListener: NavListener? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentNoteBinding = inflate(inflater, R.layout.fragment_note, container, false)
        binding.apply {
            lifecycleOwner = this.lifecycleOwner
            listener = navListener
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        setupUI(view)
        initRecyclerView()
        getNotes()
    }

    private fun initRecyclerView() {

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            noteRecyclerAdapter = NoteRecyclerAdapter()
            adapter = noteRecyclerAdapter;
//            val callback = MyItemTouchHelper(noteRecyclerAdapter)
//            val itemTouchHelper = ItemTouchHelper(callback)
//            noteRecyclerAdapter.setTouchHelper(itemTouchHelper)
//            itemTouchHelper.attachToRecyclerView(this)
            ItemTouchHelper(itemTouchHelper).attachToRecyclerView(this)
        }
    }

    private fun getNotes() {

        noteViewModel.liveNotes.observe(viewLifecycleOwner, { notes ->
            if (notes.isNotEmpty()) {
                noteRecyclerAdapter.notes.clear()
            }
            notes?.let { noteList ->
                noteRecyclerAdapter.setNote(noteList)
            }
        })
    }

    private fun setupUI(v: View) {

        v.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_NoteFragment_to_EditNoteFragment)
        }
    }

    private var itemTouchHelper: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            noteViewModel.delete(noteRecyclerAdapter.notes[viewHolder.adapterPosition])
            noteRecyclerAdapter.notifyDataSetChanged()
        }
    }
}
package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
class NoteListFragment : Fragment() {

    private val noteViewModel by viewModels<NoteViewModel> { defaultViewModelProviderFactory }
//    private val noteRecyclerAdapter: NoteRecyclerAdapter by lazy { NoteRecyclerAdapter() }
    private lateinit var noteRecyclerAdapter: NoteRecyclerAdapter
    private var navListener: NavListener? = null
    private lateinit var binding: FragmentNoteBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = inflate(inflater, R.layout.fragment_note, container, false)
        binding.apply {
            // necessary for classes working with livedata?
            lifecycleOwner = viewLifecycleOwner
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

    private fun setupUI(v: View) {
        v.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_NoteFragment_to_EditNoteFragment)
        }
    }

    private fun initRecyclerView() {

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
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
            notes?.let { noteList ->
                noteRecyclerAdapter.submitList(noteList)
            }
        })
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
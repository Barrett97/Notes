package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.adapter.NoteRecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_first.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val noteViewModel by viewModels<NoteViewModel> { defaultViewModelProviderFactory }
    private lateinit var noteRecyclerAdapter: NoteRecyclerAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }

        initRecyclerView()
        getNotes()


    }

    private fun initRecyclerView() {
        requireActivity().recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            noteRecyclerAdapter = NoteRecyclerAdapter()
            adapter = noteRecyclerAdapter;
        }
    }

    private fun getNotes() {
        noteViewModel.liveNotes.observe(viewLifecycleOwner, Observer { notes ->
            notes?.let {
                noteRecyclerAdapter.setNote(it)
            }
        })
    }

}
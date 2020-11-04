package com.example.notes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.adapter.NoteRecyclerAdapter
import com.example.notes.room.NoteEditActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_first.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var noteViewModel: NoteViewModel
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
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            val intent = Intent(activity, NoteEditActivity::class.java)
            startActivity(intent)
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
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.liveNotes.observe(viewLifecycleOwner, Observer { notes ->
            notes?.let {
                noteRecyclerAdapter.setNote(it)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

}
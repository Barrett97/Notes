package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.notes.room.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_second.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NoteFragment : Fragment() {

    private val noteViewModel by viewModels<NoteViewModel> { defaultViewModelProviderFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        noteViewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(NoteViewModel::class.java)

        view.findViewById<FloatingActionButton>(R.id.fabEditNote).setOnClickListener {
            save(view)
        }

    }

    private fun save(view: View) {
        if (editTextTitle.text.isEmpty()) {
            Snackbar.make(view, "Need a title", 1000)
            return
        }

        val note = Note(0, editTextTitle.text.toString(), editTextBody.text.toString())
        noteViewModel.insert(note)

        requireActivity().onBackPressed()
    }


}
package com.example.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notes.room.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_note.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class EditNoteFragment : Fragment() {

    private val noteViewModel by viewModels<NoteViewModel> { defaultViewModelProviderFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI(view)
    }

    private fun setupUI(v: View) {
        v.findViewById<FloatingActionButton>(R.id.fabEditNote).setOnClickListener {
            save(v)
        }
    }

    private fun save(view: View) {
        if (editTextTitle.text.isEmpty() && editTextBody.text.isEmpty()) {
            Snackbar.make(view, "Missing title or note body", 1000).show()
            return
        }

        val note = Note(0, editTextTitle.text.toString().trim(), editTextBody.text.toString().trim())
        noteViewModel.insert(note)
        requireActivity().onBackPressed()
    }

}
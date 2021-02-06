package com.example.notes

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.notes.databinding.FragmentAddNoteBinding
import com.example.notes.room.Note
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_note.*
import java.time.LocalDateTime
import java.util.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddNoteFragment : Fragment() {

    private val noteViewModel by viewModels<NoteViewModel> { defaultViewModelProviderFactory }
/////////////////////
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        val binding : FragmentAddNoteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)
        binding.lifecycleOwner = this

        val id = arguments?.getInt("note_id")
        val year = arguments?.getInt("year")
        val month = arguments?.getInt("month")
        val day = arguments?.getInt("day")
        val hour = arguments?.getInt("hour")
        val min = arguments?.getInt("min")
        val sec = arguments?.getInt("sec")
        val ampm = arguments?.getInt("ampm")


        val noteDate = Calendar.getInstance()
        if (year != null && month != null && day != null
            && hour != null && min != null && sec != null) {
            noteDate.set(year, month, day, hour, min, sec)
        }
        println("year: $year, month: $month, day: $day, hour: $hour, min: $min, sec: $sec")
        initNoteContent(id, binding)

        binding.fabEditNote.setOnClickListener {
            if (id != null) {
                val note = Note(
                    id,
                    editTextTitle.text.toString().trim(),
                    editTextBody.text.toString().trim(),
                    noteDate.timeInMillis,
                    Calendar.getInstance().timeInMillis)

                noteViewModel.edit(note)
                requireActivity().onBackPressed()
            } else {
                val note = Note(
                        0,
                        editTextTitle.text.toString().trim(),
                        editTextBody.text.toString().trim(),
                        Calendar.getInstance().timeInMillis,
                        Calendar.getInstance().timeInMillis)
                noteViewModel.insert(note)
                requireActivity().onBackPressed()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupUI(view)
    }

    private fun initNoteContent(id: Int?, binding: FragmentAddNoteBinding) {
        if (id != null) {
            noteViewModel.getNoteById(id).observe(viewLifecycleOwner, Observer {
                binding.editTextTitle.text = Editable.Factory.getInstance().newEditable( it.title)
                binding.editTextBody.text = Editable.Factory.getInstance().newEditable( it.body)
            })
        }
    }

    // TODO: New notes do not appear at the top of the list
    private fun save(view: View) {
        if (editTextTitle.text.isEmpty() && editTextBody.text.isEmpty()) {
            Snackbar.make(view, "Missing title or note body", 1000).show()
            return
        }

        val note = Note(0, editTextTitle.text.toString().trim(), editTextBody.text.toString().trim(), Calendar.getInstance().timeInMillis, Calendar.getInstance().timeInMillis)
        noteViewModel.insert(note)
        requireActivity().onBackPressed()
    }

}
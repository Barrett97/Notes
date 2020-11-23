package com.example.notes.navigation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.notes.MainActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import kotlinx.android.synthetic.main.content_main.*

class NavListenerImpl(private var activity: Activity) : NavListener {

    override fun mainToAddNote() {
        findNavController(activity, R.id.nav_host_fragment).navigate(R.id.action_NoteFragment_to_EditNoteFragment)
    }

    override fun mainToEditNote(id: String) {
        val args = Bundle()
        args.putString("note_id", id)
        Log.d("note_id", id)
        findNavController(activity, R.id.nav_host_fragment).navigate(R.id.action_NoteFragment_to_EditNoteFragment, args)
    }

}
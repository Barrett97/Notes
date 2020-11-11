package com.example.notes.navigation

import android.app.Activity
import com.example.notes.MainActivity
import androidx.navigation.Navigation.findNavController
import com.example.notes.R
import kotlinx.android.synthetic.main.content_main.*

class NavListenerImpl : NavListener {

    override fun mainToAddNote(a: Activity) {
        findNavController(a, R.id.nav_host_fragment).navigate(R.id.action_NoteFragment_to_EditNoteFragment)
    }

    override fun mainToEditNote() {
        TODO("Not yet implemented")
    }

}
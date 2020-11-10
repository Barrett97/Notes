package com.example.notes.navigation

import com.example.notes.MainActivity
import androidx.navigation.Navigation.findNavController
import com.example.notes.R
import kotlinx.android.synthetic.main.content_main.*

class NavListenerImpl : NavListener {

    private var activity: MainActivity = MainActivity()

    override fun mainToAddNote() {
        findNavController(activity, R.id.nav_host_fragment).navigate(R.id.action_NoteFragment_to_EditNoteFragment)
    }

    override fun mainToEditNote() {
        TODO("Not yet implemented")
    }

}
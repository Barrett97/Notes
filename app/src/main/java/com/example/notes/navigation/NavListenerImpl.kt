package com.example.notes.navigation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation.findNavController
import com.example.notes.R

class NavListenerImpl(private var activity: Activity) : NavListener {

    override fun mainToAddNote() {
        findNavController(activity, R.id.nav_host_fragment).navigate(R.id.action_NoteFragment_to_EditNoteFragment)
    }

    override fun mainToEditNote(id: Int) {
        val args = Bundle()
        args.putInt("note_id", id)
        Log.d("note_id", id.toString())
        findNavController(activity, R.id.nav_host_fragment).navigate(R.id.action_NoteFragment_to_EditNoteFragment, args)
    }

}
package com.example.notes.navigation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation.findNavController
import com.example.notes.R
import java.text.SimpleDateFormat
import java.util.*

class NavListenerImpl(private var activity: Activity) : NavListener {

    override fun mainToAddNote() {
        findNavController(activity, R.id.nav_host_fragment).navigate(R.id.action_NoteFragment_to_EditNoteFragment)
    }

    override fun mainToEditNote(id: Int, date: Long) {
        var cal = Calendar.getInstance()
        cal.timeInMillis = date
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DATE)
        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val min = cal.get(Calendar.MINUTE)
        val sec = cal.get(Calendar.SECOND)
        val ampm = cal.get(Calendar.AM_PM)

        val args = Bundle().apply {
            putInt("note_id", id)
            putInt("year", year)
            putInt("month", month)
            putInt("day", day)
            putInt("hour", hour)
            putInt("min", min)
            putInt("sec", sec)
            putInt("ampm", ampm)
        }

        Log.d("note_id", id.toString())
        findNavController(activity, R.id.nav_host_fragment).navigate(R.id.action_NoteFragment_to_EditNoteFragment, args)
    }

}
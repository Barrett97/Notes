package com.example.notes.navigation

import android.app.Activity
import com.example.notes.room.Note

interface NavListener {

    fun mainToAddNote()
    fun mainToEditNote(id: String)

}
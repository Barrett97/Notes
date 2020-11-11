package com.example.notes.navigation

import android.app.Activity

interface NavListener {

    fun mainToAddNote(a: Activity)
    fun mainToEditNote()

}
package com.example.notes.navigation

import java.util.*

interface NavListener {

    fun mainToAddNote()
    fun mainToEditNote(id: Int, date: Long)

}
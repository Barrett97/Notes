package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.room.NoteRepo
import com.example.notes.room.Note
import com.example.notes.room.NoteDB
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepo: NoteRepo
    val liveNotes: LiveData<List<Note>>

    init {
        val noteDao = NoteDB.getDatabase(application).noteDao
        noteRepo = NoteRepo(noteDao)
        liveNotes = noteRepo.liveNotes
    }

    fun insert(note: Note) = viewModelScope.launch {
        noteRepo.insert(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        noteRepo.delete(note)
    }

}
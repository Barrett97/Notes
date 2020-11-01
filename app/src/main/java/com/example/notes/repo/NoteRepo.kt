package com.example.notes.repo

import androidx.lifecycle.LiveData
import com.example.notes.room.Note
import com.example.notes.room.NoteDao

class NoteRepo(private val noteDao: NoteDao) {

    val liveNotes: LiveData<List<Note>> = noteDao.getAll()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}
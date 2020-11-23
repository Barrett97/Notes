package com.example.notes.room

import androidx.lifecycle.LiveData
import io.reactivex.Single

class NoteRepo(private val noteDao: NoteDao) {

    val liveNotes: LiveData<List<Note>> = noteDao.getAll()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun edit(note: Note) {
        noteDao.edit(note)
    }

    fun getNoteById(id: String): Single<Note> {
        return noteDao.getNoteById(id)
    }
}
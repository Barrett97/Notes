package com.example.notes

import android.app.Application
import androidx.lifecycle.*
import com.example.notes.room.NoteRepo
import com.example.notes.room.Note
import com.example.notes.room.NoteDB
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepo: NoteRepo = NoteRepo(NoteDB.getDatabase(application).noteDao)

    private val _liveNotes: LiveData<List<Note>> = noteRepo.liveNotes
    val liveNotes: LiveData<List<Note>> = _liveNotes

    fun insert(note: Note) = viewModelScope.launch {
        noteRepo.insert(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        noteRepo.delete(note)
    }

    fun edit(note: Note) = viewModelScope.launch {
        noteRepo.edit(note)
    }

    fun getNoteById(id: Int): LiveData<Note> {
        return noteRepo.getNoteById(id)
            .flatMap { Single.just(it) }
            .subscribeOn(Schedulers.io())
            .to { LiveDataReactiveStreams.fromPublisher(it.toFlowable()) }
    }

}
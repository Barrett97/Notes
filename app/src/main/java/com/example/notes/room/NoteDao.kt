package com.example.notes.room

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Single

@Dao
interface NoteDao {

    @Query("SELECT * FROM table_notes ORDER BY id Desc")
    fun getAll(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(n: Note)

    @Delete
    suspend fun delete(n: Note)

    @Update
    suspend fun edit(n: Note)

    @Query("SELECT * FROM table_notes WHERE id = (:id)")
    fun getNoteById(id: String): Single<Note>
}

package com.example.notes.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDB : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {

        private lateinit var INSTANCE: NoteDB

        fun getDatabase(context: Context): NoteDB {
            synchronized(NoteDB::class) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room
                            .databaseBuilder(
                                    context.applicationContext,
                                    NoteDB::class.java,
                                    "table_notes"
                            )
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

    }
}
package com.example.notes.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "table_notes")
data class Note (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo (name = "title") val title: String?,
    @ColumnInfo (name = "body") val body: String?
//    @ColumnInfo (name = "dateCreated") val date: Date,
//    @ColumnInfo (name = "lastEdited") val lastEdited: Date
)
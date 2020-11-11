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
) {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) {
            return false
        }

        other as Note
        if (id != other.id || title != other.title || body != other.body) {
            return false
        }
        return true
    }
}
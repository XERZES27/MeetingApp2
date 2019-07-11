package com.example.meetingapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meetings")
data class Meeting (
        val description: String,
        @PrimaryKey(autoGenerate = true) val id: Int,
        val title: String
)

package com.example.meetingapp.data

import androidx.room.*

@Entity(tableName = "messages",
        foreignKeys = [ForeignKey(entity = Meeting::class, parentColumns = ["id"], childColumns =  ["meeting_id"])],
        indices = [Index("meeting_id")]
    )
data class Message(@PrimaryKey(autoGenerate = true) val id :Long,
                   val body:String,
                   @ColumnInfo(name = "meeting_id") val meeting_id: Long,
                   @ColumnInfo(name = "sender_id") val sender_id :Long
)
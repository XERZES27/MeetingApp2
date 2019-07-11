package com.example.meetingapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "registered_users",
    foreignKeys = [ForeignKey(entity = Meeting::class, parentColumns = ["id"], childColumns = ["meeting_id"] , onDelete = ForeignKey.RESTRICT), ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["user_id"], onDelete = ForeignKey.RESTRICT)]
)
data class RegisteredUsers(
    @ColumnInfo(name = "meeting_id") val meeting_id: Long,
    @ColumnInfo(name = "user_id") val user_id : Long
)

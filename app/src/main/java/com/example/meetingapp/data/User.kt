package com.example.meetingapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User (
    val email: String,
    val id: Int?,
    val name: String,
    val password: String?
                 ):Serializable
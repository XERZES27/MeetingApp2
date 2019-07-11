package com.example.meetingapp.data_


data class M(
    val description: String,
    val id: Int,
    val title: String,
    val users: List<User>
)
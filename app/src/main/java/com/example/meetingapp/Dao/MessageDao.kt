package com.example.meetingapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.meetingapp.data.Message

@Dao
interface MessageDao {

    @Query("select * from messages  order by id")
    fun getAllMessages( meeting_id : Long) : LiveData<List<Message>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createMessage(message: Message) : Long

    @Update
    fun updateMessage(message: Message) : Int

    @Delete
    fun deleteMessage(message: Message) : Int
}
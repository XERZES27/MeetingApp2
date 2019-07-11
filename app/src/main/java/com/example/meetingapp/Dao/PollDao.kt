package com.example.meetingapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.meetingapp.data.Poll


@Dao
interface PollDao {

    @Query("select * from polls where id = :id")
    fun getAPoll(id:Long) : LiveData<Poll>

   @Update
   fun updatePoll(poll: Poll) : Int

    @Update
    fun closePoll(poll: Poll) :Int

    @Insert
    fun createPoll(poll: Poll) : Long


}
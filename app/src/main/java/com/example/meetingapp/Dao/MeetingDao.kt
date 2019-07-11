package com.example.meetingapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.meetingapp.data.Meeting
@Dao
interface MeetingDao {

    @Query("SELECT * from meetings ORDER BY id")
    fun getAllMeetings(): LiveData<List<Meeting>>

    @Query("SELECT * from meetings where id = :id")
    fun meetingById(id:Long):LiveData<Meeting>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeeting(meeting: Meeting):Long

    @Delete
    fun deleteMeeting(meeting: Meeting) : Int

    @Update
    fun updateMeeting(meeting: Meeting) : Int


}
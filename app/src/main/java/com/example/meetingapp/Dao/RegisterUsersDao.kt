package com.example.meetingapp.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meetingapp.data.RegisteredUsers


@Dao
interface RegisterUsersDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun registerUsersToAMeeting( registeredUsers: RegisteredUsers):Long

    @Query("delete from registered_users where user_id = :user_id")
    fun unregisterAUserFromAllMeeting(user_id :Long):Int

    @Query("delete from registered_users where meeting_id = :meeting_id")
    fun unregisterAllUsersFromAMeetings(meeting_id : Long):Int

    @Query("delete from registered_users where user_id = :user_id and meeting_id = :meeting_id")
    fun unregisterAuserFromAMeeting(user_id: Long, meeting_id: Long) : Int
}
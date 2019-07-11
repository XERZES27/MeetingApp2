package com.example.meetingapp.repository

import androidx.lifecycle.LiveData
import com.example.meetingapp.Dao.MeetingDao
import com.example.meetingapp.Service.MeetingApiService
import com.example.meetingapp.data.Meeting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MeetingRepository(private val meetingApiService: MeetingApiService, private  val meetingDao: MeetingDao) {
    //room
    fun AllMeetings():LiveData<List<Meeting>>{
        return meetingDao.getAllMeetings()
    }

    fun AMeeting(id:Long):LiveData<Meeting>{
        return meetingDao.meetingById(id)

    }

    fun insertMeeting(meeting: Meeting):Long{
        meetingDao.insertMeeting(meeting)
        return 1
    }



    //api
    suspend fun getAllMeetings(): Response<List<Meeting>> =
        withContext(Dispatchers.IO){
            meetingApiService.getAllMeetings().await()
        }

    suspend fun getAMeeting(id:Long):Response<Meeting> =
        withContext(Dispatchers.IO){
            meetingApiService.getAMeeting(id).await()
    }

    suspend fun addMeeting(meeting:Meeting,token:String): Response<Meeting> =
        withContext(Dispatchers.IO){
            meetingApiService.addMeeting(meeting,token).await()
        }

    suspend fun registerForMeeting(user_id:Long,meeting_id:Long,token:String):Response<Meeting> =
        withContext(Dispatchers.IO){
            meetingApiService.registerForMeeting(user_id,meeting_id,token).await()
        }

    suspend fun updateMeeting(id:Long,token:String):Response<Meeting> =
        withContext(Dispatchers.IO){
            meetingApiService.updateMeeting(id,token).await()
        }








}
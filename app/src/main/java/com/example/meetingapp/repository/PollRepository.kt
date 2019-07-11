package com.example.meetingapp.repository

import androidx.lifecycle.LiveData
import com.example.meetingapp.Dao.PollDao
import com.example.meetingapp.Service.PollApiService
import com.example.meetingapp.data.Poll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PollRepository(private val pollApiService: PollApiService,private val pollDao: PollDao) {
//    room
    fun getAPoll(id:Long):LiveData<Poll>{
        return pollDao.getAPoll(id)
    }

    fun updatePoll(poll:Poll):Int{
        return pollDao.updatePoll(poll)
    }

    fun closePoll(poll: Poll):Int{
        return pollDao.closePoll(poll)
    }

    fun createPoll(poll: Poll):Long{
        return pollDao.createPoll(poll)
    }

//    api

    suspend fun getPoll(id:Long): Response<Poll> =
        withContext(Dispatchers.IO){
            pollApiService.getAPoll(id).await()
        }

    suspend fun vote(answer:String):Response<Poll> =
        withContext(Dispatchers.IO){
            pollApiService.vote(answer).await()
        }

    suspend fun close():Response<String> =
        withContext(Dispatchers.IO){
            pollApiService.close().await()
        }

    suspend fun createpoll(poll:Poll):Response<Poll> =
        withContext(Dispatchers.IO){
            pollApiService.createPoll(poll).await()
        }




}
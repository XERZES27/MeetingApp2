package com.example.meetingapp.repository

import androidx.lifecycle.LiveData
import com.example.meetingapp.Dao.MessageDao
import com.example.meetingapp.Service.MessageApiService
import com.example.meetingapp.data.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MessageRepository(private val messageApiService: MessageApiService, private val messageDao: MessageDao) {

    //room
    fun getAllMessages(meeting_id:Long):LiveData<List<Message>>{
        return messageDao.getAllMessages(meeting_id)
    }

    fun createMessage(message:Message):Long{
        return messageDao.createMessage(message)
    }

    fun updateMessage(message:Message):Int{
        return messageDao.updateMessage(message)
    }

    fun deleteMessage(message:Message):Int{
        return messageDao.deleteMessage(message)
    }








    //api
    suspend fun getAllMessages(token: String): Response<List<Message>> =
        withContext(Dispatchers.IO){
            messageApiService.getAllMessages(token).await()
        }

    suspend fun createAMessage(token: String,message: Message):Response<Message> =
        withContext(Dispatchers.IO){
            messageApiService.createAMessage(token,message).await()
        }

}
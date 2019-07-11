package com.example.meetingapp.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.meetingapp.Service.MessageApiService
import com.example.meetingapp.data.Message
import com.example.meetingapp.db.MeetingDatabase
import com.example.meetingapp.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MessageViewModel(application: Application):AndroidViewModel(application){
    val messageRepository:MessageRepository

    init {
        val messageApiService = MessageApiService.getInstance()
        val messageDao = MeetingDatabase.getDatabase(application).messageDao()
        messageRepository = MessageRepository(messageApiService,messageDao)
    }
    private val _AllMessages = MutableLiveData<Response<List<Message>>>()
    val AllMessages: LiveData<Response<List<Message>>>
    get() = _AllMessages

    private val _CreateMessage=MutableLiveData<Response<Message>>()
            val CreateMessage:LiveData<Response<Message>>
    get() = _CreateMessage



    //api
    fun getAllMessages(token:String)= viewModelScope.launch {
        _AllMessages.postValue(messageRepository.getAllMessages(token))
    }

    fun createAMessage(token:String,message: Message) = viewModelScope.launch {
        _CreateMessage.postValue(messageRepository.createAMessage(token,message))
    }


    //room
    suspend fun getAllMessages(meeting_id:Long):LiveData<List<Message>> =
        withContext(Dispatchers.IO){
            messageRepository.getAllMessages(meeting_id)
        }

    suspend fun createMessage(message: Message):Long =
        withContext(Dispatchers.IO){
            messageRepository.createMessage(message)
        }

    suspend fun updateMessage(message: Message):Int =
        withContext(Dispatchers.IO){
            messageRepository.updateMessage(message)
        }

    suspend fun deleteMessage(message:Message):Int =
        withContext(Dispatchers.IO){
            messageRepository.deleteMessage(message)
        }




















}
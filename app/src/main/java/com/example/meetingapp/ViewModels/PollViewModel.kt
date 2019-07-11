package com.example.meetingapp.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.meetingapp.Service.PollApiService
import com.example.meetingapp.data.Poll
import com.example.meetingapp.db.MeetingDatabase
import com.example.meetingapp.repository.PollRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class PollViewModel (application: Application):AndroidViewModel(application){
    val pollRepository:PollRepository

    init {
        val pollApiService = PollApiService.getInstance()
        val pollDao = MeetingDatabase.getDatabase(application).pollDao()
        pollRepository = PollRepository(pollApiService,pollDao)
    }

    //api
    private val _getApoll=MutableLiveData<Response<Poll>>()
    val getAPoll:LiveData<Response<Poll>>
    get() = _getApoll

    private val _vote = MutableLiveData<Response<Poll>>()
    val vote:LiveData<Response<Poll>>
    get()  = _vote

    private  val _close = MutableLiveData<Response<String>>()
    val close:LiveData<Response<String>>
    get() = _close

    private val _createAPoll = MutableLiveData<Response<Poll>>()
    val createAPoll:LiveData<Response<Poll>>
    get() = _createAPoll


    fun getAPoll(id:Long) = viewModelScope.launch {

        _getApoll.postValue(pollRepository.getPoll(id))
    }

    fun Vote(answer:String) = viewModelScope.launch {
        _vote.postValue(pollRepository.vote(answer))
    }

    fun close() = viewModelScope.launch {
        _close.postValue(pollRepository.close())
    }

    fun createApoll(poll:Poll) = viewModelScope.launch {
        _createAPoll.postValue(pollRepository.createpoll(poll))
    }

    //room
    suspend fun getApoll(id:Long):LiveData<Poll> =
        withContext(Dispatchers.IO){
            pollRepository.getAPoll(id)
        }

    suspend fun updatePoll(poll:Poll):Int =
        withContext(Dispatchers.IO){
            pollRepository.updatePoll(poll)
        }

    suspend fun closePoll(poll:Poll):Int =
        withContext(Dispatchers.IO){
            pollRepository.closePoll(poll)
        }

    suspend fun createPoll(poll: Poll):Long =
        withContext(Dispatchers.IO){
            pollRepository.createPoll(poll)
        }
















}
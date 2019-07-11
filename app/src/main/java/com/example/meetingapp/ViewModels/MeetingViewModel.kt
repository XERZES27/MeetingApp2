package com.example.meetingapp.ViewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.meetingapp.Service.MeetingApiService
import com.example.meetingapp.data.Meeting
import com.example.meetingapp.db.MeetingDatabase
import com.example.meetingapp.repository.MeetingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MeetingViewModel(application:Application):AndroidViewModel(application) {
    val meetingRepository: MeetingRepository

    init {
        val meetingApiService = MeetingApiService.getInstance()

        val meetingDao = MeetingDatabase.getDatabase(application).meetingDao()
         meetingRepository = MeetingRepository(meetingApiService,meetingDao)
    }
    private  val _getAllMeeting = MutableLiveData<Response<List<Meeting>>>()
    val getAllMeeting: LiveData<Response<List<Meeting>>>
        get() = _getAllMeeting

    private val _getAMeeting = MutableLiveData<Response<Meeting>>()
    val getAMeeting: LiveData<Response<Meeting>>
        get() = _getAMeeting

    private val _addMeeting = MutableLiveData<Response<Meeting>>()
    val addMeeting: LiveData<Response<Meeting>>
        get() = _addMeeting

    private val _registerForMeeting = MutableLiveData<Response<Meeting>>()
    val registerForMeeting: LiveData<Response<Meeting>>
        get() = _registerForMeeting

    //api
    private val _updateMeeting = MutableLiveData<Response<Meeting>>()
    val updateMeeting: LiveData<Response<Meeting>>
        get() = _updateMeeting



    fun getAllMeetings() = viewModelScope.launch{
        _getAllMeeting.postValue(meetingRepository.getAllMeetings())


    }
 
    fun getAMeetings(id:Long) = viewModelScope.launch {

        _getAMeeting.postValue(meetingRepository.getAMeeting(id))
    }

    fun addMeetings(meeting:Meeting,token:String) = viewModelScope.launch {
        _addMeeting.postValue(meetingRepository.addMeeting(meeting,token))
    }

    fun registerForMeetings(user_id:Long,meeting_id:Long,token:String) = viewModelScope.launch {
        _registerForMeeting.postValue(meetingRepository.registerForMeeting(user_id,meeting_id,token))
    }

    fun updateMeetings(id:Long,token:String) = viewModelScope.launch {
        _updateMeeting.postValue(meetingRepository.updateMeeting(id,token))
    }



    //room
    suspend fun AllMeeting():LiveData<List<Meeting>> =
        withContext(Dispatchers.IO){
            meetingRepository.AllMeetings()
        }

    suspend fun AMeeting(id:Long):LiveData<Meeting> =
        withContext(Dispatchers.IO){
            meetingRepository.AMeeting(id)
        }

    suspend fun insert(meeting: Meeting):Long =
        withContext(Dispatchers.IO){
            meetingRepository.insertMeeting(meeting)
        }









}
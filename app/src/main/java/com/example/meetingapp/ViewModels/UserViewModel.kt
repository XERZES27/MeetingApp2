package com.example.meetingapp.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.meetingapp.Service.UserApiService
import com.example.meetingapp.data.Token
import com.example.meetingapp.data.User
import com.example.meetingapp.db.MeetingDatabase
import com.example.meetingapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserViewModel(application:Application):AndroidViewModel(application){
    val userRepository:UserRepository


    init {
        val userApiService = UserApiService.getInstance()
        val userDao = MeetingDatabase.getDatabase(application).userDao()
        userRepository  = UserRepository(userApiService,userDao)
    }

    private val _getAllUsers = MutableLiveData<Response<List<User>>>()
    val getAllUsers:LiveData<Response<List<User>>>
        get() = _getAllUsers

    private val _getAUser=MutableLiveData<Response<User>>()
    val getAUser:LiveData<Response<User>>
        get() = _getAUser

    private val _signin=MutableLiveData<Response<Token>>()
    val signin:LiveData<Response<Token>>
    get() = _signin

    private val _singup = MutableLiveData<Response<User>>()
    val signup:LiveData<Response<User>>
    get() = _singup

    private val _deleteUser = MutableLiveData<Response<Int>>()
    val deleteUser:LiveData<Response<Int>>
    get() = _deleteUser

    private val _updateUser = MutableLiveData<Response<Int>>()
    val updateUser:LiveData<Response<Int>>
        get() = _updateUser

    private val _insertUser = MutableLiveData<Response<Int>>()
    val insertUser:LiveData<Response<Int>>
        get() = _insertUser


    fun signIn(user: User) = viewModelScope.launch {
        _signin.postValue(userRepository.signIn(user))

    }

    fun signUp(user: User) = viewModelScope.launch {
        val currentUser = userRepository.signUp(user)
        _singup.postValue(currentUser)

    }

    suspend fun getAllUsers():LiveData<List<User>> =
        withContext(Dispatchers.IO){
            userRepository.AllUsers()
        }

    suspend fun getAUser(email:String,password:String):LiveData<User> =
        withContext(Dispatchers.IO){
            userRepository.getAUser(email,password)
        }

    suspend fun deleteUser(user: User):Int =
        withContext(Dispatchers.IO){
        userRepository.deleteUsers(user)

    }


    suspend fun updateUsers(user:User):Int =
        withContext(Dispatchers.IO){
            userRepository.updateUsers(user)
        }

    suspend fun insertUsers(user: User):Long =
        withContext(Dispatchers.IO){
            userRepository.insertUsers(user)
        }

















}
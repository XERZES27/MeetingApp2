package com.example.meetingapp.repository

import androidx.lifecycle.LiveData
import com.example.meetingapp.Dao.UserDao
import com.example.meetingapp.Service.UserApiService
import com.example.meetingapp.data.Token
import com.example.meetingapp.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository(private val userApiService: UserApiService, private val userDao: UserDao){




    fun AllUsers():LiveData<List<User>>{
        return userDao.getAllUsers();
    }

    fun getAUser(email:String,password:String):LiveData<User>{
        return userDao.getAUser(email,password);
    }


    fun deleteUsers(user: User):Int{
        return userDao.deleteUser(user);
    }

    fun updateUsers(user:User):Int{

       return userDao.updateUser(user);
    }

    fun insertUsers(user:User):Long{
        return userDao.insertUser(user);}




    suspend fun signUp(user:User): Response<User> =
        withContext(Dispatchers.IO){
            userApiService.signup(user).await()
        }

    suspend fun signIn(user:User): Response<Token> =
        withContext(Dispatchers.IO){
            userApiService.signin(user).await()
        }






}

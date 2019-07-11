package com.example.meetingapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.meetingapp.data.User


@Dao
interface UserDao {

    @Query("select * from users order by id")
    fun getAllUsers() : LiveData<List<User>>

//    @Query("select * from users where id = :id")
//    fun getAUser(id:Long) : LiveData<User>
    @Query("select * from users where email = :email and password = :password")
    fun getAUser(email : String, password : String): LiveData<User>

    @Delete
    fun deleteUser(user: User) : Int
    @Update
    fun updateUser(user: User) : Int
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User) : Long

}
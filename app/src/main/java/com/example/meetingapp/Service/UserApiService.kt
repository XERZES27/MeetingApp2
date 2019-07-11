package com.example.meetingapp.Service

import com.example.meetingapp.data.User
import com.example.meetingapp.data.Token
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {

    @POST("/user")
    fun signup(@Body user: User) : Deferred<Response<User>>

    @POST("/user/signin")
    fun signin(@Body user: User) : Deferred<Response<Token>>



    companion object{
        private val baseUrl = "http://localhost:8080/api/v1/"
        fun getInstance(): UserApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
            return retrofit.create(UserApiService::class.java)
        }
    }

}

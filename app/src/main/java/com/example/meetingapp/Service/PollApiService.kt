package com.example.meetingapp.Service

import com.example.meetingapp.data.Poll
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface PollApiService {

    @GET("/poll/{id}")
    fun getAPoll(@Path("id") id: Long) : Deferred<Response<Poll>>

    @PATCH("/poll/vote/{id}")
    fun vote( @Body answer : String) : Deferred<Response<Poll>>

    @PATCH("/poll/close/{id}")
    fun close( ) : Deferred<Response<String>>

    @POST("/poll")
    fun createPoll(@Body poll: Poll) : Deferred<Response<Poll>>


    companion object{
        private val baseUrl = "http://localhost:8080/api/v1/"
        fun getInstance(): PollApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
            return retrofit.create(PollApiService::class.java)
        }
    }

}
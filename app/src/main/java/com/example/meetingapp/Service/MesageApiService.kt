package com.example.meetingapp.Service

import com.example.meetingapp.data.Message
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MessageApiService {

@GET("messages?=token={token}")
fun getAllMessages(@Query("token") token: String):Deferred<Response<List<Message>>>

   @POST("message?=token={token}")
   fun createAMessage(@Query("token") token: String, @Body message: Message):Deferred<Response<Message>>





   companion object{
      private val baseUrl = "http://localhost:8080/api/v1/"
      fun getInstance(): MessageApiService {
         val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
         return retrofit.create(MessageApiService::class.java)
      }
   }
}
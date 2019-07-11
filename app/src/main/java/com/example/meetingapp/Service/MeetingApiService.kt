package com.example.meetingapp.Service

import com.example.meetingapp.data.Meeting
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface MeetingApiService {

    @GET("/meeting")
    fun getAllMeetings(): Deferred<Response<List<Meeting>>>

    @GET("/meeting/{id}")
    fun getAMeeting(@Path("id") id:Long) : Deferred<Response<Meeting>>

    @POST("/meeting?token={token}")
    fun addMeeting(@Body meeting: Meeting ,@Query("token") token:String) : Deferred<Response<Meeting>>

//    @DELETE("/meeting/{id}?token={token}")
//    fun deleteMeeting(@Path("id") id: Long ,@Query("token") token:String) : Deferred<Response<Meeting>>

    @POST("/meeting/registration?token={token}")
    fun registerForMeeting(@Body user_id: Long, @Body meeting_id: Long ,@Query("token") token:String) : Deferred<Response<Meeting>>

    @DELETE("/meeting/registration/{id}?token={token}")
    fun unregisterFromMeeting(@Path("id") id: Long ,@Query("token") token:String) : Deferred<Response<Meeting>>

    @PUT("/meeting/{id?token={token}}")
    fun updateMeeting(@Path("id") id: Long ,@Query("token") token:String): Deferred<Response<Meeting>>

    abstract fun meetingApiService() : MeetingApiService
    abstract fun messaageApiService() : MessageApiService
    abstract fun userApiService() : UserApiService

    companion object{
        private val baseUrl = "http://localhost:8080/api/v1/"
        fun getInstance(): MeetingApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
            return retrofit.create(MeetingApiService::class.java)
        }
    }

}
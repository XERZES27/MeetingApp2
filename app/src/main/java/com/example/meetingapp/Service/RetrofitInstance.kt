package com.example.meetingapp.Service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class RetrofitInstance {

    abstract fun meetingApiService() : MeetingApiService
    //abstract fun messaageApiService() : MesageApiService
    abstract fun userApiService() : UserApiService

    companion object{
        private val baseUrl = "http://localhost:8080/api/v1/"
        fun getInstance(): RetrofitInstance {
            val retrofit = Retrofit.Builder()
                                           .baseUrl(baseUrl)
                                           .addConverterFactory(MoshiConverterFactory.create())
                                           .addCallAdapterFactory(CoroutineCallAdapterFactory())
                                           .build()
            return retrofit.create(RetrofitInstance::class.java)
        }
    }
}

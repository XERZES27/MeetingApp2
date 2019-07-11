package com.example.meetingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.meetingapp.Dao.MeetingDao
import com.example.meetingapp.Dao.MessageDao
import com.example.meetingapp.Dao.PollDao
import com.example.meetingapp.Dao.UserDao
import com.example.meetingapp.data.Meeting

@Database(entities = arrayOf(Meeting::class),version = 1)
abstract class MeetingDatabase :RoomDatabase(){
    abstract fun meetingDao():MeetingDao
    abstract fun userDao(): UserDao;
    abstract fun messageDao():MessageDao
    abstract fun pollDao():PollDao

    companion object {

        @Volatile
        private var INSTANCE: MeetingDatabase? = null

        fun getDatabase(context: Context): MeetingDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(context.applicationContext, MeetingDatabase::class.java, "meeting_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }






}
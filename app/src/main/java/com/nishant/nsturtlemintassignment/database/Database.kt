package com.nishant.nsturtlemintassignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [IssueData::class, CommentData::class], version = 1, exportSchema = false)
abstract class IssueDatabase : RoomDatabase() {

    abstract fun dao() : Dao

    companion object{

        //Singleton prevents multiple instances of DB to open at the same time
        @Volatile
        private var INSTANCE : IssueDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(
            context : Context
        ) : IssueDatabase {
            // if instance is not null then retuen it
            // if null, then create DB

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IssueDatabase::class.java,
                    "local_DB"
                ).build()
                INSTANCE = instance
                //return instance
                instance
            }
        }
    }
}
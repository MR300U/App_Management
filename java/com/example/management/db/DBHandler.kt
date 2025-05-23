package com.example.management.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.management.db.dao.TaskDao
import com.example.management.db.model.TaskEntity

@Database(entities = [TaskEntity::class], version = DBHandler.DATABASE_VERSION)
abstract class DBHandler : RoomDatabase() {

    abstract fun taskDao():TaskDao


    companion object{

        const val DATABASE_NAME = "mainDatabase"
        const val DATABASE_VERSION = 1

        const val TASK_TABLE = "taskTable"

        private var INSTANCE : DBHandler? = null

        fun getDatabase(context: Context): DBHandler {

            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(context
                    ,DBHandler::class.java
                    , DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            return INSTANCE!!

        }

    }

}
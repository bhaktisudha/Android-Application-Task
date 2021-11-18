package com.bhaktisudha.androidapplicationtask.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bhaktisudha.androidapplicationtask.resultModel.ResultModelItem

@Database(entities = [ResultModelItem::class], version = 1)
abstract class ResultDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao

    companion object {
        @Volatile
        private var INSTANCE: ResultDatabase? = null

        fun getDatabase(context: Context): ResultDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResultDatabase::class.java,
                    "result_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
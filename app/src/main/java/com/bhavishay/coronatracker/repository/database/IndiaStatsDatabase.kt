package com.bhavishay.coronatracker.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bhavishay.coronatracker.models.data.IndiaTotalStats

@Database(entities = [IndiaTotalStats::class],version = 1,exportSchema = false)
abstract class IndiaStatsDatabase:RoomDatabase() {

    abstract val indiaStatsDatabaseDao:IndiaStatsDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE:IndiaStatsDatabase? = null

        fun getInstance(context: Context):IndiaStatsDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        IndiaStatsDatabase::class.java,
                        "india_stats_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}
package com.bhavishay.coronatracker.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bhavishay.coronatracker.models.data.WorldTotalStats

@Database(entities = [WorldTotalStats::class], version = 1,exportSchema = false)
abstract class WorldStatsDatabase:RoomDatabase() {

    abstract val worldStatsDatabaseDao:WorldStatsDatabaseDao

    companion object{

        @Volatile
        private var INSTANCE:WorldStatsDatabase? = null

        fun getInstance(context: Context):WorldStatsDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WorldStatsDatabase::class.java,
                        "world_stats_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
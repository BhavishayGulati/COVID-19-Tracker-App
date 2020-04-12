package com.bhavishay.coronatracker.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bhavishay.coronatracker.models.data.State

@Database(entities = [State::class], version = 1,exportSchema = false)
abstract class StateStatsDatabase:RoomDatabase() {

    abstract val stateStatsDatabaseDao:StateStatsDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE:StateStatsDatabase? = null

        fun getInstance(context: Context):StateStatsDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StateStatsDatabase::class.java,
                        "state_stats_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
package com.bhavishay.coronatracker.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bhavishay.coronatracker.models.data.Country

@Database(entities = [Country::class],version = 1,exportSchema = false)
abstract class CountryStatsDatabase : RoomDatabase(){

    abstract val countryStatsDatabaseDao:CountryStatsDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE:CountryStatsDatabase? = null

        fun getInstance(context: Context):CountryStatsDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CountryStatsDatabase::class.java,
                        "country_stats_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
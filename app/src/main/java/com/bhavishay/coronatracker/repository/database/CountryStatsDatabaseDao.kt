package com.bhavishay.coronatracker.repository.database

import androidx.room.*
import com.bhavishay.coronatracker.models.data.Country

@Dao
interface CountryStatsDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countryStats:Country)

    @Query("SELECT * from country_stats_table WHERE countryName = :countryName")
    suspend fun getCountryStats(countryName:String):Country?

    @Query("SELECT * from country_stats_table")
    suspend fun getAllCountries():List<Country>?
}
package com.bhavishay.coronatracker.repository.database

import androidx.room.*
import com.bhavishay.coronatracker.models.data.Country

@Dao
interface CountryStatsDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(countryStats:Country)

    @Update
    fun update(countryStats: Country)

    @Query("SELECT * from country_stats_table WHERE countryName = :countryName")
    fun getCountryStats(countryName:String):Country?

    @Query("SELECT * from country_stats_table")
    fun getAllCountries():List<Country>?
}
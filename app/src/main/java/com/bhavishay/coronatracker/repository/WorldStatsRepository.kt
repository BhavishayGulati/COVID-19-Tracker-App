package com.bhavishay.coronatracker.repository

import android.util.Log
import com.bhavishay.coronatracker.models.data.Country
import com.bhavishay.coronatracker.models.data.WorldTotalStats
import com.bhavishay.coronatracker.repository.database.CountryStatsDatabase
import com.bhavishay.coronatracker.repository.database.WorldStatsDatabase
import com.bhavishay.coronatracker.repository.network.StatsApi
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class WorldStatsRepository(
    private val worldStatsDatabase: WorldStatsDatabase,
    private val countryStatsDatabase: CountryStatsDatabase
) {

    suspend fun getWorldStats(): WorldTotalStats? {
        var worldTotalStats: WorldTotalStats? = getWorldStatsFromLocalDatabase()
        if (worldTotalStats != null) {
            if (shouldRefreshWorldStats(worldTotalStats.lastNetworkCallTime)) {
                worldTotalStats = refreshWorldStats()
            }

        } else {
            worldTotalStats = refreshWorldStats()

        }
        return worldTotalStats
    }

    fun getCountriesStatsList():List<Country>?{
        return getCountryStatsListFromLocalDatabase()

    }

    private fun shouldRefreshWorldStats(timeString: String): Boolean {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        try {
            val date = formatter.parse(timeString)
            val currentDate = Date()
            val diff = currentDate.time - date?.time!!
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60

            return hours > 1
        } catch (e: ParseException) {

        }
        return false
    }

    private fun getWorldStatsFromLocalDatabase(): WorldTotalStats? {
        Log.d("ApiResponse","checking cache")
        return worldStatsDatabase.worldStatsDatabaseDao.get()
    }

    private fun addWorldStatsToLocalDatabase(worldTotalStats: WorldTotalStats?) {
        if (worldTotalStats != null)
            worldStatsDatabase.worldStatsDatabaseDao.insert(worldTotalStats)
    }

    private fun getCountryStatsListFromLocalDatabase():List<Country>?{
        return countryStatsDatabase.countryStatsDatabaseDao.getAllCountries()
    }

    private fun addCountryStatsToLocalDatabase(countryStats: List<Country>?) {
        countryStats?.forEach { country ->
            countryStatsDatabase.countryStatsDatabaseDao.insert(country)
        }
    }

    private suspend fun refreshWorldStats(): WorldTotalStats? {
        Log.d("ApiResponse","refreshing from api")
        try {

            val worldStatsResponse = StatsApi.retrofitService.getWorldStats()
            return if (worldStatsResponse.isSuccessful) {
                val responseBody = worldStatsResponse.body()

                val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
                val worldTotalStats = responseBody?.worldTotalStats
                worldTotalStats?.lastNetworkCallTime = formatter.format(Date())

                    addWorldStatsToLocalDatabase(worldTotalStats)
                addCountryStatsToLocalDatabase(responseBody?.countriesStats)


                responseBody?.worldTotalStats
            } else {
                null
            }
        } catch (e: IOException) {
            throw e
        }
    }

}
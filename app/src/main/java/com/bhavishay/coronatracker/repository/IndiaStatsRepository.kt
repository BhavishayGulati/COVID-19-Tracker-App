package com.bhavishay.coronatracker.repository


import android.util.Log

import com.bhavishay.coronatracker.models.data.IndiaTotalStats
import com.bhavishay.coronatracker.models.data.State

import com.bhavishay.coronatracker.repository.database.IndiaStatsDatabase
import com.bhavishay.coronatracker.repository.database.StateStatsDatabase
import com.bhavishay.coronatracker.repository.network.StatsApi
import kotlinx.coroutines.delay
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class IndiaStatsRepository(
private val indiaStatsDatabase: IndiaStatsDatabase,
private val stateStatsDatabase: StateStatsDatabase
) {

    suspend fun getIndiaStats(): IndiaTotalStats? {
        var indiaTotalStats: IndiaTotalStats? = getIndiaStatsFromLocalDatabase()
        if (indiaTotalStats != null) {
            if (shouldRefreshStats(indiaTotalStats.lastNetworkCallTime)) {
                indiaTotalStats = refreshStateStats()
            }

        } else {
            indiaTotalStats = refreshStateStats()

        }
        return indiaTotalStats
    }

    suspend fun getStatesStatsList():List<State>?{
        return getStateStatsListFromLocalDatabase()

    }

    suspend fun getStateStats(stateName:String):State?{
        return stateStatsDatabase.stateStatsDatabaseDao.getStateStats(stateName)
    }

    private fun shouldRefreshStats(timeString: String): Boolean {
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

    private suspend fun getIndiaStatsFromLocalDatabase(): IndiaTotalStats? {
        return indiaStatsDatabase.indiaStatsDatabaseDao.get()
    }

    private suspend fun addIndiaStatsToLocalDatabase(indiaTotalStats: IndiaTotalStats?) {
        if (indiaTotalStats != null)
            indiaStatsDatabase.indiaStatsDatabaseDao.insert(indiaTotalStats)
    }

    private suspend fun getStateStatsListFromLocalDatabase():List<State>?{
        return stateStatsDatabase.stateStatsDatabaseDao.getAllStates()
    }

    private suspend fun addStateStatsToLocalDatabase(stateStats: List<State>?) {
        stateStats?.forEach { state ->
            stateStatsDatabase.stateStatsDatabaseDao.insert(state)
        }
    }

    private suspend fun refreshStateStats():IndiaTotalStats?{

        try {
            delay(2000)
            Log.d("ApiResponse","refreshing india stats from api")
            val indiaStatsResponse = StatsApi.retrofitService.getIndiaStats()
            return if(indiaStatsResponse.isSuccessful){
               val responseBody = indiaStatsResponse.body()
                val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
                val indiaTotalStats = responseBody?.indiaTotalStat
                indiaTotalStats?.lastNetworkCallTime = formatter.format(Date())

                addIndiaStatsToLocalDatabase(indiaTotalStats)

                val statesList = ArrayList<State>()
                responseBody?.states?.values?.forEach { state ->
                    statesList.add(state)
                }
                addStateStatsToLocalDatabase(statesList)

                responseBody?.indiaTotalStat
            }else{
                null
            }
        }catch (e:IOException){
            throw e
        }
    }
}
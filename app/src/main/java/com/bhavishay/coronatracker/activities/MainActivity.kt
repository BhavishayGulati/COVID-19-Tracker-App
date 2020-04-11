package com.bhavishay.coronatracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.network.StatsApi
import kotlinx.coroutines.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testApi()
    }

    private fun testApi(){
        CoroutineScope(Dispatchers.IO).launch {

            try {
              val worldStatsResponse  = StatsApi.retrofitService.getWorldStats()
                if(worldStatsResponse.isSuccessful)
                Log.d("statsApiResponse","no of countries ${worldStatsResponse.body()?.countriesStats?.size} Total World Cases ${worldStatsResponse.body()?.worldTotalStats?.totalCases}")
                else Log.e("statsApiResponse",worldStatsResponse.errorBody()!!.string())
                delay(1000)
                val indiaStatsResponse = StatsApi.retrofitService.getIndiaStats()
                if(indiaStatsResponse.isSuccessful)
                    Log.d("statsApiResponse","no of states ${indiaStatsResponse.body()?.states?.size} Total India Cases ${indiaStatsResponse.body()?.indiaTotalStat?.activeCases}")
                else Log.e("statsApiResponse",indiaStatsResponse.errorBody()!!.string())
            }catch (e:Exception){
                Log.e("statsApiResponse",e.toString())
            }
        }
    }
}

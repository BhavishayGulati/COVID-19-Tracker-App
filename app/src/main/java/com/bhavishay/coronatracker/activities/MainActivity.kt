package com.bhavishay.coronatracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.network.NewsApi
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
                Log.d("ApiResponse","no of countries ${worldStatsResponse.body()?.countriesStats?.size} Total World Cases ${worldStatsResponse.body()?.worldTotalStats?.totalCases}")
                else Log.e("ApiResponse",worldStatsResponse.errorBody()!!.string())
                delay(1000)
                val indiaStatsResponse = StatsApi.retrofitService.getIndiaStats()
                if(indiaStatsResponse.isSuccessful)
                    Log.d("ApiResponse","no of states ${indiaStatsResponse.body()?.states?.size} Total India Cases ${indiaStatsResponse.body()?.indiaTotalStat?.activeCases}")
                else Log.e("ApiResponse",indiaStatsResponse.errorBody()!!.string())

                val newsApiResponse = NewsApi.retrofitService.getNews("covid India","2020-04-11",pageNo=1)
                if(newsApiResponse.isSuccessful)
                    Log.d("ApiResponse","status - ${newsApiResponse.body()?.status} totalNews - ${newsApiResponse.body()?.totalResults} news1 - ${newsApiResponse.body()?.articles?.get(0)?.newsTitle}")
                else Log.e("ApiResponse",newsApiResponse.errorBody()!!.string())
            }catch (e:Exception){
                Log.e("statsApiResponse",e.toString())
            }
        }
    }
}

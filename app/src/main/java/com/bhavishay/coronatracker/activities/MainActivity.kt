package com.bhavishay.coronatracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.models.data.WorldStatsResponse
import com.bhavishay.coronatracker.repository.WorldStatsRepository
import com.bhavishay.coronatracker.repository.database.CountryStatsDatabase
import com.bhavishay.coronatracker.repository.database.WorldStatsDatabase
import com.bhavishay.coronatracker.repository.network.NewsApi
import com.bhavishay.coronatracker.repository.network.StatsApi
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

               val repository = WorldStatsRepository(
                   WorldStatsDatabase.getInstance(this@MainActivity),
               CountryStatsDatabase.getInstance(this@MainActivity)
               )
                //for total world statistics
                val worldStats = repository.getWorldStats()

                //for country wise stats
                val countriesList = repository.getCountriesStatsList()


                Log.d("ApiResponse","total cases ${worldStats?.totalCases}")

//                delay(1000)
//                val indiaStatsResponse = StatsApi.retrofitService.getIndiaStats()
//                if(indiaStatsResponse.isSuccessful)
//                    Log.d("ApiResponse","no of states ${indiaStatsResponse.body()?.states?.size} Total India Cases ${indiaStatsResponse.body()?.indiaTotalStat?.activeCases}")
//                else Log.e("ApiResponse",indiaStatsResponse.errorBody()!!.string())
//
                val newsApiResponse = NewsApi.retrofitService.getNews("covid india","2020-04-11",pageNo=1)
                if(newsApiResponse.isSuccessful)
                        Log.d("ApiResponse","status - ${newsApiResponse.body()?.status} totalNews - ${newsApiResponse.body()?.totalResults} news1 - ${newsApiResponse.body()?.articles?.get(0)?.newsTitle}")
                else Log.e("ApiResponse",newsApiResponse.errorBody()!!.string())
            }catch (e:Exception){
                Log.e("statsApiResponse",e.toString())
            }
        }
    }
}

package com.bhavishay.coronatracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.models.data.State
import com.bhavishay.coronatracker.models.data.WorldStatsResponse
import com.bhavishay.coronatracker.repository.IndiaStatsRepository
import com.bhavishay.coronatracker.repository.WorldStatsRepository
import com.bhavishay.coronatracker.repository.database.CountryStatsDatabase
import com.bhavishay.coronatracker.repository.database.IndiaStatsDatabase
import com.bhavishay.coronatracker.repository.database.StateStatsDatabase
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
                val worldStats = repository.getWorldStats()
                val countriesList = repository.getCountriesStatsList()
                Log.d("ApiResponse","total cases ${worldStats?.totalCases}")

                delay(1000)

                val indiaStatsRepository = IndiaStatsRepository(
                    IndiaStatsDatabase.getInstance(this@MainActivity),
                    StateStatsDatabase.getInstance(this@MainActivity)
                )
                val indiaStats = indiaStatsRepository.getIndiaStats()
                val statesList = indiaStatsRepository.getStatesStatsList()
                Log.d("ApiResponse","total cases in india ${indiaStats?.confirmedCases}")

//

            }catch (e:Exception){
                Log.e("ApiResponse",e.toString())
            }
        }

        //both requests run together
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newsApiResponse =
                    NewsApi.retrofitService.getNews("covid India", "2020-04-11", pageNo = 1)
                if (newsApiResponse.isSuccessful)
                    Log.d(
                        "ApiResponse",
                        "status - ${newsApiResponse.body()?.status} totalNews - ${newsApiResponse.body()?.totalResults} news1 - ${newsApiResponse.body()?.articles?.get(
                            0
                        )?.newsTitle}"
                    )
                else Log.e("ApiResponse", newsApiResponse.errorBody()!!.string())
            }catch (e:Exception){
                Log.e("ApiResponse",e.toString())
            }
        }
    }
}

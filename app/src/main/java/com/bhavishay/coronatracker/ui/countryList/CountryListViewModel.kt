package com.bhavishay.coronatracker.ui.countryList

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishay.coronatracker.models.data.Country
import com.bhavishay.coronatracker.models.data.WorldTotalStats
import com.bhavishay.coronatracker.repository.WorldStatsRepository
import com.bhavishay.coronatracker.repository.database.CountryStatsDatabase
import com.bhavishay.coronatracker.repository.database.WorldStatsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryListViewModel : ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    lateinit var worldStatsRepository: WorldStatsRepository
    var countriesList = MutableLiveData<List<Country>>()



    fun getCountriesList(context: Context){
        isLoading.value = true
        worldStatsRepository = WorldStatsRepository(
            WorldStatsDatabase.getInstance(context),
            CountryStatsDatabase.getInstance(context)
        )

        viewModelScope.launch(Dispatchers.IO) {
            val response = worldStatsRepository.getCountriesStatsList()
            if (response!=null){
                Log.d("countriesList","countries size ${response.size}")
                withContext(Dispatchers.Main){
                    countriesList.value = response
                    isLoading.value=false
                }
            }
        }
    }
}

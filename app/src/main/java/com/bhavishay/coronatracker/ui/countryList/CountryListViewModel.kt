package com.bhavishay.coronatracker.ui.countryList

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishay.coronatracker.models.data.WorldTotalStats
import com.bhavishay.coronatracker.repository.WorldStatsRepository
import com.bhavishay.coronatracker.repository.database.CountryStatsDatabase
import com.bhavishay.coronatracker.repository.database.WorldStatsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryListViewModel : ViewModel() {
//    lateinit var isLoading : MutableLiveData<Boolean>
//    lateinit var worldStatsRepository: WorldStatsRepository
//    lateinit var worldTotalStats: WorldTotalStats
//
//
//
//
//    fun getCountriesList(context: Context){
//        isLoading.value = true
//        worldStatsRepository = WorldStatsRepository(
//            WorldStatsDatabase.getInstance(context),
//            CountryStatsDatabase.getInstance(context)
//        )
//
//        viewModelScope.launch(Dispatchers.IO) {
//            var response = worldStatsRepository.getWorldStats()
//            if (response!=null){
//                withContext(Dispatchers.Main){
//                    worldTotalStats = response
//                    isLoading.value=false
//                }
//            }
//        }
//    }
}

package com.bhavishay.coronatracker.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.helpers.TimeHelper
import com.bhavishay.coronatracker.models.data.Country
import com.bhavishay.coronatracker.models.data.WorldTotalStats
import com.bhavishay.coronatracker.repository.WorldStatsRepository
import com.bhavishay.coronatracker.ui.info.precautions.PrecautionsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


class HomeViewModel : ViewModel() {
    val totalCases = MutableLiveData<String>()
    val totalDeaths = MutableLiveData<String>()
    val totalRecovered = MutableLiveData<String>()
    val lastUpdatedTime = MutableLiveData<String>()
    val hasError = MutableLiveData<Boolean>()
    var countriesList = MutableLiveData<List<Country>>()

    var errorMessage = ""
    init { }
    fun getWorldStats(worldStatsRepository: WorldStatsRepository) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val worldTotalStats = worldStatsRepository.getWorldStats()
                if (worldTotalStats != null) {
                    val countriesListResponse = worldStatsRepository.getCountriesStatsList() ?: listOf<Country>()
                    withContext(Dispatchers.Main) {
                        handleRequestData(worldTotalStats,countriesListResponse)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        handleRequestError()
                    }
                }
            }
        } catch (e: IOException) {
            handleRequestError(e.localizedMessage!!)
        }
    }
    private fun handleRequestData(worldTotalStats: WorldTotalStats, countriesList:List<Country>) {
        totalCases.value = worldTotalStats.totalCases
        totalDeaths.value = worldTotalStats.totalDeaths
        totalRecovered.value = worldTotalStats.totalRecovered
        lastUpdatedTime.value = worldTotalStats.statsTakenAt
        this.countriesList.value = countriesList
    }

    private fun handleRequestError(errorMessage: String = "Some Error Occurred") {
        hasError.value = true
        this.errorMessage = errorMessage
    }


}

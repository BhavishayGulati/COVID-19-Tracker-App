package com.bhavishay.coronatracker.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.helpers.TimeHelper
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
    var errorMessage = ""


    init {

    }

    fun getWorldStats(worldStatsRepository: WorldStatsRepository) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val worldTotalStats = worldStatsRepository.getWorldStats()
                if (worldTotalStats != null) {
                    withContext(Dispatchers.Main) {
                        handleRequestData(worldTotalStats)
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

    private fun handleRequestData(worldTotalStats: WorldTotalStats) {
        totalCases.value = worldTotalStats.totalCases
        totalDeaths.value = worldTotalStats.totalDeaths
        totalRecovered.value = worldTotalStats.totalRecovered
        lastUpdatedTime.value = worldTotalStats.statsTakenAt
    }

    private fun handleRequestError(errorMessage: String = "Some Error Occurred") {
        hasError.value = true
        this.errorMessage = errorMessage
    }


}

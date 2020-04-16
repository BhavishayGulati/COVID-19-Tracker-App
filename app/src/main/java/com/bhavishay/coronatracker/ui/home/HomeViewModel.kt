package com.bhavishay.coronatracker.ui.home

import android.util.Log
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


class HomeViewModel : ViewModel() {
    val worldTotalStats = MutableLiveData<WorldTotalStats>()
    val hasError = MutableLiveData<Boolean>()
    var countriesList = ArrayList<Country>()
    var isLoading = MutableLiveData<Boolean>()

    var errorMessage = ""

    init {
    }


    fun getWorldStats(worldStatsRepository: WorldStatsRepository) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val worldTotalStats = worldStatsRepository.getWorldStats()
                if (worldTotalStats != null) {
                    val countriesListResponse =
                        worldStatsRepository.getCountriesStatsList() ?: listOf<Country>()
                    withContext(Dispatchers.Main) {
                        handleRequestData(worldTotalStats, countriesListResponse)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        handleRequestError()
                    }
                }

            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    handleRequestError(e.localizedMessage!!)
                }
            } finally {
                withContext(Dispatchers.Main) {
                    isLoading.value = false
                }
            }
        }
    }

    private fun handleRequestData(worldTotalStats: WorldTotalStats, countriesList: List<Country>) {
        this.countriesList = ArrayList(countriesList)
        this.worldTotalStats.value = worldTotalStats

    }

    private fun handleRequestError(errorMessage: String = "Some Error Occurred") {
        hasError.value = true
        this.errorMessage = errorMessage
    }


}

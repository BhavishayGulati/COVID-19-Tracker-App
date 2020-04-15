package com.bhavishay.coronatracker.ui.states

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishay.coronatracker.models.data.IndiaTotalStats
import com.bhavishay.coronatracker.repository.IndiaStatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class StatesPageViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val totalCases = MutableLiveData<String>()
    val totalDeaths = MutableLiveData<String>()
    val totalRecovered = MutableLiveData<String>()
    val lastUpdatedTime = MutableLiveData<String>()
    val hasError = MutableLiveData<Boolean>()
    var errorMessage = ""
    fun getStatesStat(indiaStatsRepository: IndiaStatsRepository)
    {
        try {
            viewModelScope.launch(Dispatchers.IO)
            {
                val indiaStats = indiaStatsRepository.getIndiaStats()
                if (indiaStats !=null)
                {
                    withContext(Dispatchers.Main)
                    {
                        handleRequestData(indiaStats)
                    }
                }
                else
                {
                    withContext(Dispatchers.Main)
                    {
                        handleRequestError()
                    }
                }
            }
        }
        catch (e:IOException)
        {
            e.printStackTrace()

        }
    }

    private fun handleRequestError()
    {
        hasError.value = true
        this.errorMessage = errorMessage

    }

    private fun handleRequestData(indiaStats: IndiaTotalStats)
    {
        totalCases.value = indiaStats.activeCases
        totalDeaths.value = indiaStats.deaths
        totalRecovered.value = indiaStats.recoveredCases
        lastUpdatedTime.value = indiaStats.lastUpdatedTime
    }

}

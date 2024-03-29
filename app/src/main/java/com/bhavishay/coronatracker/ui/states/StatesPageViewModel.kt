package com.bhavishay.coronatracker.ui.states

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishay.coronatracker.models.data.IndiaTotalStats
import com.bhavishay.coronatracker.models.data.State
import com.bhavishay.coronatracker.repository.IndiaStatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class StatesPageViewModel : ViewModel() {
    val indiaTotalStats = MutableLiveData<IndiaTotalStats>()
    val hasError = MutableLiveData<Boolean>()
    var statesList = ArrayList<State>()
    var isLoading = MutableLiveData<Boolean>()
    var errorMessage = ""


    fun getStatesStat(indiaStatsRepository: IndiaStatsRepository)
    {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO)
        {
        try {

                val indiaStats = indiaStatsRepository.getIndiaStats()
                if (indiaStats !=null)
                {
                    val statesListResponse = indiaStatsRepository.getStatesStatsList() ?: listOf<State>()
                    withContext(Dispatchers.Main)
                    {
                        handleRequestData(indiaStats,statesListResponse)
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
        catch (e:IOException)
        {
            Log.e("Tag", e.printStackTrace().toString())

        }finally {
            withContext(Dispatchers.Main){
                isLoading.value = false
            }
        }
        }
    }

    private fun handleRequestError()
    {
        hasError.value = true
        this.errorMessage = errorMessage

    }

    private fun handleRequestData(indiaStats: IndiaTotalStats, statesList : List<State>)
    {
        this.statesList = ArrayList(statesList)
        indiaTotalStats.value = indiaStats
    }

}

package com.bhavishay.coronatracker.ui.states


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishay.coronatracker.models.data.State
import com.bhavishay.coronatracker.repository.IndiaStatsRepository
import com.bhavishay.coronatracker.repository.WorldStatsRepository
import com.bhavishay.coronatracker.repository.database.IndiaStatsDatabase
import com.bhavishay.coronatracker.repository.database.StateStatsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StateListViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    lateinit var indiaStatsRepository: IndiaStatsRepository
    var statesList = MutableLiveData<List<State>>()

    fun getStatesList(context: Context){
        isLoading.value = true
        indiaStatsRepository = IndiaStatsRepository(
            IndiaStatsDatabase.getInstance(context),
            StateStatsDatabase.getInstance(context)
        )

        viewModelScope.launch(Dispatchers.IO) {
            val response = indiaStatsRepository.getStatesStatsList()
            if (response!=null){
                withContext(Dispatchers.Main){
                    statesList.value = response
                    isLoading.value = false
                }
            }
        }
    }
}

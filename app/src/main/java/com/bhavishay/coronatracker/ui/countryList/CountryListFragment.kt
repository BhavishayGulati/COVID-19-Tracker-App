package com.bhavishay.coronatracker.ui.countryList


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.bhavishay.coronatracker.R

class CountryListFragment : Fragment() {

    private lateinit var viewModel: CountryListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.country_list_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {isLoading ->
            if (!isLoading){
                viewModel.getCountriesList(context!!)
               var totalCases= viewModel.worldTotalStats.totalCases
                Log.e("Api response",totalCases)
            }else{
                Log.e("Api respone","No result")
            }
        })
    }
}

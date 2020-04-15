package com.bhavishay.coronatracker.ui.states

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.repository.IndiaStatsRepository
import com.bhavishay.coronatracker.repository.database.IndiaStatsDatabase
import com.bhavishay.coronatracker.repository.database.StateStatsDatabase
import kotlinx.android.synthetic.main.states_page_fragment.*

class StatesPage : Fragment() {

    companion object {
        fun newInstance() = StatesPage()
    }

    private lateinit var viewModel: StatesPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.states_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StatesPageViewModel::class.java)
        // TODO: Use the ViewModel

        viewModel.totalCases.observe(viewLifecycleOwner, Observer { totalCases ->
            txt_cases.text = totalCases
        })

        viewModel.totalDeaths.observe(viewLifecycleOwner, Observer { totalDeaths ->
            txt_deaths.text = totalDeaths
        })

        viewModel.totalRecovered.observe(viewLifecycleOwner, Observer { totalRecovered ->
            txt_recovered.text = totalRecovered
        })


        viewModel.getStatesStat(IndiaStatsRepository(IndiaStatsDatabase.getInstance(context!!),
            StateStatsDatabase.getInstance(context!!)))
    }

}

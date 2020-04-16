package com.bhavishay.coronatracker.ui.states

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.repository.IndiaStatsRepository
import com.bhavishay.coronatracker.repository.database.IndiaStatsDatabase
import com.bhavishay.coronatracker.repository.database.StateStatsDatabase

import kotlinx.android.synthetic.main.states_page_fragment.*

class StatesPage : Fragment() {


    private lateinit var viewModel: StatesPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(StatesPageViewModel::class.java)
        return inflater.inflate(R.layout.states_page_fragment, container, false)

    }

    @SuppressLint("ResourceType")
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val fragment = StatesPage()
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.state_list, fragment)
            ?.commit()
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.totalCases.observe(viewLifecycleOwner, Observer { totalCases ->
            txt_cases.text = totalCases
        })

        viewModel.totalDeaths.observe(viewLifecycleOwner, Observer {deaths ->
            txt_deaths.text = deaths
        })

        viewModel.totalRecovered.observe(viewLifecycleOwner, Observer {cured->
            txt_recovered.text = cured
        })
        statesRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.statesList.observe(viewLifecycleOwner, Observer { list->
            statesRecyclerView.adapter = StageListAdapter(list)
        })

    }

    override fun onResume() {
        viewModel.getStatesStat(IndiaStatsRepository(
            IndiaStatsDatabase.getInstance(context!!),
            StateStatsDatabase.getInstance(context!!)
        ))
        super.onResume()
    }
}
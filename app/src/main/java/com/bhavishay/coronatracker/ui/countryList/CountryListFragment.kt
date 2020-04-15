package com.bhavishay.coronatracker.ui.countryList


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.bhavishay.coronatracker.R
import kotlinx.android.synthetic.main.country_list_fragment.*

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
        viewModel = ViewModelProvider(this).get(CountryListViewModel::class.java)

        itemsswipetorefresh.setOnRefreshListener {
            viewModel.getCountriesList(context!!)
        }

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            itemsswipetorefresh.isRefreshing = it
        })

        countryRecyclerView.layoutManager = LinearLayoutManager(context)


        viewModel.countriesList.observe(viewLifecycleOwner, Observer { list ->

            countryRecyclerView.adapter = CountryListAdapter(list)
        })


    }

    override fun onResume() {
        viewModel.getCountriesList(context!!)
        super.onResume()
    }
}

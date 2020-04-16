package com.bhavishay.coronatracker.ui.states

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders.of
import androidx.lifecycle.ViewModelStores.of
import androidx.recyclerview.widget.LinearLayoutManager

import com.bhavishay.coronatracker.R
import kotlinx.android.synthetic.main.states_list_fragment.*
import java.time.LocalTime.of
import java.util.EnumSet.of

class StateListFragment : Fragment() {

    private lateinit var viewModel: StateListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.states_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(StateListViewModel::class.java)

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            itemsswipetorefresh.isRefreshing = it
        })

        statesRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.statesList.observe(viewLifecycleOwner, Observer {list ->
            statesRecyclerView.adapter = StageListAdapter(list)
        })

        viewModel.getStatesList(context!!)

    }
}
package com.bhavishay.coronatracker.ui.states

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bhavishay.coronatracker.R

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
    }

}

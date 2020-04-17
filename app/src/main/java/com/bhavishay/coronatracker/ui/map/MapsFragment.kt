package com.bhavishay.coronatracker.ui.map

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bhavishay.coronatracker.R

class MapsFragment : Fragment() {

    companion object {
        fun newInstance() = MapsFragment()
    }

    private lateinit var viewModel: MapsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.maps_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MapsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

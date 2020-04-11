package com.bhavishay.coronatracker.ui.info.rumors

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bhavishay.coronatracker.R

class RumorsFragment : Fragment() {

    companion object {
        fun newInstance() = RumorsFragment()
    }

    private lateinit var viewModel: RumorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rumors_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RumorsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

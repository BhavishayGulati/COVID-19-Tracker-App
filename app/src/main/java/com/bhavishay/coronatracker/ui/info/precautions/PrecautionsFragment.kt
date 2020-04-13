package com.bhavishay.coronatracker.ui.info.precautions

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bhavishay.coronatracker.R

class PrecautionsFragment : Fragment() {



 //   private lateinit var viewModel: PrecautionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("this","precautions")
        return inflater.inflate(R.layout.precautions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
     //   viewModel = ViewModelProviders.of(this).get(PrecautionsViewModel::class.java)
        // TODO: Use the ViewModel
    }



}

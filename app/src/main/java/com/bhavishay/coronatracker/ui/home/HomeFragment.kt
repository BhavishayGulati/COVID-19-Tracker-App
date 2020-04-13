package com.bhavishay.coronatracker.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.ui.info.precautions.PrecautionsFragment
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.home_fragment, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nav_precautions.setOnClickListener{

        }
    }



}

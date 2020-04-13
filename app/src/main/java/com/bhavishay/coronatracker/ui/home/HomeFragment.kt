package com.bhavishay.coronatracker.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction

import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.ui.info.precautions.PrecautionsFragment
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view= inflater.inflate(R.layout.home_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nav_precautions.setOnClickListener {

            nav_precautions.setOnClickListener{
                var fragmentTransaction:FragmentTransaction = parentFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container,PrecautionsFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }

        }
    }

}

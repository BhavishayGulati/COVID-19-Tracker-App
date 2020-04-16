package com.bhavishay.coronatracker.ui.states

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.ContextMenu
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

    @SuppressLint("ResourceType")
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val fragment = StatesPage()
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.state_list,fragment)?.commit()
        super.onCreateContextMenu(menu, v, menuInfo)
    }
}

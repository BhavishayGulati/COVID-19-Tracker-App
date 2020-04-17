package com.bhavishay.coronatracker.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.activities.SearchActivity
import com.bhavishay.coronatracker.helpers.TimeHelper
import com.bhavishay.coronatracker.repository.WorldStatsRepository
import com.bhavishay.coronatracker.repository.database.CountryStatsDatabase
import com.bhavishay.coronatracker.repository.database.WorldStatsDatabase
import com.bhavishay.coronatracker.ui.countryList.CountryListAdapter
import com.bhavishay.coronatracker.ui.info.help.HelpFragment
import com.bhavishay.coronatracker.ui.info.precautions.PrecautionsFragment
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var countryListAdapter: CountryListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

     override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
                R.id.action_search_app ->{
                    startActivity(Intent(context,SearchActivity::class.java))
                }
                 R.id.action_share_app ->{
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.setType("text/plain")
                    .putExtra(Intent.EXTRA_TEXT,"This is the App link")
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            nav_precautions.setOnClickListener{
                val fragmentTransaction:FragmentTransaction = parentFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container,PrecautionsFragment())
                 .addToBackStack(null)
                 .commit()
            }

            nav_help.setOnClickListener{
                val fragmentTransaction = parentFragmentManager.beginTransaction()
                fragmentTransaction.replace((R.id.container),HelpFragment())
                    .addToBackStack(null)
                    .commit()
            }



        //setting liveData observers
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.worldTotalStats.observe(viewLifecycleOwner, Observer { stats ->
            countryListAdapter = CountryListAdapter(viewModel.countriesList,stats)

            recyclerView.adapter = countryListAdapter
        })


        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            swipeToRefresh.isRefreshing = isLoading
        })

        swipeToRefresh.setOnRefreshListener {
            viewModel.getWorldStats(WorldStatsRepository(
                WorldStatsDatabase.getInstance(context!!),
                CountryStatsDatabase.getInstance(context!!)
            ))
        }






        }

    override fun onResume() {
        viewModel.getWorldStats(WorldStatsRepository(
            WorldStatsDatabase.getInstance(context!!),
            CountryStatsDatabase.getInstance(context!!)
        ))
        super.onResume()
    }
    }



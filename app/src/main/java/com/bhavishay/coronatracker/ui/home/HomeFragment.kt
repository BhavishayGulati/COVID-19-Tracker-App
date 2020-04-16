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
import com.bhavishay.coronatracker.helpers.TimeHelper
import com.bhavishay.coronatracker.repository.WorldStatsRepository
import com.bhavishay.coronatracker.repository.database.CountryStatsDatabase
import com.bhavishay.coronatracker.repository.database.WorldStatsDatabase
import com.bhavishay.coronatracker.ui.countryList.CountryListAdapter
import com.bhavishay.coronatracker.ui.info.help.HelpFragment
import com.bhavishay.coronatracker.ui.info.precautions.PrecautionsFragment

import kotlinx.android.synthetic.main.home_fragment.*

import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel


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
        viewModel.totalCases.observe(viewLifecycleOwner, Observer {totalCases ->
            text_confirmed_cases.text = totalCases
        })

        viewModel.totalDeaths.observe(viewLifecycleOwner, Observer { totalDeaths ->
            text_deceased_cases.text = totalDeaths
        })

        viewModel.totalRecovered.observe(viewLifecycleOwner, Observer { totalRecovered ->
            text_recovered_cases.text = totalRecovered
        })

        viewModel.lastUpdatedTime.observe(viewLifecycleOwner, Observer { timeString ->
            //time is in format - yyyy-MM-dd HH:mm:ss
            //need to convert to date time object to use time helper class
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
            val date = formatter.parse(timeString)
            tv_update_date.text =  "Updated ${TimeHelper.getTimeAgo(date.time)}"
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {isLoading ->
            if (!isLoading)
                progress_bar.visibility = View.VISIBLE
            else
                progress_bar.visibility = View.GONE
        })

//        viewModel.mortalityRate.observe(viewLifecycleOwner, Observer {
//            tv_mortality_rate_value.text = ((viewModel.totalDeaths.toString().toDouble()/viewModel.totalCases.toString().toDouble()) * 100).toString()
//        })


        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = true

        viewModel.countriesList.observe(viewLifecycleOwner, Observer { list ->

            recyclerView.adapter = CountryListAdapter(list)
        })
        //requesting world stats data
        }

    override fun onResume() {
        viewModel.getWorldStats(WorldStatsRepository(
            WorldStatsDatabase.getInstance(context!!),
            CountryStatsDatabase.getInstance(context!!)
        ))
        super.onResume()
    }

    }



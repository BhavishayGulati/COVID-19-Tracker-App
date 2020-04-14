package com.bhavishay.coronatracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.models.data.WorldStatsResponse
import com.bhavishay.coronatracker.repository.WorldStatsRepository
import com.bhavishay.coronatracker.repository.database.CountryStatsDatabase
import com.bhavishay.coronatracker.repository.database.WorldStatsDatabase
import com.bhavishay.coronatracker.repository.network.NewsApi
import com.bhavishay.coronatracker.repository.network.StatsApi
import com.bhavishay.coronatracker.ui.countryList.CountryListFragment
import com.bhavishay.coronatracker.ui.home.HomeFragment
import com.bhavishay.coronatracker.ui.info.help.HelpFragment
import com.bhavishay.coronatracker.ui.info.precautions.PrecautionsFragment
import com.bhavishay.coronatracker.ui.news.NewsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.nav_home ->{
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container,fragment)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_stats ->{
                    val fragment = CountryListFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container,fragment)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_news ->{
                    val fragment = NewsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container,fragment)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

        if (savedInstanceState == null) {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                .commit()
        }
        nav_view.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)

//        nav_precautions.setOnClickListener{
//            callPrecautionFragment()
//        }

//        nav_help.setOnClickListener{
//            callHelpFragment()
//        }

    }

//    fun callPrecautionFragment(){
//        val precautionsFragment = PrecautionsFragment()
//        val fT = supportFragmentManager.beginTransaction()
//        fT.add(R.id.container,precautionsFragment,"pre")
//        fT.commit()
//    }
//
//    fun callHelpFragment(){
//        val fragment = HelpFragment()
//        supportFragmentManager.beginTransaction().replace(R.id.container,fragment)
//            .commit()
//    }


}

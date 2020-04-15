package com.bhavishay.coronatracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.ui.countryList.CountryListViewModel
import com.bhavishay.coronatracker.ui.news.RumoursFragment
import com.bhavishay.coronatracker.ui.news.ViewPagerAdapter
import com.google.android.gms.common.api.internal.ActivityLifecycleObserver.of
import com.google.firebase.components.Component.of
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.precautions_fragment.*
import java.time.DayOfWeek.of
import java.util.EnumSet.of
import java.util.Optional.of

class NewsActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        viewModel =  ViewModelProviders.of(this).get(CountryListViewModel::class.java)

        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpager_id.adapter = fragmentAdapter

        tablayout_id.setupWithViewPager(viewpager_id)


    }
}


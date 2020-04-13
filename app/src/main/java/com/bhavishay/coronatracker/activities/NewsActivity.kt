package com.bhavishay.coronatracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.ui.news.RumoursFragment
import com.bhavishay.coronatracker.ui.news.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpager_id.adapter = fragmentAdapter

        tablayout_id.setupWithViewPager(viewpager_id)
    }
}

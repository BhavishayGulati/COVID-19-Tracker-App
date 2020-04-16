package com.bhavishay.coronatracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.ui.home.HomeFragment
import com.bhavishay.coronatracker.ui.news.NewsFragment
import com.bhavishay.coronatracker.ui.states.StatesPage
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_home -> {
                        val fragment = HomeFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                            .commit()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.nav_stats -> {
                        val fragment = StatesPage()
                        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                            .commit()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.nav_news -> {
                        val fragment = NewsFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
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
        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
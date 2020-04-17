package com.bhavishay.coronatracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.ui.home.HomeFragment
import com.bhavishay.coronatracker.ui.news.NewsFragment
import com.bhavishay.coronatracker.ui.news.ViewPagerAdapter
import com.bhavishay.coronatracker.ui.states.StatesPage
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var mViewPagerAdapter: ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = mViewPagerAdapter
        viewPager.offscreenPageLimit = 3
        title = "Corona Virus Tracker"

       viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
           override fun onPageScrollStateChanged(state: Int) {

           }

           override fun onPageScrolled(
               position: Int,
               positionOffset: Float,
               positionOffsetPixels: Int
           ) {

           }

           override fun onPageSelected(position: Int) {
             nav_view.selectedItemId =  when(position){
                   0 -> R.id.nav_home
                     1 -> R.id.nav_stats
                 2 -> R.id.nav_news
                 3->R.id.nav_rumours
                 else -> R.id.nav_home
             }
           }

       })
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        nav_view.setOnNavigationItemSelectedListener(this)


    }
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_home -> viewPager.currentItem = 0
            R.id.nav_stats-> viewPager.currentItem = 1
            R.id.nav_news -> viewPager.currentItem = 2
            R.id.nav_rumours -> viewPager.currentItem = 3
        }
        return true
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_search_app -> {
                startActivity(Intent(this,SearchActivity::class.java))
            }
            R.id.action_share_app -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.setType("text/plain")
                    .putExtra(Intent.EXTRA_TEXT,"This is the App link")
                startActivity(shareIntent)
            }
        }

        return true
    }
}
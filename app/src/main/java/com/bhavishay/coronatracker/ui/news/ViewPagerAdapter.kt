package com.bhavishay.coronatracker.ui.news

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bhavishay.coronatracker.ui.home.HomeFragment
import com.bhavishay.coronatracker.ui.states.StatesPage

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm)
{
    override fun getItem(position: Int): Fragment
    {
        return when(position)
        {
            0->{
                HomeFragment()
            }
            1->{
                StatesPage()
            }
            2 -> {
                NewsFragment()
            }
            else->{
                NewsFragment()
            }
        }
    }
    override fun getCount(): Int {
        return 2
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position)
        {
            0-> "Home"
            1 -> "Stats"
            2 -> "News"
            else-> "News"
        }
    }
}

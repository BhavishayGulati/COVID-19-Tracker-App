package com.bhavishay.coronatracker.ui.news

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm)
{
    override fun getItem(position: Int): Fragment
    {
        return when(position)
        {
            0->{
                NewsFragment()
            }
            else->{
                RumoursFragment()
            }
        }
    }
    override fun getCount(): Int {
        return 2
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position)
        {
            0-> "News"
            else -> "Rumours"
        }
    }
}

package com.anb.soccerschedulematch.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.anb.soccerschedulematch.Feature.Main.ListMatchFragment

class MainPagerAdapter(fm: FragmentManager, val id: String) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return ListMatchFragment.newInstance(id, position)
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Last Match"
            1 -> return "Next Match"
            2 -> return "Favorite"
            else -> return null
        }

    }

}
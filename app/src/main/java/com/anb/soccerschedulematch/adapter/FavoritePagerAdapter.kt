package com.anb.soccerschedulematch.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.anb.soccerschedulematch.feature.listmatch.ListMatchFragment

class FavoritePagerAdapter(fm: FragmentManager, val id: String): FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {
        return ListMatchFragment.newInstance(id, position)
    }

    override fun getCount(): Int {
        return 2
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Match"
            1 -> "Team"
            else -> null
        }

    }

}
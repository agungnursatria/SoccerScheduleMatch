package com.anb.soccerschedulematch.Adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import com.anb.soccerschedulematch.Feature.Main.ListMatchFragment
import com.anb.soccerschedulematch.Helper.Constant
import org.jetbrains.anko.support.v4.toast
import java.util.ArrayList

class MainPagerAdapter(fm: FragmentManager, val id: String) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return ListMatchFragment.newInstance(id, position)
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Last Match"
            1 -> return "Next Match"
            else -> return null
        }

    }
}
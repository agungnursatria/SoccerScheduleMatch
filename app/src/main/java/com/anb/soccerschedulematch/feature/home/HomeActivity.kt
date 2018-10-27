package com.anb.soccerschedulematch.feature.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.anb.soccerschedulematch.R
import com.anb.soccerschedulematch.adapter.HomePagerAdapter
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class HomeActivity : AppCompatActivity(), AHBottomNavigation.OnTabSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        initBottomNavigation()
    }

    @Suppress("DEPRECATION")
    private fun initBottomNavigation() {
        val navigationItems = ArrayList<AHBottomNavigationItem>()
        navigationItems.add(AHBottomNavigationItem("Match", R.drawable.ic_match))
        navigationItems.add(AHBottomNavigationItem("Team", R.drawable.ic_team))
        navigationItems.add(AHBottomNavigationItem("Favorite", R.drawable.ic_favorite))

        with(bot_nav) {
            addItems(navigationItems)
            defaultBackgroundColor = resources.getColor(R.color.colorPrimary)
            setUseElevation(true)
            accentColor = resources.getColor(R.color.white)
            inactiveColor = resources.getColor(R.color.whiteInactive)
            setOnTabSelectedListener(this@HomeActivity)
            titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
            setTitleTextSizeInSp(14f, 12f)
            currentItem = 0
            isBehaviorTranslationEnabled = false
        }

        val mainPagerAdapter = HomePagerAdapter(supportFragmentManager)
        vp_main.adapter = mainPagerAdapter
    }

    override fun onTabSelected(position: Int, wasSelected: Boolean): Boolean {
        vp_main.currentItem = position
        when (position) {
            0 -> {
                toolbar.title = "Football Match Schedule"
            }
            1 -> {
                toolbar.title = "Football Team List"
            }
            2 -> {
                toolbar.title = "Favorite"
            }
        }

        return true
    }

}

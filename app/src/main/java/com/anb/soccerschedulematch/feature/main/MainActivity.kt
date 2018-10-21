package com.anb.soccerschedulematch.feature.main

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.anb.soccerschedulematch.adapter.MainPagerAdapter
import com.anb.soccerschedulematch.helper.Utils
import com.anb.soccerschedulematch.model.league.LeagueResponse
import com.anb.soccerschedulematch.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var spinnerLeague : Spinner
    lateinit var tabLayout : TabLayout
    lateinit var vp : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        initView()
        initLeaguesSpinner()
    }

    private fun initView(){
        spinnerLeague = find(R.id.spinnerLeague)
        tabLayout = find(R.id.tabLayout)
        vp = find(R.id.vp)
    }

    private fun initLeaguesSpinner(){
        val leagueCall = Utils.request.getAllLeague()
        leagueCall.enqueue(object : Callback<LeagueResponse>{
            override fun onFailure(call: Call<LeagueResponse>?, t: Throwable?) {
                t?.message?.let { toast(it) }
            }

            override fun onResponse(call: Call<LeagueResponse>?, response: Response<LeagueResponse>?) {
                response?.body()?.let { setSpinner(it) }
            }
        })
    }

    private fun setSpinner(leagueResponse: LeagueResponse){
        val onlySoccerLeague = leagueResponse.leagues.filter { it.strSport.equals("Soccer") }

        val spinnerItems = ArrayList<String>()
        onlySoccerLeague.forEach {
            it.strLeague?.let { it1 -> spinnerItems.add(it1) }
        }

        val spinnerAdapter = ArrayAdapter(ctx, R.layout.spinner_item, spinnerItems)
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        spinnerLeague.adapter = spinnerAdapter

        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) { }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueResponse.leagues[position].idLeague?.let { setTabLayout(it) }
            }
        }
    }

    private fun setTabLayout(idLeague: String){
        val mSectionsPagerAdapter = MainPagerAdapter(supportFragmentManager, idLeague)
        vp.adapter = mSectionsPagerAdapter
        (vp.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.ScrollingViewBehavior()

        tabLayout.setupWithViewPager(vp)
        tabLayout.tabTextColors = ContextCompat.getColorStateList(this, android.R.color.white)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) { }
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(p0: TabLayout.Tab?) {
                p0?.position?.let { spinnerLeague.visibility = if (it == 2){ View.GONE } else { View.VISIBLE } }
            }

        })
    }

    class MainActivityUI: AnkoComponent<MainActivity>{
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui){
            coordinatorLayout {
                lparams(matchParent, matchParent)

                appBarLayout {
                    lparams(matchParent, wrapContent)

                    spinner {
                        id = R.id.spinnerLeague
                        padding = dip(8)
                    }.lparams(matchParent, wrapContent)

                    themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                        lparams(matchParent, wrapContent)
                        id = R.id.tabLayout
                        tabMode = TabLayout.MODE_FIXED
                        tabGravity = TabLayout.GRAVITY_FILL
                    }
                }

                viewPager {
                    id = R.id.vp
                }.lparams(matchParent, matchParent)
            }
        }

    }
}

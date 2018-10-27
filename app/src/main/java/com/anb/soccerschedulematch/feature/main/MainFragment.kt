package com.anb.soccerschedulematch.feature.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.anb.soccerschedulematch.R
import com.anb.soccerschedulematch.adapter.MatchPagerAdapter
import com.anb.soccerschedulematch.api.ApiRepository
import com.anb.soccerschedulematch.model.league.LeagueResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.toast

class MainFragment : Fragment(), MainView {
    companion object {
        fun newInstance() : MainFragment{
            return MainFragment()
        }
    }

    lateinit var MPresenter : MainPresenter<MainView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)

        val request = ApiRepository()
        val gson = Gson()
        MPresenter = MainPresenterImpl(request,gson)
        MPresenter.onAttach(this)
        MPresenter.initLeaguesSpinnerData()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
    }

    override fun setSpinner(leagueResponse: LeagueResponse) {
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

    override fun setTabLayout(idLeague: String) {
        val mSectionsPagerAdapter = MatchPagerAdapter(childFragmentManager, idLeague)
        vp_match.adapter = mSectionsPagerAdapter
        tl_match.setupWithViewPager(vp_match)
    }

    override fun showToast(message: String) {
        ctx.toast(message)
    }
}
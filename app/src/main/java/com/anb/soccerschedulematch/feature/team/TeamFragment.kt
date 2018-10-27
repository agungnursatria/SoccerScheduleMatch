package com.anb.soccerschedulematch.feature.team

import android.R.layout.simple_spinner_dropdown_item
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import com.anb.soccerschedulematch.R
import com.anb.soccerschedulematch.adapter.TeamAdapter
import com.anb.soccerschedulematch.api.ApiRepository
import com.anb.soccerschedulematch.feature.detailteam.DetailTeamActivity
import com.anb.soccerschedulematch.helper.Constant
import com.anb.soccerschedulematch.model.league.LeagueResponse
import com.anb.soccerschedulematch.model.team.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class TeamFragment : Fragment(), TeamView {

    companion object {
        fun newInstance() : TeamFragment {
            return TeamFragment()
        }
    }

    private lateinit var TPresenter: TeamPresenter<TeamView>
    private lateinit var rvTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    var leagueName = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return TeamFragmentUI().createView(AnkoContext.create(ctx,this))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()

        val request = ApiRepository()
        val gson = Gson()
        TPresenter = TeamPresenterImpl(request, gson)
        TPresenter.onAttach(this)
        TPresenter.initLeaguesSpinnerData()


        swipeRefresh.onRefresh {
            TPresenter.loadTeamList(leagueName)
        }
    }

    private fun initView(){
        swipeRefresh = find(R.id.srl_list_team)
        spinner = find(R.id.spinnerLeagueTeam)
        rvTeam = find(R.id.rv_team)

    }

    override fun setSpinner(leagueResponse: LeagueResponse) {
        val onlySoccerLeague = leagueResponse.leagues.filter { it.strSport.equals("Soccer") }

        val spinnerItems = ArrayList<String>()
        onlySoccerLeague.forEach {
            it.strLeague?.let { it1 -> spinnerItems.add(it1) }
        }

        val spinnerAdapter = ArrayAdapter(ctx, simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) { }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                TPresenter.loadTeamList(leagueName)
            }
        }
    }

    override fun setRecyclerViewTeamList(teamResponse: TeamResponse) {
        val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        rvTeam.layoutManager = layoutManager

        teamResponse.teams?.let {
            val teamAdapter = TeamAdapter(it){
                startActivity(intentFor<DetailTeamActivity>( Constant.TEAM to it ))
            }
            rvTeam.adapter = teamAdapter
        }

        stopRefresh()
    }

    override fun startRefresh() {
        swipeRefresh.isRefreshing = true
    }

    override fun stopRefresh() {
        swipeRefresh.isRefreshing = false
    }

    class TeamFragmentUI: AnkoComponent<TeamFragment> {
        override fun createView(ui: AnkoContext<TeamFragment>) = with(ui){
            linearLayout {
                lparams (width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                spinner {
                    id = R.id.spinnerLeagueTeam
                }
                swipeRefreshLayout {
                    id = R.id.srl_list_team
                    recyclerView {
                        id = R.id.rv_team
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
        }
    }
}
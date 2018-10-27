package com.anb.soccerschedulematch.feature.team

import com.anb.soccerschedulematch.feature.Base.MvpPresenter
import com.anb.soccerschedulematch.feature.Base.MvpView
import com.anb.soccerschedulematch.model.league.LeagueResponse
import com.anb.soccerschedulematch.model.team.TeamResponse

interface TeamView: MvpView{
    fun setSpinner(leagueResponse: LeagueResponse)
    fun startRefresh()
    fun stopRefresh()
    fun setRecyclerViewTeamList(teamResponse: TeamResponse)
}

interface TeamPresenter<V: MvpView>: MvpPresenter<V> {
    fun initLeaguesSpinnerData()
    fun loadTeamList(leagueName: String)
}
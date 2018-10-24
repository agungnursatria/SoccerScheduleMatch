package com.anb.soccerschedulematch.feature.main

import com.anb.soccerschedulematch.feature.Base.MvpPresenter
import com.anb.soccerschedulematch.feature.Base.MvpView
import com.anb.soccerschedulematch.model.league.LeagueResponse

interface MainView : MvpView{
    fun initView()
    fun setSpinner(leagueResponse: LeagueResponse)
    fun setTabLayout(idLeague: String)
    fun showToast(message: String)
}

interface MainPresenter<V: MvpView>: MvpPresenter<V>{
    fun initLeaguesSpinner()
}
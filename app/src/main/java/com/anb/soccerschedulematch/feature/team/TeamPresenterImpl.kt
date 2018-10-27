package com.anb.soccerschedulematch.feature.team

import com.anb.soccerschedulematch.api.ApiRepository
import com.anb.soccerschedulematch.api.TheSportDBApi
import com.anb.soccerschedulematch.feature.Base.BasePresenter
import com.anb.soccerschedulematch.helper.CoroutineContextProvider
import com.anb.soccerschedulematch.model.league.LeagueResponse
import com.anb.soccerschedulematch.model.team.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenterImpl<V: TeamView>(private val apiRepository: ApiRepository,
                                     private val gson: Gson,
                                     private val context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<V>(), TeamPresenter<V>{

    override fun initLeaguesSpinnerData() {
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getAllLeague()),
                        LeagueResponse::class.java
                )
            }
            getView().setSpinner(data.await())
        }
    }

    override fun loadTeamList(leagueName: String) {
        getView().startRefresh()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeams(leagueName)),
                        TeamResponse::class.java
                )
            }
            getView().setRecyclerViewTeamList(data.await())
        }
    }

}
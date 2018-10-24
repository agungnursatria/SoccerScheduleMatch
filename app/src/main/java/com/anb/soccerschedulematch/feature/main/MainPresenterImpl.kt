package com.anb.soccerschedulematch.feature.main

import com.anb.soccerschedulematch.api.ApiRepository
import com.anb.soccerschedulematch.api.TheSportDBApi
import com.anb.soccerschedulematch.feature.Base.BasePresenter
import com.anb.soccerschedulematch.helper.CoroutineContextProvider
import com.anb.soccerschedulematch.helper.Utils
import com.anb.soccerschedulematch.model.league.LeagueResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenterImpl<V: MainView>(private val apiRepository: ApiRepository,
                                     private val gson: Gson,
                                     private val context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<V>(), MainPresenter<V>{

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

}
package com.anb.soccerschedulematch.feature.main

import com.anb.soccerschedulematch.feature.Base.BasePresenter
import com.anb.soccerschedulematch.helper.Utils
import com.anb.soccerschedulematch.model.league.LeagueResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenterImpl<V: MainView> : BasePresenter<V>(), MainPresenter<V>{

    override fun initLeaguesSpinner() {
        val leagueCall = Utils.request.getAllLeague()
        leagueCall.enqueue(object : Callback<LeagueResponse> {
            override fun onFailure(call: Call<LeagueResponse>?, t: Throwable?) {
                t?.message?.let { getView().showToast(it) }
            }

            override fun onResponse(call: Call<LeagueResponse>?, response: Response<LeagueResponse>?) {
                response?.body()?.let { getView().setSpinner(it) }
            }
        })
    }

}
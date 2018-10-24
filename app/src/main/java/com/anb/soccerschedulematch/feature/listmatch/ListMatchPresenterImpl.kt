package com.anb.soccerschedulematch.feature.listmatch

import android.content.Context
import com.anb.soccerschedulematch.api.ApiRepository
import com.anb.soccerschedulematch.api.TheSportDBApi
import com.anb.soccerschedulematch.database.database
import com.anb.soccerschedulematch.feature.Base.BasePresenter
import com.anb.soccerschedulematch.helper.CoroutineContextProvider
import com.anb.soccerschedulematch.model.match.Match
import com.anb.soccerschedulematch.model.match.MatchResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ListMatchPresenterImpl<V: ListMatchView>(private var idLeague: String,
                                               private var position: Int,
                                               private val apiRepository: ApiRepository,
                                               private val gson: Gson,
                                               private val cContext: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<V>(), ListMatchPresenter<V> {

    override fun loadData() {
        getView().startRefresh()
        when (position) {
            in 0..1 -> {
                async(cContext.main){
                    val data = bg {
                        gson.fromJson(apiRepository
                                .doRequest(if (position == 0) TheSportDBApi.getPrevMatch(idLeague) else TheSportDBApi.getNextMatch(idLeague)),
                                MatchResponse::class.java
                        )
                    }
                    getView().setDataToRecyclerView(data.await())
                }
            }
            2 -> {
                getView().getCtx()?.database?.use {
                    val matchResponse = MatchResponse()
                    val result = select(Match.TABLE_MATCH)
                    val favoriteMatch: ArrayList<Match> = ArrayList(result.parseList(classParser()))
                    matchResponse.matchs = favoriteMatch
                    getView().setDataToRecyclerView(matchResponse)
                }
            }
        }
    }

}
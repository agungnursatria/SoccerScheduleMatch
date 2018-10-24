package com.anb.soccerschedulematch.feature.main.Fragment

import android.content.Context
import com.anb.soccerschedulematch.database.database
import com.anb.soccerschedulematch.feature.Base.BasePresenter
import com.anb.soccerschedulematch.helper.Utils
import com.anb.soccerschedulematch.model.match.Match
import com.anb.soccerschedulematch.model.match.MatchResponse
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListMatchPresenterImpl<V: ListMatchView>(private var context: Context?,
                                               private var idLeague: String,
                                               private var position: Int)
    : BasePresenter<V>(), ListMatchPresenter<V>{

    override fun loadData() {
        getView().startRefresh()
        when (position) {
            in 0..1 -> {
                val matchCall = if (position == 0) Utils.request.getPrevMatch(idLeague) else Utils.request.getNextMatch(idLeague)
                matchCall.enqueue(object : Callback<MatchResponse> {
                    override fun onFailure(call: Call<MatchResponse>?, t: Throwable?) {
                        t?.message?.let { getView().showToast(it) }
                    }

                    override fun onResponse(call: Call<MatchResponse>?, response: Response<MatchResponse>?) {
                        response?.body()?.let { getView().setDataToRecyclerView(it) }
                    }

                })
            }
            2 -> {
                context?.database?.use {
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
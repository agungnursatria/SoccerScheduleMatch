package com.anb.soccerschedulematch.feature.listmatch

import android.view.View
import com.anb.soccerschedulematch.feature.Base.MvpPresenter
import com.anb.soccerschedulematch.feature.Base.MvpView
import com.anb.soccerschedulematch.model.match.MatchResponse

interface ListMatchView: MvpView{
    fun initView(view: View)
    fun setDataToRecyclerView(matchResponse: MatchResponse)
    fun showToast(message: String)
    fun startRefresh()
    fun stopRefresh()
}

interface ListMatchPresenter<V: MvpView>: MvpPresenter<V>{
    fun loadData()
}
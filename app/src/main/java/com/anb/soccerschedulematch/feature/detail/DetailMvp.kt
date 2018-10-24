package com.anb.soccerschedulematch.feature.detail

import android.content.Context
import com.anb.soccerschedulematch.feature.Base.MvpPresenter
import com.anb.soccerschedulematch.feature.Base.MvpView
import com.anb.soccerschedulematch.model.match.Match

interface DetailView: MvpView{
    fun setFavoriteViewIcon(isFavorite: Boolean)
    fun setView(match: Match)
    fun showSnackbar(message: String)
    fun getCtx(): Context?
}

interface DetailPresenter<V: MvpView>: MvpPresenter<V>{
    fun isMatchFavorite()
    fun addToFavorite()
    fun removeFromFavorite()
    fun updateFavorite()
}
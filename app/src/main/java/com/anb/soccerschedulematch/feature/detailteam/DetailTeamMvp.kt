package com.anb.soccerschedulematch.feature.detailteam

import android.content.Context
import com.anb.soccerschedulematch.feature.Base.MvpPresenter
import com.anb.soccerschedulematch.feature.Base.MvpView
import com.anb.soccerschedulematch.model.player.Player
import com.anb.soccerschedulematch.model.team.Team


interface DetailTeamView: MvpView {
    fun setFavoriteViewIcon(isFavorite: Boolean)
    fun setView(team: Team)
    fun showSnackbar(message: String)
    fun getCtx(): Context?
}

interface DetailTeamPresenter<V: MvpView>: MvpPresenter<V> {
    fun isTeamFavorite()
    fun addToFavorite()
    fun removeFromFavorite()
    fun updateFavorite()
    fun getPlayer(teamName: String): ArrayList<Player>
}
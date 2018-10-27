package com.anb.soccerschedulematch.feature.detailteam

import com.anb.soccerschedulematch.feature.Base.BasePresenter
import com.anb.soccerschedulematch.model.player.Player
import com.anb.soccerschedulematch.model.team.Team

class DetailTeamPresenterImpl<V: DetailTeamView>(var team:Team)
    : BasePresenter<V>(), DetailTeamPresenter<V>{

    override fun getPlayer(teamName: String) : ArrayList<Player> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isTeamFavorite() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addToFavorite() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeFromFavorite() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateFavorite() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
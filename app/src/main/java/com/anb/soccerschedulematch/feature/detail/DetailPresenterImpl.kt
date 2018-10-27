package com.anb.soccerschedulematch.feature.detail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.anb.soccerschedulematch.database.database
import com.anb.soccerschedulematch.feature.Base.BasePresenter
import com.anb.soccerschedulematch.helper.Constant
import com.anb.soccerschedulematch.helper.Utils
import com.anb.soccerschedulematch.model.match.Match
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailPresenterImpl<V: DetailView>(var match: Match)
    : BasePresenter<V>(), DetailPresenter<V>{

    var isFavorite = false

    override fun isMatchFavorite() {
        getView().getCtx()?.database?.use {
            match.idEvent?.let {
                val result = select(Constant.TABLE_MATCH)
                        .whereArgs("(ID_EVENT = {id})", "id" to it)
                val favorite = result.parseList(classParser<Match>())
                if (!favorite.isEmpty()) isFavorite = true
                getView().setFavoriteViewIcon(isFavorite)
            }
        }
    }

    override fun addToFavorite() {
        try {
            val matchShown = Utils.removeNullString(match)
            getView().getCtx()?.database?.use {
                insert(Constant.TABLE_MATCH,
                        Constant.ID_EVENT to matchShown.idEvent,
                        Constant.HOME_NAME to matchShown.strHomeTeam,
                        Constant.AWAY_NAME to matchShown.strAwayTeam,
                        Constant.HOME_SCORE to matchShown.strHomeScore,
                        Constant.AWAY_SCORE to matchShown.strAwayScore,
                        Constant.HOME_GOAL_DETAILS to matchShown.strHomeGoalDetails,
                        Constant.HOME_RED_CARDS to matchShown.strHomeRedCards,
                        Constant.HOME_YELLOW_CARDS to matchShown.strHomeYellowCards,
                        Constant.HOME_GOALKEEPER to matchShown.strHomeLineupGoalkeeper,
                        Constant.HOME_DEFENDER to matchShown.strHomeLineupDefense,
                        Constant.HOME_MIDFIELD to matchShown.strHomeLineupMidfield,
                        Constant.HOME_FORWARD to matchShown.strHomeLineupForward,
                        Constant.HOME_SUBTITUTE to matchShown.strHomeLineupSubstitutes,
                        Constant.AWAY_RED_CARDS to matchShown.strAwayRedCards,
                        Constant.AWAY_YELLOW_CARDS to matchShown.strAwayYellowCards,
                        Constant.AWAY_GOAL_DETAILS to matchShown.strHomeGoalDetails,
                        Constant.AWAY_GOALKEEPER to matchShown.strAwayLineupGoalkeeper,
                        Constant.AWAY_DEFENDER to matchShown.strAwayLineupDefense,
                        Constant.AWAY_MIDFIELD to matchShown.strAwayLineupMidfield,
                        Constant.AWAY_FORWARD to matchShown.strAwayLineupForward,
                        Constant.AWAY_SUBTITUTE to matchShown.strAwayLineupSubstitutes,
                        Constant.HOME_SHOTS to matchShown.strHomeShots,
                        Constant.AWAY_SHOTS to matchShown.strAwayShots,
                        Constant.DATE_EVENT to matchShown.dateEvent,
                        Constant.ID_HOME to matchShown.idHomeTeam,
                        Constant.ID_AWAY to matchShown.idAwayTeam
                )
            }
            getView().showSnackbar("Added to favorite")
        } catch (e: SQLiteConstraintException){
            getView().showSnackbar(e.localizedMessage)
        }
    }

    override fun removeFromFavorite() {
        try {
            getView().getCtx()?.database?.use {
                match.idEvent?.let {
                    delete(Constant.TABLE_MATCH,
                            "(ID_EVENT = {id})", "id" to it)
                }
            }
            getView().showSnackbar("Removed from favorite")
        } catch (e: SQLiteConstraintException){
            getView().showSnackbar(e.localizedMessage)
        }
    }

    override fun updateFavorite() {
        if (isFavorite) removeFromFavorite() else addToFavorite()

        isFavorite = !isFavorite
        getView().setFavoriteViewIcon(isFavorite)
    }

}
package com.anb.soccerschedulematch.feature.detail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.anb.soccerschedulematch.database.database
import com.anb.soccerschedulematch.feature.Base.BasePresenter
import com.anb.soccerschedulematch.helper.Utils
import com.anb.soccerschedulematch.model.match.Match
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailPresenterImpl<V: DetailView>(val context: Context,
                                         var match: Match)
    : BasePresenter<V>(), DetailPresenter<V>{

    var isFavorite = false

    override fun isMatchFavorite() {
        context.database.use {
            match.idEvent?.let {
                val result = select(Match.TABLE_MATCH)
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
            context.database.use {
                insert(Match.TABLE_MATCH,
                        Match.ID_EVENT to matchShown.idEvent,
                        Match.HOME_NAME to matchShown.strHomeTeam,
                        Match.AWAY_NAME to matchShown.strAwayTeam,
                        Match.HOME_SCORE to matchShown.strHomeScore,
                        Match.AWAY_SCORE to matchShown.strAwayScore,
                        Match.HOME_GOAL_DETAILS to matchShown.strHomeGoalDetails,
                        Match.HOME_RED_CARDS to matchShown.strHomeRedCards,
                        Match.HOME_YELLOW_CARDS to matchShown.strHomeYellowCards,
                        Match.HOME_GOALKEEPER to matchShown.strHomeLineupGoalkeeper,
                        Match.HOME_DEFENDER to matchShown.strHomeLineupDefense,
                        Match.HOME_MIDFIELD to matchShown.strHomeLineupMidfield,
                        Match.HOME_FORWARD to matchShown.strHomeLineupForward,
                        Match.HOME_SUBTITUTE to matchShown.strHomeLineupSubstitutes,
                        Match.AWAY_RED_CARDS to matchShown.strAwayRedCards,
                        Match.AWAY_YELLOW_CARDS to matchShown.strAwayYellowCards,
                        Match.AWAY_GOAL_DETAILS to matchShown.strHomeGoalDetails,
                        Match.AWAY_GOALKEEPER to matchShown.strAwayLineupGoalkeeper,
                        Match.AWAY_DEFENDER to matchShown.strAwayLineupDefense,
                        Match.AWAY_MIDFIELD to matchShown.strAwayLineupMidfield,
                        Match.AWAY_FORWARD to matchShown.strAwayLineupForward,
                        Match.AWAY_SUBTITUTE to matchShown.strAwayLineupSubstitutes,
                        Match.HOME_SHOTS to matchShown.strHomeShots,
                        Match.AWAY_SHOTS to matchShown.strAwayShots,
                        Match.DATE_EVENT to matchShown.dateEvent,
                        Match.ID_HOME to matchShown.idHomeTeam,
                        Match.ID_AWAY to matchShown.idAwayTeam
                )
            }
            getView().showSnackbar("Added to favorite")
        } catch (e: SQLiteConstraintException){
            getView().showSnackbar(e.localizedMessage)
        }
    }

    override fun removeFromFavorite() {
        try {
            context.database.use {
                match.idEvent?.let {
                    delete(Match.TABLE_MATCH,
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
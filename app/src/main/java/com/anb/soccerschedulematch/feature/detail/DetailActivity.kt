package com.anb.soccerschedulematch.feature.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.anb.soccerschedulematch.database.database
import com.anb.soccerschedulematch.helper.Constant
import com.anb.soccerschedulematch.helper.Utils
import com.anb.soccerschedulematch.model.match.Match
import com.anb.soccerschedulematch.R
import com.anb.soccerschedulematch.R.menu.detail_menu
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailActivity : AppCompatActivity() {
    var menuDetail : Menu? = null
    lateinit var match: Match
    var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        match = intent.getParcelableExtra<Match>(Constant.MATCH)
        setView(match)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuDetail = menu
        isMatchFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home ->{
                onBackPressed()
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavoriteViewIcon()

            }
            else -> {}
        }
        return true
    }

    private fun isMatchFavorite(){
        database.use {
            match.idEvent?.let {
                val result = select(Match.TABLE_MATCH)
                        .whereArgs("(ID_EVENT = {id})", "id" to it)
                val favorite = result.parseList(classParser<Match>())
                if (!favorite.isEmpty()) isFavorite = true
                setFavoriteViewIcon()
            }
        }
    }

    private fun addToFavorite(){
        try {
            val sendedMatch = Utils.removeNullString(match)
            database.use {
                insert(Match.TABLE_MATCH,
                        Match.ID_EVENT to sendedMatch.idEvent,
                        Match.HOME_NAME to sendedMatch.strHomeTeam,
                        Match.AWAY_NAME to sendedMatch.strAwayTeam,
                        Match.HOME_SCORE to sendedMatch.strHomeScore,
                        Match.AWAY_SCORE to sendedMatch.strAwayScore,
                        Match.HOME_GOAL_DETAILS to sendedMatch.strHomeGoalDetails,
                        Match.HOME_RED_CARDS to sendedMatch.strHomeRedCards,
                        Match.HOME_YELLOW_CARDS to sendedMatch.strHomeYellowCards,
                        Match.HOME_GOALKEEPER to sendedMatch.strHomeLineupGoalkeeper,
                        Match.HOME_DEFENDER to sendedMatch.strHomeLineupDefense,
                        Match.HOME_MIDFIELD to sendedMatch.strHomeLineupMidfield,
                        Match.HOME_FORWARD to sendedMatch.strHomeLineupForward,
                        Match.HOME_SUBTITUTE to sendedMatch.strHomeLineupSubstitutes,
                        Match.AWAY_RED_CARDS to sendedMatch.strAwayRedCards,
                        Match.AWAY_YELLOW_CARDS to sendedMatch.strAwayYellowCards,
                        Match.AWAY_GOAL_DETAILS to sendedMatch.strHomeGoalDetails,
                        Match.AWAY_GOALKEEPER to sendedMatch.strAwayLineupGoalkeeper,
                        Match.AWAY_DEFENDER to sendedMatch.strAwayLineupDefense,
                        Match.AWAY_MIDFIELD to sendedMatch.strAwayLineupMidfield,
                        Match.AWAY_FORWARD to sendedMatch.strAwayLineupForward,
                        Match.AWAY_SUBTITUTE to sendedMatch.strAwayLineupSubstitutes,
                        Match.HOME_SHOTS to sendedMatch.strHomeShots,
                        Match.AWAY_SHOTS to sendedMatch.strAwayShots,
                        Match.DATE_EVENT to sendedMatch.dateEvent,
                        Match.ID_HOME to sendedMatch.idHomeTeam,
                        Match.ID_AWAY to sendedMatch.idAwayTeam
                )
            }
            snackbar(ll_detailActivity, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(ll_detailActivity, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                match.idEvent?.let {
                    delete(Match.TABLE_MATCH,
                            "(ID_EVENT = {id})", "id" to it)
                }
            }
            snackbar(ll_detailActivity, "Removed from favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(ll_detailActivity, e.localizedMessage).show()
        }
    }

    private fun setFavoriteViewIcon(){
        menuDetail?.getItem(0)?.icon = if (isFavorite)
                            ContextCompat.getDrawable(this,
                                    R.drawable.ic_added_to_favorites)
                        else
                            ContextCompat.getDrawable(this,
                                    R.drawable.ic_add_to_favorites)
    }

    private fun setView(match: Match) {
        with(match){
            dateEvent?.let { tvMatchTimeDetail.text = Utils.formatDate(it) }
            strHomeTeam?.let { tvHomeTeamNameDetail.text = it }
            strHomeScore?.let { tvHomeTeamScoreDetail.text = it }
            strAwayTeam?.let { tvAwayTeamNameDetail.text = it }
            strAwayScore?.let { tvAwayTeamScoreDetail.text = it }

            idHomeTeam?.let { Utils.getImageBadgeTeam(it, imgHomeTeamDetail) }
            idAwayTeam?.let { Utils.getImageBadgeTeam(it, imgAwayTeamDetail) }

            strHomeGoalDetails?.let { tvHomeGoals.text = Utils.semicolonReplacer(it) }
            strAwayGoalDetails?.let { tvAwayGoals.text = Utils.semicolonReplacer(it) }

            strHomeShots?.let { tvHomeShot.text = it }
            strAwayShots?.let { tvAwayShot.text = it }

            strHomeLineupGoalkeeper?.let { tvHomeGoalkeeper.text = Utils.semicolonReplacer(it) }
            strAwayLineupGoalkeeper?.let { tvAwayGoalKeeper.text = Utils.semicolonReplacer(it) }

            strHomeLineupDefense?.let { tvHomeDefender.text = Utils.semicolonReplacer(it) }
            strAwayLineupDefense?.let { tvAwayDefender.text = Utils.semicolonReplacer(it) }

            strHomeLineupMidfield?.let { tvHomeMidfield.text = Utils.semicolonReplacer(it) }
            strAwayLineupMidfield?.let { tvAwayMidfield.text = Utils.semicolonReplacer(it) }

            strHomeLineupForward?.let { tvHomeForward.text = Utils.semicolonReplacer(it) }
            strAwayLineupForward?.let { tvAwayForward.text = Utils.semicolonReplacer(it) }

            strHomeLineupSubstitutes?.let { tvHomeSubtitute.text = Utils.semicolonReplacer(it) }
            strAwayLineupSubstitutes?.let { tvAwaySubtitute.text = Utils.semicolonReplacer(it) }

            strHomeYellowCards?.let { tvHomeYellowCard.text = if (!it.isBlank()) Utils.semicolonReplacer(it) else "-" }
            strAwayYellowCards?.let { tvAwayYellowCard.text = if (!it.isBlank()) Utils.semicolonReplacer(it) else "-" }

            strHomeRedCards?.let { tvHomeRedCard.text = if (!it.isBlank()) Utils.semicolonReplacer(it) else "-" }
            strAwayRedCards?.let { tvAwayRedCard.text = if (!it.isBlank()) Utils.semicolonReplacer(it) else "-" }
        }
    }
}

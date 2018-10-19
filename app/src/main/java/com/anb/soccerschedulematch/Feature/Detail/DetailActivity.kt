package com.anb.soccerschedulematch.Feature.Detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.anb.soccerschedulematch.Helper.Constant
import com.anb.soccerschedulematch.Helper.Utils
import com.anb.soccerschedulematch.Model.Match.Match
import com.anb.soccerschedulematch.Model.Match.MatchResponse
import com.anb.soccerschedulematch.R
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val idEvent = intent.getStringExtra(Constant.ID_MATCH)
        initMatchDetail(idEvent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home ->{
                onBackPressed()
            }
            else -> {}
        }
        return true
    }

    private fun initMatchDetail(idEvent: String) {
        val matchDetailCall = Utils.request.getEventDetail(idEvent)
        matchDetailCall.enqueue(object : Callback<MatchResponse>{
            override fun onFailure(call: Call<MatchResponse>?, t: Throwable?) {
                t?.message?.let { toast(it) }
            }

            override fun onResponse(call: Call<MatchResponse>?, response: Response<MatchResponse>?) {
                response?.body()?.let { setView(it.matchs[0]) }
            }

        })
    }

    private fun setView(match: Match) {
        with(match){
            dateEvent?.let { tvMatchTimeDetail.text = Utils.formatDate(it) }
            strHomeTeam?.let { tvHomeTeamNameDetail.text = it }
            intHomeScore?.let { tvHomeTeamScoreDetail.text = it.toString() }
            strAwayTeam?.let { tvAwayTeamNameDetail.text = it }
            intAwayScore?.let { tvAwayTeamScoreDetail.text = it.toString() }

            idHomeTeam?.let { Utils.getImageBadgeTeam(it, imgHomeTeamDetail) }
            idAwayTeam?.let { Utils.getImageBadgeTeam(it, imgAwayTeamDetail) }

            strHomeGoalDetails?.let { tvHomeGoals.text = Utils.semicolonReplacer(it) }
            strAwayGoalDetails?.let { tvAwayGoals.text = Utils.semicolonReplacer(it) }

            intHomeShots?.let { tvHomeShot.text = it.toString() }
            intAwayShots?.let { tvAwayShot.text = it.toString() }

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

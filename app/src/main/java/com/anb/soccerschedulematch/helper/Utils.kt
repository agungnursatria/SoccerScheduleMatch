package com.anb.soccerschedulematch.helper

import android.view.View
import android.widget.ImageView
import com.anb.soccerschedulematch.api.RetroServer
import com.anb.soccerschedulematch.model.match.Match
import com.anb.soccerschedulematch.model.team.TeamResponse
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    val request = RetroServer.getRequestService()

    fun formatDate(dateEvent : String): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = format.parse(dateEvent)
        return SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
                .format(date).toString()
    }


    fun getImageBadgeTeam(id : String, imageView : ImageView){
        val teamCall : Call<TeamResponse> = request.getTeamDetail(id)
        teamCall.enqueue(object : Callback<TeamResponse> {
            override fun onFailure(call: Call<TeamResponse>?, t: Throwable?) {}

            override fun onResponse(call: Call<TeamResponse>?, response: Response<TeamResponse>?) {
                response?.body()?.let { Picasso.get().load(it.teams[0].strTeamBadge).into(imageView) }
            }

        })
    }

    fun semicolonReplacer(text: String): String {
        var newText = text.replace("; ","\n")
                .replace(";","\n")
        return newText
    }

    fun removeNullString(match: Match) : Match{
        with(match){
            idEvent = if (idEvent.isNullOrBlank()) "" else idEvent
            strHomeTeam = if (strHomeTeam.isNullOrBlank()) "" else strHomeTeam
            strAwayTeam = if (strAwayTeam.isNullOrBlank()) "" else strAwayTeam
            strHomeScore = if (strHomeScore.isNullOrBlank()) "" else strHomeScore
            strAwayScore = if (strAwayScore.isNullOrBlank()) "" else strAwayScore
            strHomeGoalDetails = if (strHomeGoalDetails.isNullOrBlank()) "" else strHomeGoalDetails
            strHomeRedCards = if (strHomeRedCards.isNullOrBlank()) "" else strHomeRedCards
            strHomeYellowCards = if (strHomeYellowCards.isNullOrBlank()) "" else strHomeYellowCards
            strHomeLineupGoalkeeper = if (strHomeLineupGoalkeeper.isNullOrBlank()) "" else strHomeLineupGoalkeeper
            strHomeLineupDefense = if (strHomeLineupDefense.isNullOrBlank()) "" else strHomeLineupDefense
            strHomeLineupMidfield = if (strHomeLineupMidfield.isNullOrBlank()) "" else strHomeLineupMidfield
            strHomeLineupForward = if (strHomeLineupForward.isNullOrBlank()) "" else strHomeLineupForward
            strHomeLineupSubstitutes = if (strHomeLineupSubstitutes.isNullOrBlank()) "" else strHomeLineupSubstitutes
            strAwayRedCards = if (strAwayRedCards.isNullOrBlank()) "" else strAwayRedCards
            strAwayYellowCards = if (strHomeYellowCards.isNullOrBlank()) "" else strHomeYellowCards
            strAwayLineupGoalkeeper = if (strAwayLineupGoalkeeper.isNullOrBlank()) "" else strAwayLineupGoalkeeper
            strAwayLineupDefense = if (strAwayLineupDefense.isNullOrBlank()) "" else strAwayLineupDefense
            strAwayLineupMidfield = if (strAwayLineupMidfield.isNullOrBlank()) "" else strAwayLineupMidfield
            strAwayLineupForward = if (strAwayLineupForward.isNullOrBlank()) "" else strAwayLineupForward
            strAwayLineupSubstitutes = if (strAwayLineupSubstitutes.isNullOrBlank()) "" else strAwayLineupSubstitutes
            strHomeShots = if (strHomeShots.isNullOrBlank()) "" else strHomeShots
            strAwayShots = if (strAwayShots.isNullOrBlank()) "" else strAwayShots
            dateEvent = if (dateEvent.isNullOrBlank()) "" else dateEvent
            idHomeTeam = if (idHomeTeam.isNullOrBlank()) "" else idHomeTeam
            idAwayTeam = if (idAwayTeam.isNullOrBlank()) "" else idAwayTeam
        }
        return match
    }
}
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}
package com.anb.soccerschedulematch.helper

import android.view.View
import android.widget.ImageView
import com.anb.soccerschedulematch.api.ApiRepository
import com.anb.soccerschedulematch.api.TheSportDBApi
import com.anb.soccerschedulematch.model.match.Match
import com.anb.soccerschedulematch.model.team.TeamResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun formatDate(dateEvent : String): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = format.parse(dateEvent)
        return SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
                .format(date).toString()
    }


    fun getImageBadgeTeam(id : String, imageView : ImageView){
        val gson = Gson()
        val apiRepository = ApiRepository()
        val context = CoroutineContextProvider()

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(id)),
                        TeamResponse::class.java
                )
            }
            Picasso.get().load(data.await().teams[0].strTeamBadge).into(imageView)
        }
    }

    fun semicolonReplacer(text: String): String {
        return text.replace("; ","\n")
                .replace(";","\n")
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
package com.anb.soccerschedulematch.Api

import com.anb.soccerschedulematch.Model.League.LeagueResponse
import com.anb.soccerschedulematch.Model.Match.MatchResponse
import com.anb.soccerschedulematch.Model.Team.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestInterface {

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatch(@Query("id") idLeague: String): Call<MatchResponse>

    @GET("api/v1/json/1/eventspastleague.php")
    fun getPrevMatch(@Query("id") idLeague: String) : Call<MatchResponse>

    @GET("api/v1/json/1/all_leagues.php")
    fun getAllLeague() : Call<LeagueResponse>

    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamDetail(@Query("id") idTeam: String) : Call<TeamResponse>

    @GET("api/v1/json/1/lookupevent.php")
    fun getEventDetail(@Query("id") idEvent: String) : Call<MatchResponse>
}
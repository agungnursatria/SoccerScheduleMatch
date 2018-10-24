package com.anb.soccerschedulematch.api

import com.anb.soccerschedulematch.BuildConfig

object TheSportDBApi {
    fun getTeamDetail(idTeam: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + idTeam
    }

    fun getAllLeague(): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/all_leagues.php"
    }

    fun getNextMatch(idLeague: String): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + idLeague
    }
    fun getPrevMatch(idLeague: String): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + idLeague
    }
}
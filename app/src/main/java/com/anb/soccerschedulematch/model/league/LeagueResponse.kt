package com.anb.soccerschedulematch.model.league

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LeagueResponse(
        @SerializedName("leagues")
        @Expose
        val leagues: ArrayList<League>)
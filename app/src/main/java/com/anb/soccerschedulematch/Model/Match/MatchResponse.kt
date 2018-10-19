package com.anb.soccerschedulematch.Model.Match

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MatchResponse(
        @SerializedName("events")
        @Expose
        val matchs: ArrayList<Match>)
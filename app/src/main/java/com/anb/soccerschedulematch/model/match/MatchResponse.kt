package com.anb.soccerschedulematch.model.match

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MatchResponse(
        @SerializedName("events")
        @Expose
        var matchs: ArrayList<Match>? = null)
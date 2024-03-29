package com.anb.soccerschedulematch.Model.Team

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TeamResponse(
        @SerializedName("teams")
        @Expose
        val teams: ArrayList<Team>)
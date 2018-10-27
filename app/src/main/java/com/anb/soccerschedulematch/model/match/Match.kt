package com.anb.soccerschedulematch.model.match

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Match (
        @SerializedName("idEvent")
        @Expose
        var idEvent: String? = null,

        @SerializedName("strHomeTeam")
        @Expose
        var strHomeTeam: String? = null,

        @SerializedName("strAwayTeam")
        @Expose
        var strAwayTeam: String? = null,

        @SerializedName("intHomeScore")
        @Expose
        var strHomeScore: String? = null,

        @SerializedName("intAwayScore")
        @Expose
        var strAwayScore: String? = null,

        @SerializedName("strHomeGoalDetails")
        @Expose
        var strHomeGoalDetails: String? = null,

        @SerializedName("strHomeRedCards")
        @Expose
        var strHomeRedCards: String? = null,

        @SerializedName("strHomeYellowCards")
        @Expose
        var strHomeYellowCards: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        @Expose
        var strHomeLineupGoalkeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        @Expose
        var strHomeLineupDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        @Expose
        var strHomeLineupMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        @Expose
        var strHomeLineupForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        @Expose
        var strHomeLineupSubstitutes: String? = null,

        @SerializedName("strAwayRedCards")
        @Expose
        var strAwayRedCards: String? = null,

        @SerializedName("strAwayYellowCards")
        @Expose
        var strAwayYellowCards: String? = null,

        @SerializedName("strAwayGoalDetails")
        @Expose
        var strAwayGoalDetails: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        @Expose
        var strAwayLineupGoalkeeper: String? = null,

        @SerializedName("strAwayLineupDefense")
        @Expose
        var strAwayLineupDefense: String? = null,

        @SerializedName("strAwayLineupMidfield")
        @Expose
        var strAwayLineupMidfield: String? = null,

        @SerializedName("strAwayLineupForward")
        @Expose
        var strAwayLineupForward: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        @Expose
        var strAwayLineupSubstitutes: String? = null,

        @SerializedName("intHomeShots")
        @Expose
        var strHomeShots: String? = null,

        @SerializedName("intAwayShots")
        @Expose
        var strAwayShots: String? = null,

        @SerializedName("dateEvent")
        @Expose
        var dateEvent: String? = null,

        @SerializedName("idHomeTeam")
        @Expose
        var idHomeTeam: String? = null,

        @SerializedName("idAwayTeam")
        @Expose
        var idAwayTeam: String? = null


) : Parcelable {

        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()) {
        }

        override fun toString(): String {
                return super.toString()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(idEvent)
                parcel.writeString(strHomeTeam)
                parcel.writeString(strAwayTeam)
                parcel.writeString(strHomeScore)
                parcel.writeString(strAwayScore)
                parcel.writeString(strHomeGoalDetails)
                parcel.writeString(strHomeRedCards)
                parcel.writeString(strHomeYellowCards)
                parcel.writeString(strHomeLineupGoalkeeper)
                parcel.writeString(strHomeLineupDefense)
                parcel.writeString(strHomeLineupMidfield)
                parcel.writeString(strHomeLineupForward)
                parcel.writeString(strHomeLineupSubstitutes)
                parcel.writeString(strAwayRedCards)
                parcel.writeString(strAwayYellowCards)
                parcel.writeString(strAwayGoalDetails)
                parcel.writeString(strAwayLineupGoalkeeper)
                parcel.writeString(strAwayLineupDefense)
                parcel.writeString(strAwayLineupMidfield)
                parcel.writeString(strAwayLineupForward)
                parcel.writeString(strAwayLineupSubstitutes)
                parcel.writeString(strHomeShots)
                parcel.writeString(strAwayShots)
                parcel.writeString(dateEvent)
                parcel.writeString(idHomeTeam)
                parcel.writeString(idAwayTeam)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Match> {
                override fun createFromParcel(parcel: Parcel): Match {
                        return Match(parcel)
                }

                override fun newArray(size: Int): Array<Match?> {
                        return arrayOfNulls(size)
                }
        }
}
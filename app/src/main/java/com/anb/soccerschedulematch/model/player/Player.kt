package com.anb.soccerschedulematch.model.player

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Player (
    @SerializedName("idPlayer")
    var idPlayer : String?,

    @SerializedName("strPlayer")
    var strPlayer : String?,

    @SerializedName("dateBorn")
    var dateBorn : String?,

    @SerializedName("strBirthLocation")
    var strBirthLocation : String?,

    @SerializedName("strPosition")
    var strPosition : String?,

    @SerializedName("strThumb")
    var strThumb : String?,

    @SerializedName("strCutout")
    var strCutout : String?,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN : String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idPlayer)
        parcel.writeString(strPlayer)
        parcel.writeString(dateBorn)
        parcel.writeString(strBirthLocation)
        parcel.writeString(strPosition)
        parcel.writeString(strThumb)
        parcel.writeString(strCutout)
        parcel.writeString(strDescriptionEN)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }

}
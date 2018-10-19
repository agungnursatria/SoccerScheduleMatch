package com.anb.soccerschedulematch.Helper

import android.view.View
import android.widget.ImageView
import com.anb.soccerschedulematch.Api.RetroServer
import com.anb.soccerschedulematch.Model.Team.TeamResponse
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
}
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}
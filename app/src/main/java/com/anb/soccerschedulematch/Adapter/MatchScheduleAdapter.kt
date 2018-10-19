package com.anb.soccerschedulematch.Adapter

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.anb.soccerschedulematch.Api.RetroServer
import com.anb.soccerschedulematch.Helper.Utils
import com.anb.soccerschedulematch.Model.Match.Match
import com.anb.soccerschedulematch.Model.Team.TeamResponse
import com.anb.soccerschedulematch.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MatchScheduleAdapter(private val matchList: ArrayList<Match>, private val listener: (Match) -> Unit): RecyclerView.Adapter<MatchScheduleVH>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MatchScheduleVH {
        return MatchScheduleVH(MatchScheduleUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(p0: MatchScheduleVH, p1: Int) {
        p0.bindItem(matchList[p1], listener)
    }

}

class MatchScheduleUI: AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
        verticalLayout {
            lparams(matchParent, wrapContent)
            padding = dip(8)
            textView {
                id = R.id.tvDateMatch
                text = "Kamis, 18 Oktober 2018"
                gravity = Gravity.CENTER_HORIZONTAL
                bottomPadding = dip(8)
            }.lparams(matchParent, wrapContent)

            linearLayout {
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.imgHomeTeam
                }.lparams(dip(40),dip(40)){
                    gravity = Gravity.CENTER_VERTICAL
                    weight = 0.1f
                }

                textView {
                    id = R.id.tvHomeTeamName
                }.lparams(wrapContent, wrapContent){
                    gravity = Gravity.CENTER_VERTICAL
                    weight = 0.1f
                }

                textView {
                    id = R.id.tvHomeTeamScore
                }.lparams(wrapContent, wrapContent){
                    gravity = Gravity.CENTER_VERTICAL
                    weight = 0.1f
                }

                textView {
                    id = R.id.middleStrip
                    text = "-"
                }.lparams(wrapContent, wrapContent){
                    gravity = Gravity.CENTER_VERTICAL
                    weight = 0.1f
                }

                textView {
                    id = R.id.tvAwayTeamScore
                }.lparams(wrapContent, wrapContent){
                    gravity = Gravity.CENTER_VERTICAL
                    weight = 0.1f
                }

                textView {
                    id = R.id.tvAwayTeamName
                }.lparams(wrapContent, wrapContent){
                    gravity = Gravity.CENTER_VERTICAL
                    weight = 0.1f
                }

                imageView {
                    id = R.id.imgAwayTeam
                }.lparams(dip(40),dip(40)){
                    gravity = Gravity.CENTER_VERTICAL
                    weight = 0.1f
                }
            }.lparams(matchParent, wrapContent, 0.6f)
        }
    }
}

class MatchScheduleVH(val view: View) : RecyclerView.ViewHolder(view){
    private val tvDateMatch: TextView = view.find(R.id.tvDateMatch)
    private val imgHomeTeam: ImageView = view.find(R.id.imgHomeTeam)
    private val tvHomeTeamName: TextView = view.find(R.id.tvHomeTeamName)
    private val tvHomeTeamScore: TextView = view.find(R.id.tvHomeTeamScore)
    private val tvAwayTeamScore: TextView = view.find(R.id.tvAwayTeamScore)
    private val tvAwayTeamName: TextView = view.find(R.id.tvAwayTeamName)
    private val imgAwayTeam: ImageView = view.find(R.id.imgAwayTeam)

    fun bindItem(match: Match, listener: (Match) -> Unit) {
        with(match){
            dateEvent?.let { tvDateMatch.text = Utils.formatDate(it) }
            strHomeTeam?.let { tvHomeTeamName.text = it }
            intHomeScore?.let { tvHomeTeamScore.text = it.toString() }
            strAwayTeam?.let { tvAwayTeamName.text = it }
            intAwayScore?.let { tvAwayTeamScore.text = it.toString() }

            idHomeTeam?.let { Utils.getImageBadgeTeam(it, imgHomeTeam) }
            idAwayTeam?.let { Utils.getImageBadgeTeam(it, imgAwayTeam) }

            view.setOnClickListener {
                listener(match)
            }
        }
    }
}
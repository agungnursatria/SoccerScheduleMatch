package com.anb.soccerschedulematch.feature.detail

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.anb.soccerschedulematch.R
import com.anb.soccerschedulematch.R.menu.detail_menu
import com.anb.soccerschedulematch.helper.Constant
import com.anb.soccerschedulematch.helper.Utils
import com.anb.soccerschedulematch.model.match.Match
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.design.snackbar

class DetailActivity : AppCompatActivity(),DetailView {

    var menuDetail : Menu? = null
    lateinit var DPresenter : DetailPresenter<DetailView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val match = intent.getParcelableExtra<Match>(Constant.MATCH)
        DPresenter = DetailPresenterImpl( match)
        DPresenter.onAttach(this)
        setView(match)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuDetail = menu
        DPresenter.isMatchFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home ->{
                onBackPressed()
            }
            R.id.add_to_favorite -> {
                DPresenter.updateFavorite()
            }
            else -> {}
        }
        return true
    }

    override fun setFavoriteViewIcon(isFavorite: Boolean){
        menuDetail?.getItem(0)?.icon = if (isFavorite)
                            ContextCompat.getDrawable(this,
                                    R.drawable.ic_added_to_favorites)
                        else
                            ContextCompat.getDrawable(this,
                                    R.drawable.ic_add_to_favorites)
    }

    override fun getCtx(): Context?{
        return this
    }

    override fun setView(match: Match) {
        with(match){
            dateEvent?.let { tvMatchTimeDetail.text = Utils.formatDate(it) }
            strHomeTeam?.let { tvHomeTeamNameDetail.text = it }
            strHomeScore?.let { tvHomeTeamScoreDetail.text = it }
            strAwayTeam?.let { tvAwayTeamNameDetail.text = it }
            strAwayScore?.let { tvAwayTeamScoreDetail.text = it }

            idHomeTeam?.let { Utils.getImageBadgeTeam(it, imgHomeTeamDetail) }
            idAwayTeam?.let { Utils.getImageBadgeTeam(it, imgAwayTeamDetail) }

            strHomeGoalDetails?.let { tvHomeGoals.text = Utils.semicolonReplacer(it) }
            strAwayGoalDetails?.let { tvAwayGoals.text = Utils.semicolonReplacer(it) }

            strHomeShots?.let { tvHomeShot.text = it }
            strAwayShots?.let { tvAwayShot.text = it }

            strHomeLineupGoalkeeper?.let { tvHomeGoalkeeper.text = Utils.semicolonReplacer(it) }
            strAwayLineupGoalkeeper?.let { tvAwayGoalKeeper.text = Utils.semicolonReplacer(it) }

            strHomeLineupDefense?.let { tvHomeDefender.text = Utils.semicolonReplacer(it) }
            strAwayLineupDefense?.let { tvAwayDefender.text = Utils.semicolonReplacer(it) }

            strHomeLineupMidfield?.let { tvHomeMidfield.text = Utils.semicolonReplacer(it) }
            strAwayLineupMidfield?.let { tvAwayMidfield.text = Utils.semicolonReplacer(it) }

            strHomeLineupForward?.let { tvHomeForward.text = Utils.semicolonReplacer(it) }
            strAwayLineupForward?.let { tvAwayForward.text = Utils.semicolonReplacer(it) }

            strHomeLineupSubstitutes?.let { tvHomeSubtitute.text = Utils.semicolonReplacer(it) }
            strAwayLineupSubstitutes?.let { tvAwaySubtitute.text = Utils.semicolonReplacer(it) }

            strHomeYellowCards?.let { tvHomeYellowCard.text = if (!it.isBlank()) Utils.semicolonReplacer(it) else "-" }
            strAwayYellowCards?.let { tvAwayYellowCard.text = if (!it.isBlank()) Utils.semicolonReplacer(it) else "-" }

            strHomeRedCards?.let { tvHomeRedCard.text = if (!it.isBlank()) Utils.semicolonReplacer(it) else "-" }
            strAwayRedCards?.let { tvAwayRedCard.text = if (!it.isBlank()) Utils.semicolonReplacer(it) else "-" }
        }
    }
    override fun showSnackbar(message: String) {
        snackbar(ll_detailActivity, message).show()
    }
}

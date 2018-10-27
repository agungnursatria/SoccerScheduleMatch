package com.anb.soccerschedulematch.feature.detailteam

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.anb.soccerschedulematch.R
import com.anb.soccerschedulematch.adapter.PlayerAdapter
import com.anb.soccerschedulematch.feature.detailplayer.DetailPlayerActivity
import com.anb.soccerschedulematch.helper.Constant
import com.anb.soccerschedulematch.model.player.Player
import com.anb.soccerschedulematch.model.team.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {
    var menuDetail : Menu? = null
    var team = Team()

    lateinit var DTPresenter : DetailTeamPresenter<DetailTeamView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        team = intent.getParcelableExtra<Team>(Constant.TEAM)
        DTPresenter = DetailTeamPresenterImpl(team)
        DTPresenter.onAttach(this)
        setView(team)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuDetail = menu
        DTPresenter.isTeamFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home ->{
                onBackPressed()
            }
            R.id.add_to_favorite -> {
                DTPresenter.updateFavorite()
            }
            else -> {}
        }
        return true
    }

    override fun setFavoriteViewIcon(isFavorite: Boolean) {
        menuDetail?.getItem(0)?.icon = if (isFavorite)
            ContextCompat.getDrawable(this,
                    R.drawable.ic_added_to_favorites)
        else
            ContextCompat.getDrawable(this,
                    R.drawable.ic_add_to_favorites)
    }

    override fun setView(team: Team) {
        Picasso.get().load(team.strTeamBadge).into(iv_teamBadge)
        tv_teamName.text = team.strTeam
        tv_teamYear.text = team.intFormedYear
        tv_teamStadium.text = team.strStadium
        tv_teamDesc.text = team.strDescriptionEN

        rv_listPerson.setHasFixedSize(true)
        rv_listPerson.layoutManager = LinearLayoutManager(this)
        rv_listPerson.addItemDecoration(DividerItemDecoration(rv_listPerson.context, DividerItemDecoration.VERTICAL))

        val playerList = arrayListOf<Player>()
        team.strTeam?.let { DTPresenter.getPlayer(it) }
        rv_listPerson.adapter = PlayerAdapter(playerList){
            startActivity(intentFor<DetailPlayerActivity>( Constant.PLAYER to it ))
        }
    }

    override fun showSnackbar(message: String) {
        snackbar(ll_detailTeamActivity, message).show()
    }

    override fun getCtx(): Context? {
        return this
    }

}

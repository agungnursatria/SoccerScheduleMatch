package com.anb.soccerschedulematch.feature.detailplayer

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.anb.soccerschedulematch.R

import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        setSupportActionBar(toolbar)
    }

}

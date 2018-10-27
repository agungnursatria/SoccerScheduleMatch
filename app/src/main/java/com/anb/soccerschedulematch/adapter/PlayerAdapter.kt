package com.anb.soccerschedulematch.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.anb.soccerschedulematch.R
import com.anb.soccerschedulematch.model.player.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*


class PlayerAdapter(private val players: ArrayList<Player>, private val listener: (Player) -> Unit) : RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerViewHolder {
        return PlayerViewHolder(PlayerUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(p0: PlayerViewHolder, p1: Int) {
        p0.bindItems(players[p1], listener)
    }
}


class PlayerUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                val value = intArrayOf(R.attr.selectableItemBackground)
                val format = ctx.obtainStyledAttributes(value)
                val backResource = format.getResourceId(0, 0)
                backgroundResource = backResource
                isClickable = true

                format.recycle()

                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.iv_player
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    params.gravity = Gravity.CENTER_VERTICAL
                    layoutParams = params
                    leftPadding = dip(10)
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER_VERTICAL
                    textView {
                        textSize = 16f
                        textColor = Color.BLACK
                        id = R.id.tv_playerName
                    }
                    textView {
                        textSize = 12f
                        id = R.id.tv_playerPosition
                    }
                }
            }
        }
    }
}

class PlayerViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
    private val image : ImageView = view.findViewById(R.id.iv_player)
    private val playerName : TextView = view.findViewById(R.id.tv_playerName)
    private val playerPosition : TextView = view.findViewById(R.id.tv_playerPosition)

    fun bindItems(player: Player, listener: (Player) -> Unit){
        Picasso.get().load(player.strCutout).into(image)
        playerName.text = player.strPlayer
        playerPosition.text = player.strPosition
        view.setOnClickListener {
            listener(player)
        }
    }
}
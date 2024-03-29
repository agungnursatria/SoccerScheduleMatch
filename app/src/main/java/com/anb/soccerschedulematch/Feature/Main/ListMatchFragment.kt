package com.anb.soccerschedulematch.Feature.Main

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anb.soccerschedulematch.Adapter.MatchScheduleAdapter
import com.anb.soccerschedulematch.Api.RetroServer
import com.anb.soccerschedulematch.Feature.Detail.DetailActivity
import com.anb.soccerschedulematch.Helper.Constant
import com.anb.soccerschedulematch.Helper.Utils
import com.anb.soccerschedulematch.Model.Match.MatchResponse
import com.anb.soccerschedulematch.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListMatchFragment : Fragment() {
    private lateinit var rvLeague : RecyclerView
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    lateinit var idLeague : String
    var position: Int = 0

    companion object {
        fun newInstance(id: String, position: Int) : ListMatchFragment{
            val bundle = Bundle()
            bundle.putString(Constant.ID_LEAGUE, id)
            bundle.putInt(Constant.POSITION, position)

            val fragment = ListMatchFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = ListMatchFragmentUI().createView(AnkoContext.create(ctx,this))
        arguments?.getString(Constant.ID_LEAGUE)?.let { idLeague = it }
        arguments?.getInt(Constant.POSITION)?.let { position = it }
        initView(view)
        loadData()

        swipeRefreshLayout.onRefresh {
            loadData()
        }

        return view
    }

    private fun loadData() {
        swipeRefreshLayout.isRefreshing = true
        val matchCall = if (position == 0) Utils.request.getPrevMatch(idLeague) else Utils.request.getNextMatch(idLeague)
        matchCall.enqueue(object : Callback<MatchResponse>{
            override fun onFailure(call: Call<MatchResponse>?, t: Throwable?) {
                t?.message?.let { toast(it) }
            }

            override fun onResponse(call: Call<MatchResponse>?, response: Response<MatchResponse>?) {
                response?.body()?.let { setDataToRecyclerView(it) }
            }

        })
    }

    private fun setDataToRecyclerView(matchResponse: MatchResponse) {
        val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        val matchScheduleAdapter = MatchScheduleAdapter(matchResponse.matchs){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(Constant.ID_MATCH, it.idEvent)
            context?.startActivity(intent)
        }
        rvLeague.layoutManager = layoutManager
        rvLeague.adapter = matchScheduleAdapter

        swipeRefreshLayout.isRefreshing = false
    }

    private fun initView(view: View) {
        rvLeague = view.find(R.id.rv_list_league)
        swipeRefreshLayout = view.find(R.id.srl_list_league)
    }


    class ListMatchFragmentUI: AnkoComponent<ListMatchFragment>{
        override fun createView(ui: AnkoContext<ListMatchFragment>) = with(ui){
            swipeRefreshLayout {
                id = R.id.srl_list_league
                recyclerView {
                    id = R.id.rv_list_league
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }
}
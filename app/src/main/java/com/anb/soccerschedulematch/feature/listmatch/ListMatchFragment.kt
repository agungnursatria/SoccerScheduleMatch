package com.anb.soccerschedulematch.feature.listmatch

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anb.soccerschedulematch.R
import com.anb.soccerschedulematch.adapter.MatchScheduleAdapter
import com.anb.soccerschedulematch.api.ApiRepository
import com.anb.soccerschedulematch.feature.detail.DetailActivity
import com.anb.soccerschedulematch.helper.Constant
import com.anb.soccerschedulematch.model.match.MatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast

class ListMatchFragment : Fragment(), ListMatchView {
    private lateinit var rvLeague : RecyclerView
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    lateinit var LMPresenter : ListMatchPresenter<ListMatchView>

    companion object {

        fun newInstance(id: String, position: Int) : ListMatchFragment {
            val bundle = Bundle()
            bundle.putString(Constant.ID_LEAGUE, id)
            bundle.putInt(Constant.POSITION, position)

            val fragment = ListMatchFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ListMatchFragmentUI().createView(AnkoContext.create(ctx,this))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idLeague = arguments?.getString(Constant.ID_LEAGUE) ?: ""
        val position = arguments?.getInt(Constant.POSITION) ?: 0

        initView(view)
        val request = ApiRepository()
        val gson = Gson()
        LMPresenter = ListMatchPresenterImpl(view.context, idLeague, position, request, gson)
        LMPresenter.onAttach(this)
        LMPresenter.loadData()

        swipeRefreshLayout.onRefresh {
            LMPresenter.loadData()
        }
    }

    override fun setDataToRecyclerView(matchResponse: MatchResponse) {
        val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        rvLeague.layoutManager = layoutManager

        matchResponse.matchs?.let {
            val matchScheduleAdapter = MatchScheduleAdapter(it){
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(Constant.MATCH, it)
                context?.startActivity(intent)
            }
            rvLeague.adapter = matchScheduleAdapter
        }

        stopRefresh()
    }

    override fun initView(view: View) {
        rvLeague = view.find(R.id.rv_list_league)
        swipeRefreshLayout = view.find(R.id.srl_list_league)
    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun startRefresh() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun stopRefresh() {
        swipeRefreshLayout.isRefreshing = false
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

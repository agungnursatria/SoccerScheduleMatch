package com.anb.soccerschedulematch.feature.listmatch

import com.anb.soccerschedulematch.TestContextProvider
import com.anb.soccerschedulematch.api.ApiRepository
import com.anb.soccerschedulematch.api.TheSportDBApi
import com.anb.soccerschedulematch.model.match.MatchResponse
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ListMatchPresenterImplTest{
    @Mock
    private
    lateinit var view: ListMatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: ListMatchPresenter<ListMatchView>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = ListMatchPresenterImpl( "4328", 0, apiRepository, gson, TestContextProvider())
        presenter.onAttach(view)
    }

    @Test
    fun testLoadData(){
        val matchResponse = MatchResponse(arrayListOf())

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPrevMatch("4328")),
                MatchResponse::class.java
        )).thenReturn(matchResponse)

        presenter.loadData()

        Mockito.verify(view).startRefresh()
        Mockito.verify(view).setDataToRecyclerView(matchResponse)
    }
}
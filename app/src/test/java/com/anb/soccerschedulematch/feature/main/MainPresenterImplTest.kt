package com.anb.soccerschedulematch.feature.main

import com.anb.soccerschedulematch.TestContextProvider
import com.anb.soccerschedulematch.api.ApiRepository
import com.anb.soccerschedulematch.api.TheSportDBApi
import com.anb.soccerschedulematch.model.league.LeagueResponse
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainPresenterImplTest{
    @Mock
    private
    lateinit var view: MainView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MainPresenter<MainView>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenterImpl(apiRepository,gson, TestContextProvider())
        presenter.onAttach(view)
    }

    @Test
    fun testInitLeagueSpinnerData(){
        val leagueResponse = LeagueResponse(arrayListOf())

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getAllLeague()),
                LeagueResponse::class.java
        )).thenReturn(leagueResponse)

        presenter.initLeaguesSpinnerData()

        verify(view).setSpinner(leagueResponse)
    }
}
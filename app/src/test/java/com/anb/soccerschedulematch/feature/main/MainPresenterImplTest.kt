package com.anb.soccerschedulematch.feature.main

import com.anb.soccerschedulematch.api.RequestInterface
import com.anb.soccerschedulematch.model.league.LeagueResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenterImplTest{
    @Mock
    private
    lateinit var view: MainView

    private lateinit var presenter: MainPresenter<MainView>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenterImpl()
        presenter.onAttach(view)
    }

    @Test
    fun testInitLeagueSpinnerData(){
        val idLeague = "4328"
        val api : RequestInterface = mock(RequestInterface::class.java)
        val mockedCall : Call<LeagueResponse> = mock(Call::class.java) as Call<LeagueResponse>
        val mockResponse : LeagueResponse = mock(LeagueResponse::class.java)

        `when`(api.getAllLeague()).thenReturn(mockedCall)

        doAnswer {
            val callback : Callback<LeagueResponse> = it.arguments[0] as Callback<LeagueResponse>

            callback.onResponse(mockedCall, Response.success(mockResponse))
        }.`when`(mockedCall).enqueue(any())

        presenter.initLeaguesSpinnerData()

        verify(view).setSpinner(mockResponse)

    }
}
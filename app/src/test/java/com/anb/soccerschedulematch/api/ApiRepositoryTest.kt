package com.anb.soccerschedulematch.api

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class ApiRepositoryTest {

    @Mock
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetRequestService(){
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

}
package com.anb.soccerschedulematch.api

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class RetroServerTest {

    @Mock
    lateinit var retroServer : RetroServer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetRequestService(){
        retroServer.getRequestService()
        Mockito.verify(retroServer).getRequestService()
    }

}
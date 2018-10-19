package com.anb.soccerschedulematch.Api

import com.anb.soccerschedulematch.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroServer {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        return retrofit!!
    }

    fun getRequestService(): RequestInterface {
        return getClient().create(RequestInterface::class.java)
    }
}

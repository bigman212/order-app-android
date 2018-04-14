package org.application.bigman.fogstreamorderapp.data.source.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * org.application.bigman.fogstreamorderapp.network
 * Created by bigman212 on 11.03.2018.
 **/

object ApiProvider {
    val orderClient: OrderApi

    private const val BASE_URL = "http://10.0.2.2:8000" //3.1

    private var retrofitInstance: Retrofit? = null

    init {
        orderClient = getRetrofit().create(OrderApi::class.java)
    }

    private fun getRetrofit(): Retrofit {
        if (retrofitInstance == null) {
            retrofitInstance = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
        }
        return retrofitInstance!!
    }
}
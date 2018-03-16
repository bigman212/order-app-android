package org.application.bigman.fogstreamorderapp.network

import org.application.bigman.fogstreamorderapp.data.source.remote.OrderApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * org.application.bigman.fogstreamorderapp.network
 * Created by bigman212 on 11.03.2018.
 **/
object ApiProvider {
    private const val baseUrl = "http://10.0.2.2:8000"
    val orderClient: OrderApi

    init {
        orderClient = getRetrofit().create(OrderApi::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }
}
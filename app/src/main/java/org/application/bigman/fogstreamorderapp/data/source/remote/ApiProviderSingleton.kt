package org.application.bigman.fogstreamorderapp.data.source.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * org.application.bigman.fogstreamorderapp.network
 * Created by bigman212 on 11.03.2018.
 **/

object ApiProviderSingleton {
    var BASE_URL = ""
    val orderApiClient: OrderApi
        get() = getRetrofit().create(OrderApi::class.java)

    fun changeServerIp(ip: String) {
        BASE_URL = "http://$ip"
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }
}
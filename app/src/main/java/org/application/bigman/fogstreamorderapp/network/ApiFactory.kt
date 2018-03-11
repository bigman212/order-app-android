package org.application.bigman.fogstreamorderapp.network

import org.application.bigman.fogstreamorderapp.auth.AuthApi
import org.application.bigman.fogstreamorderapp.data.source.remote.OrderApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * org.application.bigman.fogstreamorderapp.network
 * Created by bigman212 on 11.03.2018.
 **/
object ApiFactory {
    private val orderClient: OrderApi
    private val authClient: AuthApi

    init {
        orderClient = getRetrofit().create(OrderApi::class.java)
        authClient = getRetrofit().create(AuthApi::class.java)
    }

    fun getOrderClient(): OrderApi {
        return orderClient
    }

    fun getAuthClient(): AuthApi {
        return authClient
    }

    private fun getRetrofit(): Retrofit {
        val BASE_URL = "-"
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }
}
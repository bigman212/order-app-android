package org.application.bigman.fogstreamorderapp.network

import org.application.bigman.fogstreamorderapp.data.source.remote.OrderService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * org.application.bigman.fogstreamorderapp.network
 * Created by bigman212 on 11.03.2018.
 **/
object ApiFactory {

    private const val BASE_URL = "-"
    fun getOrdersClient(): OrderService {
        return getRetrofit().create(OrderService::class.java)
    }

    fun getAuthClient(): AuthService {
        return getRetrofit().create(AuthService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }
}
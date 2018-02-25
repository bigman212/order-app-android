package org.application.bigman.fogstreamorderapp.data.source.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * org.application.bigman.fogstreamorderapp.data.source.remote
 * Created by bigman212 on 25.02.2018.
 **/
object OrderServiceClient {
    private const val BASE_URL = ""
    fun create(): OrderService {
        val retrofitInstance = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        return retrofitInstance.create(OrderService::class.java)
    }
}
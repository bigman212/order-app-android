package org.application.bigman.fogstreamorderapp.data.source.remote

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * org.application.bigman.fogstreamorderapp.network
 * Created by bigman212 on 11.03.2018.
 **/

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
                .addHeader("Authorization", "9944b09199c62bcf9418ad846dd0e4bbdfc6ee4b")
                .build()
        val response = chain.proceed(request)
        return response
    }
}

object ApiProvider {
    val orderClient: OrderApi

    private const val BASE_URL = "http://10.0.2.2:8000"
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
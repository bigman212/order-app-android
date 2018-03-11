package org.application.bigman.fogstreamorderapp.data.source.remote

import io.reactivex.Observable
import org.application.bigman.fogstreamorderapp.data.model.Order
import retrofit2.http.GET


/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 25.02.2018.
 **/
interface OrderApi {
    @GET("/orders")
    fun getOrders(): Observable<List<Order>>
}
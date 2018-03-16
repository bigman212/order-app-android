package org.application.bigman.fogstreamorderapp.data.source.remote

import io.reactivex.Observable
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 25.02.2018.
 **/
interface OrderApi {
    @GET("/orders/")
    fun getAllUserOrders(): Observable<List<Order>>

    @GET("/orders/{id}/")
    fun getOrderById(@Path("id") id: String): Observable<Order>

    @GET("/orders/{id}/start/")
    fun startOrder(@Path("id") id: String): Observable<JSONObject>

    @GET("/orders/{id}/finish/")
    fun finishOrder(@Path("id") id: String): Observable<JSONObject>
}
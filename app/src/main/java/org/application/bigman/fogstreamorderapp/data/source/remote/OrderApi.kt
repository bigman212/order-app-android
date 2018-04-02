package org.application.bigman.fogstreamorderapp.data.source.remote

import io.reactivex.Observable
import org.application.bigman.fogstreamorderapp.data.model.AuthResponse
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.data.model.StatusChangeResponse
import retrofit2.http.*


/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 25.02.2018.
 **/
interface OrderApi {
    @GET("/orders/")
    fun getAllUserOrders(@Header("Authorization") token: String): Observable<List<Order>>

    @GET("/orders/{id}/")
    fun getOrderById(@Header("Authorization") token: String, @Path("id") id: Int): Observable<Order>

    @GET("/orders/{id}/start/")
    fun startOrder(@Header("Authorization") token: String, @Path("id") id: Int): Observable<StatusChangeResponse>

    @GET("/orders/{id}/finish/")
    fun finishOrder(@Header("Authorization") token: String, @Path("id") id: Int): Observable<StatusChangeResponse>

    @FormUrlEncoded
    @POST("/log-in/")
    fun authorize(
            @Field("username") username: String,
            @Field("password") password: String): Observable<AuthResponse>
//
//    @POST("/log-in/")
//    fun authorize(@Body user: CurrentUser): Observable<TokenResp>
}
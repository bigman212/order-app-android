package org.application.bigman.fogstreamorderapp.data

import io.reactivex.Observable
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.data.source.remote.OrderServiceClient

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 25.02.2018.
 **/
object OrderRepository {
    private val apiService = OrderServiceClient.create()
    fun getOrders(): Observable<Order> {
        return apiService.getOrders()
    }
}
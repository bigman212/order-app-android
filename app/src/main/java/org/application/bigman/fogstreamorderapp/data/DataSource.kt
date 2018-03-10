package org.application.bigman.fogstreamorderapp.data

import io.reactivex.Observable
import org.application.bigman.fogstreamorderapp.data.model.Order

/**
 * org.application.bigman.fogstreamorderapp.data
 * Created by bigman212 on 28.02.2018.
 **/
interface DataSource {
    fun getOrders(): Observable<List<Order>>?
    fun getOrderById(id: Int): Observable<List<Order>>?
}
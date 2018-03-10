package org.application.bigman.fogstreamorderapp.data

import io.reactivex.Observable
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.orderdetail.Status

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 25.02.2018.
 **/
object OrderRepository : DataSource {
    var data: List<Order> = listOf(Order("DATE", "FROM", "TO", Status.OK.value))

    override fun getOrderById(id: Int): Observable<List<Order>>? {
        return null
    }

    override fun getOrders(): Observable<List<Order>>? {
        return null
    }
}
package org.application.bigman.fogstreamorderapp.data

import io.reactivex.Observable
import org.application.bigman.fogstreamorderapp.data.model.Address
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.data.model.Status

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 25.02.2018.
 **/
object OrderRepository : DataSource {
    var data: List<Order> = listOf(
            Order("12-12-12", Address("Dikopoltzeva", 12.3, 11.1, true),
                    Address("Ussuriyskaa", 11.3, 20.1, true),
                    Status.NOT_STARTED))

    override fun getOrderById(id: Int): Observable<List<Order>>? {
        return null
    }

    override fun getOrders(): Observable<List<Order>>? {
        return null
    }
}
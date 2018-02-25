package org.application.bigman.fogstreamorderapp.orderlist

import io.reactivex.Observable
import org.application.bigman.fogstreamorderapp.data.OrderRepository
import org.application.bigman.fogstreamorderapp.data.model.Order

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 22.02.2018.
 **/
object OrderListModel : OrderListContract.Model {
    override fun getOrders(): Observable<Order> {
        return OrderRepository.getOrders()
    }
}
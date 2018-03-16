package org.application.bigman.fogstreamorderapp.data

import io.reactivex.Observable
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.network.ApiProvider

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 25.02.2018.
 **/
object OrderRepository : DataSource {

    override fun getAllUserOrders(id: Int): Observable<List<Order>> {
        return ApiProvider.orderClient.getAllUserOrders().doOnNext {
            it.forEach {
                it.dateOfOrderCreation = this.parseDate(it.dateOfOrderCreation!!)
            }
        }
    }

    override fun getOrderById(id: String): Observable<Order> {
        return ApiProvider.orderClient.getOrderById(id)
                .doOnNext { it.dateOfOrderCreation = parseDate(it.dateOfOrderCreation!!) }
    }

    private fun parseDate(date: String) = date.removeRange(10, date.length)
}
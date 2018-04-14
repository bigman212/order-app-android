package org.application.bigman.fogstreamorderapp.data

import io.reactivex.Observable
import org.application.bigman.fogstreamorderapp.data.model.CurrentUser
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.data.source.remote.ApiProvider
import java.text.SimpleDateFormat
import java.util.*

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 25.02.2018.
 **/

object OrderRepository : DataSource {

    override fun getAllUserOrders(): Observable<List<Order>> {
        return ApiProvider.orderClient.getAllUserOrders(CurrentUser.token!!)
                .doOnNext {
            it.forEach {
                it.dateOfOrderCreation = this.parseDate(it.dateOfOrderCreation!!)
            }

        }
    }

    override fun getOrderById(id: Int): Observable<Order> {
        return ApiProvider.orderClient.getOrderById(CurrentUser.token!!, id).doOnNext {
            it.dateOfOrderCreation = parseDate(it.dateOfOrderCreation)
        }
    }

    private fun parseDate(date: String?): String {
        val newDate = date?.replace("T", " ")
        val oldDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
        val newDateFormat = SimpleDateFormat("hh:mm:ss dd.MM.yyyy", Locale.getDefault())
        return newDateFormat.format(oldDateFormat.parse(newDate))
    }
}
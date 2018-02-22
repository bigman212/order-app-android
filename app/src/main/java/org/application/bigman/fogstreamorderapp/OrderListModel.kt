package org.application.bigman.fogstreamorderapp

import java.util.*

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 22.02.2018.
 **/
object OrderListModel : OrderListContract.Model {
    override fun getOrders(): List<Order> {
        val lists = ArrayList<Order>()
        val rightNow = Calendar.getInstance()
        lists.add(Order(Date(1996, 11, 29), "QWERTY", "AZERTY", 1))
        lists.add(Order(Date(1996, 11, 30), "AZERTY", "QWERTY", 1))
        return lists
    }
}
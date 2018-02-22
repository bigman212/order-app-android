package org.application.bigman.fogstreamorderapp

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 22.02.2018.
 **/


interface OrderListContract {

    interface View : ProgressView {
        fun showOrders(orders: List<Order>)
        fun update(orders: List<Order>)
    }

    interface Presenter {
        fun loadOrders()
    }

    interface Model {
        fun getOrders(): List<Order>
    }
}
package org.application.bigman.fogstreamorderapp.orderlist

import org.application.bigman.fogstreamorderapp.MvpPresenter
import org.application.bigman.fogstreamorderapp.MvpView
import org.application.bigman.fogstreamorderapp.data.model.Order

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 22.02.2018.
 **/


interface OrderListContract {

    interface Presenter : MvpPresenter {
        fun getAllOrders()
    }

    interface View : MvpView<Presenter> {
        fun updateView(orders: List<Order>)
    }
}
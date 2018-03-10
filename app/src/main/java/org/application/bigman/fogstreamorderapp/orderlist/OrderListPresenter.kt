package org.application.bigman.fogstreamorderapp.orderlist

import android.os.Handler
import org.application.bigman.fogstreamorderapp.data.DataSource
import org.application.bigman.fogstreamorderapp.data.OrderRepository
import org.application.bigman.fogstreamorderapp.data.model.Order

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 22.02.2018.
 **/
class OrderListPresenter(private val mView: OrderListContract.View,
                         private val mDataManager: DataSource) : OrderListContract.Presenter {
    init {
        mView.setPresenter(this)
    }

    override fun getAllOrders() {
        val orders = ArrayList<Order>()
        mView.showProgress()

        Handler().postDelayed(Runnable {
            mView.updateView(OrderRepository.data)
            mView.hideProgress()
        }, 3000)

        mView.updateView(orders)
    }
}
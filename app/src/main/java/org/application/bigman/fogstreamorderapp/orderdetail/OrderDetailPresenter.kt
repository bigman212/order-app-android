package org.application.bigman.fogstreamorderapp.orderdetail

import android.os.Handler
import org.application.bigman.fogstreamorderapp.data.DataSource
import org.application.bigman.fogstreamorderapp.data.OrderRepository
import org.application.bigman.fogstreamorderapp.data.model.Order

/**
 * org.application.bigman.fogstreamorderapp.orderdetail
 * Created by bigman212 on 10.03.2018.
 **/
class OrderDetailPresenter(private val mView: OrderDetailContract.View,
                           private val mDataSource: DataSource) : OrderDetailContract.Presenter {
    init {
        mView.setPresenter(this)
    }

    override fun loadOrderById(id: Int) {
        mView.showProgress()

        Handler().postDelayed(Runnable {
            mView.updateView(findOrderById(id))
            mView.hideProgress()
        }, 3000)
    }

    private fun findOrderById(id: Int): Order? {
        var value: Order? = null
        OrderRepository.data.forEach {
            if (it.from.equals("FROM", true))
                value = it
        }
        return value
    }

    override fun updateOrder(id: Int, status: Status) {
    }
}
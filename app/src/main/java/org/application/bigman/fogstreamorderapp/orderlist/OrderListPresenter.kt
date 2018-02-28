package org.application.bigman.fogstreamorderapp.orderlist

import android.os.Handler
import org.application.bigman.fogstreamorderapp.data.DataSource
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.orderdetail.Status

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
            mView.updateView(listOf(Order("DATE", "from", "to", Status.OK.value)))
            mView.hideProgress()
        }, 3000)

//        mDataManager.getOrders().doOnNext {
//        }
        mView.updateView(orders)
    }
}
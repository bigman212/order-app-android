package org.application.bigman.fogstreamorderapp.orderdetail

import android.os.Handler
import org.application.bigman.fogstreamorderapp.data.DataSource
import org.application.bigman.fogstreamorderapp.data.OrderRepository
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.data.model.Status

/**
 * org.application.bigman.fogstreamorderapp.orderdetail
 * Created by bigman212 on 10.03.2018.
 **/

class OrderDetailPresenter(private val mView: OrderDetailContract.View,
                           private val mDataSource: DataSource) : OrderDetailContract.Presenter {

    private var currentOrder: Order? = null

    init {
        mView.setPresenter(this)
    }

    override fun loadOrderById(id: Int) {
        mView.showProgress()

        Handler().postDelayed(Runnable {
            currentOrder = findOrderById(id)
            if (currentOrder != null) {
                checkStatus(currentOrder!!.status)
                mView.updateView(currentOrder)
            } else {

            }
            mView.hideProgress()
        }, 3000)
    }

    private fun checkStatus(status: Status) { //засунуть ли вызов этой функции в activity
        when (status) {
            Status.DELIVERING -> mView.setEnabledStartButton(false)
            Status.FINISHED -> mView.setEnabledFinishButton(false)
            Status.NOT_STARTED -> {
                mView.setEnabledStartButton(true)
                mView.setEnabledFinishButton(false)
            }
        }
    }

    private fun findOrderById(id: Int): Order? {
        var value: Order? = null
        OrderRepository.data.forEach {
            if (it.date == "12-12-12")
                value = it
        }
        return value
    }


    override fun sendNewStatus(id: Int, status: Status) {
    }
}
package org.application.bigman.fogstreamorderapp.orderlist

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.application.bigman.fogstreamorderapp.data.model.Order

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 22.02.2018.
 **/
class OrderListPresenter(private var orderView: OrderListContract.View) : OrderListContract.Presenter {
    private var orderModel: OrderListContract.Model = OrderListModel

    override fun loadOrders() {
        val orders = ArrayList<Order>()
        orderView.showProgress()
        orderModel.getOrders()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    orders.add(result)
                }, { error ->
                    error.printStackTrace()
                })
        orderView.hideProgress()
        orderView.showOrders(orders)
    }
}
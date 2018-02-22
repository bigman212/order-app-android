package org.application.bigman.fogstreamorderapp

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 22.02.2018.
 **/
class OrderListPresenter(private var orderView: OrderListContract.View) : OrderListContract.Presenter {
    private var orderModel: OrderListContract.Model = OrderListModel

    override fun loadOrders() {
        orderView.showOrders(orderModel.getOrders())
    }
}
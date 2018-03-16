package org.application.bigman.fogstreamorderapp.orderdetail

import org.application.bigman.fogstreamorderapp.MvpPresenter
import org.application.bigman.fogstreamorderapp.MvpView
import org.application.bigman.fogstreamorderapp.data.model.Order



/**
 * org.application.bigman.fogstreamorderapp.orderdetail
 * Created by bigman212 on 28.02.2018.
 **/
interface OrderDetailContract {

    interface Presenter : MvpPresenter {
        fun getOrderById(id: Int)
        fun sendNewStatus(status: Int)
    }

    interface View : MvpView<Presenter> {
        fun updateView(order: Order?)
        fun setEnabledStartButton(enabledValue: Boolean)
        fun setEnabledFinishButton(enabledValue: Boolean)
    }

}
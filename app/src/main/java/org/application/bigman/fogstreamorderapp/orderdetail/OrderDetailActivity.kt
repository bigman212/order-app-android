package org.application.bigman.fogstreamorderapp.orderdetail

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_order_detail.*
import org.application.bigman.fogstreamorderapp.R
import org.application.bigman.fogstreamorderapp.data.OrderRepository
import org.application.bigman.fogstreamorderapp.data.model.Order

/**
 * org.application.bigman.fogstreamorderapp.orderdetail
 * Created by bigman212 on 10.03.2018.
 **/

inline fun <T : Any, R> whenNotNull(input: T?, callback: (T) -> R): R? {
    return input?.let(callback)
}

class OrderDetailActivity : AppCompatActivity(), OrderDetailContract.View, SwipeRefreshLayout.OnRefreshListener {
    private lateinit var mPresenter: OrderDetailContract.Presenter
    private lateinit var mCurrentOrder: Order

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_order_detail)
        swipe_order_detail.setOnRefreshListener(this)
        mPresenter = OrderDetailPresenter(this, OrderRepository)
        mPresenter.loadOrderById(0)
    }

    override fun setPresenter(presenter: OrderDetailContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun updateView(order: Order?) {
        if (order != null) {
            mCurrentOrder = order
            tv_date_detail.text = order.date
            tv_from_detail.text = order.from
            tv_to_detail.text = order.to
        }
    }

    override fun showProgress() {
        swipe_order_detail.isRefreshing = true
    }

    override fun hideProgress() {
        swipe_order_detail.isRefreshing = false
    }

    override fun onRefresh() {
        mPresenter.loadOrderById(0)
    }
}
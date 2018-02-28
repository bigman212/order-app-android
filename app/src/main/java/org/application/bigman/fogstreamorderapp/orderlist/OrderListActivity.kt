package org.application.bigman.fogstreamorderapp.orderlist


import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_order_list.*
import org.application.bigman.fogstreamorderapp.R
import org.application.bigman.fogstreamorderapp.data.OrderRepository
import org.application.bigman.fogstreamorderapp.data.model.Order


class OrderListActivity : AppCompatActivity(), OrderListContract.View, SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        mPresenter.getAllOrders()
    }

    private lateinit var mPresenter: OrderListContract.Presenter
    private lateinit var mAdapter: OrderListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        rv_orders.layoutManager = LinearLayoutManager(this)
        rv_orders.hasFixedSize()
        mAdapter = OrderListAdapter(this)
        rv_orders.adapter = mAdapter
        mPresenter = OrderListPresenter(this, OrderRepository)
        swipe_container.setOnRefreshListener(this)
    }

    override fun setPresenter(presenter: OrderListContract.Presenter) {
        mPresenter = presenter
    }

    override fun showProgress() {
        swipe_container.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        swipe_container.isRefreshing = false
    }

    override fun updateView(orders: List<Order>) {
        mAdapter.updateData(orders)
    }
}

package org.application.bigman.fogstreamorderapp.orderlist


import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order_list.*
import org.application.bigman.fogstreamorderapp.R
import org.application.bigman.fogstreamorderapp.data.OrderRepository
import org.application.bigman.fogstreamorderapp.data.model.Order


class OrderListActivity : AppCompatActivity(), OrderListContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    private lateinit var mPresenter: OrderListContract.Presenter
    private lateinit var mAdapter: OrderListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        swipe_order_list.setOnRefreshListener(this)

        rv_orders.layoutManager = LinearLayoutManager(this)
        rv_orders.hasFixedSize()

        mPresenter = OrderListPresenter(this, OrderRepository)
        mAdapter = OrderListAdapter(this)

        rv_orders.adapter = mAdapter

        mPresenter.getAllOrders()
    }

    override fun setPresenter(presenter: OrderListContract.Presenter) {
        mPresenter = presenter
    }

    override fun showProgress() {
        swipe_order_list.isRefreshing = true
    }

    override fun hideProgress() {
        swipe_order_list.isRefreshing = false
    }

    override fun onRefresh() {
        mPresenter.getAllOrders()
    }

    override fun updateView(orders: List<Order>) {
        mAdapter.updateData(orders)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

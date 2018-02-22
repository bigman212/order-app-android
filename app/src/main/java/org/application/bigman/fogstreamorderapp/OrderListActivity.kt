package org.application.bigman.fogstreamorderapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_order_list.*


class OrderListActivity : AppCompatActivity(), OrderListContract.View {

    lateinit var presenter: OrderListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)
        presenter = OrderListPresenter(this)
        rv_orders.layoutManager = LinearLayoutManager(this)
        rv_orders.hasFixedSize()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadOrders()
    }

    override fun showOrders(orders: List<Order>) {
        rv_orders.adapter = MyAdapter(orders)
    }

    override fun update(orders: List<Order>) {
        rv_orders.adapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

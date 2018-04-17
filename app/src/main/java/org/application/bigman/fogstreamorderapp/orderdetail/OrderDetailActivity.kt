package org.application.bigman.fogstreamorderapp.orderdetail

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order_detail.*
import org.application.bigman.fogstreamorderapp.R
import org.application.bigman.fogstreamorderapp.data.Constants
import org.application.bigman.fogstreamorderapp.data.OrderRepository
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.data.model.TokenHolder
import org.application.bigman.fogstreamorderapp.extension.startActivityAndClearStack
import org.application.bigman.fogstreamorderapp.loginview.AuthActivity
import org.application.bigman.fogstreamorderapp.util.SharedPreferencesHelper

/**
 * org.application.bigman.fogstreamorderapp.orderdetail
 * Created by bigman212 on 10.03.2018.
 **/

class OrderDetailActivity : AppCompatActivity(), OrderDetailContract.View, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var mPresenter: OrderDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        setSupportActionBar(toolbar_orderdetail)

        mPresenter = OrderDetailPresenter(this, OrderRepository)

        b_start.setOnClickListener {
            mPresenter.sendNewStatus(Constants.Status.PERFORMING)
        }

        b_finish.setOnClickListener {
            mPresenter.sendNewStatus(Constants.Status.DONE)
        }
    }

    override fun onStart() {
        super.onStart()
        tokenValidOrLogout()
        val orderId = intent.getIntExtra(Constants.KEY_ORDER_ID, 0)
        mPresenter.getOrderById(orderId)
    }


    private fun tokenValidOrLogout() {
        if (TokenHolder.token == null) {
            SharedPreferencesHelper.valueToNull(applicationContext, getString(R.string.key_auth_token))
            this.startActivityAndClearStack(AuthActivity::class.java)
        }
    }

    override fun updateView(order: Order?) {
        if (order != null) {
            title = getString(R.string.orderdetail_toolbar_title, order.id, order.dateOfOrderCreation).toUpperCase()
            tv_status_detail.text = order.statusToString()
            tv_from_detail.text = order.startAddress?.address
            tv_to_detail.text = order.endAddress?.address
            tv_comment_detail.text = order.comment
        }
    }

    override fun showProgress() {
        swipe_order_detail.isRefreshing = true
    }

    override fun hideProgress() {
        swipe_order_detail.isRefreshing = false
    }

    override fun setEnabledStartButton(value: Boolean) {
        b_start.isEnabled = value
    }

    override fun setEnabledFinishButton(value: Boolean) {
        b_finish.isEnabled = value
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_orderdetail_menu, menu)
        return true
    }

    override fun setPresenter(presenter: OrderDetailContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun showError(message: String) {
        tokenValidOrLogout()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.menu_map_item -> {
            val currentOrder = mPresenter.getDetailOrder()
            val intent = Intent(this, OrderMapActivity::class.java)
            intent.putExtra("startLat", currentOrder.startAddress?.latitude)
            intent.putExtra("startLng", currentOrder.startAddress?.longitude)
            intent.putExtra("endLat", currentOrder.endAddress?.latitude)
            intent.putExtra("endLng", currentOrder.endAddress?.longitude)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
            true
        }
    }

    override fun onRefresh() {
        mPresenter.getOrderById(mPresenter.getDetailOrder().id!!)
    }
}
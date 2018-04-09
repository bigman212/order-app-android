package org.application.bigman.fogstreamorderapp.orderdetail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order_detail.*
import org.application.bigman.fogstreamorderapp.R
import org.application.bigman.fogstreamorderapp.data.Constants
import org.application.bigman.fogstreamorderapp.data.OrderRepository
import org.application.bigman.fogstreamorderapp.data.model.CurrentUser
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.loginview.AuthActivity
import org.application.bigman.fogstreamorderapp.util.SharedPreferencesHelper

/**
 * org.application.bigman.fogstreamorderapp.orderdetail
 * Created by bigman212 on 10.03.2018.
 **/

inline fun <T : Any, R> whenNotNull(input: T?, callback: (T) -> R): R? { // КАК ТЕБЕ ТАКОЕ ИЛОН МАСК
    return input?.let(callback)
}

class OrderDetailActivity : AppCompatActivity(), OrderDetailContract.View {

    private lateinit var mPresenter: OrderDetailContract.Presenter

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_order_detail)
        setSupportActionBar(toolbar_orderdetail)

        tokenValidOrLogout()

        mPresenter = OrderDetailPresenter(this, OrderRepository)
        mPresenter.getOrderById(intent.getIntExtra(Constants.ORDER_ID, Constants.DEFAULT_VALUE))

        b_start.setOnClickListener {
            mPresenter.sendNewStatus(Constants.Status.PERFORMING)
        }

        b_finish.setOnClickListener {
            mPresenter.sendNewStatus(Constants.Status.DONE)
        }
    }

    private fun tokenValidOrLogout() {
        if (CurrentUser.token == null) {
            SharedPreferencesHelper.valueToNull(applicationContext, getString(R.string.key_auth_token))
            val intent = Intent(this, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            this.finish()
        }
    }

    override fun updateView(order: Order?) {
        if (order != null) {
            title = getString(R.string.orderdetail_toolbar_title, order.id)
            tv_status_detail.text = order.statusToString()
            tv_from_detail.text = order.startAddress?.address //TODO
            tv_to_detail.text = order.endAddress?.address
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
}
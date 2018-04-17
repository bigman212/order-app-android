package org.application.bigman.fogstreamorderapp.orderlist


import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order_list.*
import org.application.bigman.fogstreamorderapp.R
import org.application.bigman.fogstreamorderapp.data.OrderRepository
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.data.model.TokenHolder
import org.application.bigman.fogstreamorderapp.data.source.remote.ApiProviderSingleton
import org.application.bigman.fogstreamorderapp.extension.startActivityAndClearStack
import org.application.bigman.fogstreamorderapp.loginview.AuthActivity
import org.application.bigman.fogstreamorderapp.settingsview.SettingsActivity
import org.application.bigman.fogstreamorderapp.util.SharedPreferencesHelper


class OrderListActivity : AppCompatActivity(), OrderListContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    private lateinit var mPresenter: OrderListContract.Presenter
    private lateinit var mAdapter: OrderListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)
        title = getString(R.string.orderlist_toolbar_title)
        setSupportActionBar(toolbar)
        initRecyclerView()
        swipe_order_list.setOnRefreshListener(this)

        mPresenter = OrderListPresenter(this, OrderRepository)

    }

    override fun onResume() {
        super.onResume()
        println(ApiProviderSingleton.BASE_URL)
        mPresenter.getAllOrders()
    }

    override fun onRestart() {
        super.onRestart()

        mPresenter.getAllOrders()
    }

    private fun initRecyclerView() {
        rv_orders.layoutManager = LinearLayoutManager(this)
        rv_orders.hasFixedSize()

        val divider = DividerItemDecoration(rv_orders.context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(baseContext, R.drawable.custom_divider))
        rv_orders.addItemDecoration(divider)

        mAdapter = OrderListAdapter(this)
        rv_orders.adapter = mAdapter
    }

    private fun tokenValidOrLogout() {
        if (TokenHolder.token == null) {
            SharedPreferencesHelper.valueToNull(applicationContext, getString(R.string.key_auth_token))
            this.startActivityAndClearStack(AuthActivity::class.java)
        }
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

    override fun updateView(orders: List<Order>) {
        mAdapter.updateData(orders)
    }

    override fun showError(message: String) {
        tokenValidOrLogout()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_orderlist_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.menu_logout_item -> {
            SharedPreferencesHelper.valueToNull(applicationContext, getString(R.string.key_auth_token))
            TokenHolder.token = null
            tokenValidOrLogout()
            true
        }

        R.id.menu_settings_item -> {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onRefresh() {
        tokenValidOrLogout()
        mPresenter.getAllOrders()
    }

}

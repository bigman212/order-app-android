package org.application.bigman.fogstreamorderapp.orderdetail

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order_detail.*
import org.application.bigman.fogstreamorderapp.R
import org.application.bigman.fogstreamorderapp.data.Constants
import org.application.bigman.fogstreamorderapp.data.OrderRepository
import org.application.bigman.fogstreamorderapp.data.model.Order

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

        mPresenter = OrderDetailPresenter(this, OrderRepository)
        mPresenter.getOrderById(intent.getIntExtra(Constants.ORDER_ID, Constants.DEFAULT_VALUE))

        b_start.setOnClickListener {
            mPresenter.sendNewStatus(Constants.Status.PERFORMING)
        }

        b_finish.setOnClickListener {
            mPresenter.sendNewStatus(Constants.Status.DONE)
        }
    }

    override fun setPresenter(presenter: OrderDetailContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun updateView(order: Order?) {
        if (order != null) {
            tv_date_detail.text = order.dateOfOrderCreation
            tv_from_detail.text = order.startAddress!!.address //TODO
            tv_to_detail.text = order.endAddress!!.address
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

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
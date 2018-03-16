package org.application.bigman.fogstreamorderapp.orderdetail

import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.application.bigman.fogstreamorderapp.data.DataSource
import org.application.bigman.fogstreamorderapp.data.model.Order

/**
 * org.application.bigman.fogstreamorderapp.orderdetail
 * Created by bigman212 on 10.03.2018.
 **/

class OrderDetailPresenter(private val mView: OrderDetailContract.View,
                           private val mDataSource: DataSource) : OrderDetailContract.Presenter {

    private var currentOrder: Order? = null

    init {
        mView.setPresenter(this)
    }

    override fun getOrderById(id: Int) {
        mDataSource.getOrderById(id.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Order> {
                    override fun onComplete() {
                        mView.hideProgress()
                    }

                    override fun onSubscribe(d: Disposable) {
                        mView.showProgress()
                    }

                    override fun onNext(t: Order) {
                        mView.updateView(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("TAG", e.message)
                    }
                })
    }

    override fun sendNewStatus(status: Int) {
    }
}
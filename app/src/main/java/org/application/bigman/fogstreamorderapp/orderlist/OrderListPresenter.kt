package org.application.bigman.fogstreamorderapp.orderlist

import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.application.bigman.fogstreamorderapp.data.DataSource
import org.application.bigman.fogstreamorderapp.data.model.Order

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 22.02.2018.
 **/

class OrderListPresenter(private val mView: OrderListContract.View,
                         private val mDataManager: DataSource) : OrderListContract.Presenter {
    init {
        mView.setPresenter(this)
    }

    override fun getAllOrders() {   //Возвращать ли
        mDataManager.getAllUserOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Order>> {
                    override fun onComplete() {
                        mView.hideProgress()
                    }

                    override fun onSubscribe(d: Disposable) {
                        mView.showProgress()
                    }

                    override fun onNext(t: List<Order>) {
                        mView.updateView(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("TAG", e.message)
                        mView.hideProgress()
                        mView.showError(e.message!!) // TODO
                    }
                })
    }
}
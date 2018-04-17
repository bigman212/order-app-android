package org.application.bigman.fogstreamorderapp.orderlist

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.application.bigman.fogstreamorderapp.data.DataSource
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.data.model.TokenHolder
import retrofit2.HttpException

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 22.02.2018.
 **/

class OrderListPresenter(private val mView: OrderListContract.View,
                         private val mDataManager: DataSource) : OrderListContract.Presenter {
    init {
        mView.setPresenter(this)
    }

    override fun getAllOrders() {
        try {
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
                            try {
                                val errorCode = (e as HttpException).code()
                                if (errorCode == 401) {
                                    TokenHolder.token = null
                                    mView.showError("Вы не авторизованы")
                                } else if (errorCode == 400) {
                                    mView.showError("Внутренняя ошибка сервера")
                                } else {
                                    mView.showError(e.message())
                                }
                            } catch (e1: ClassCastException) {
                                mView.showError(e.message.toString())
                            }
                        }
                    })
        } catch (e: IllegalArgumentException) {
            mView.showError(e.localizedMessage.toString())
        }
    }
}
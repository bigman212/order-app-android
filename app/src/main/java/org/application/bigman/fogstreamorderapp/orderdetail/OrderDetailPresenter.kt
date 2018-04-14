package org.application.bigman.fogstreamorderapp.orderdetail

import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.application.bigman.fogstreamorderapp.data.Constants
import org.application.bigman.fogstreamorderapp.data.DataSource
import org.application.bigman.fogstreamorderapp.data.model.CurrentUser
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.data.model.StatusChangeResponse
import org.application.bigman.fogstreamorderapp.data.source.remote.ApiProvider
import retrofit2.HttpException

/**
 * org.application.bigman.fogstreamorderapp.orderdetail
 * Created by bigman212 on 10.03.2018.
 **/

class OrderDetailPresenter(private val mView: OrderDetailContract.View,
                           private val mDataSource: DataSource) : OrderDetailContract.Presenter {

    var currentOrder: Order = Order()

    init {
        mView.setPresenter(this)
        currentOrder.status = Constants.Status.DONE
    }

    override fun getOrderById(id: Int) {
        mDataSource.getOrderById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Order> {
                    override fun onComplete() {
                        mView.hideProgress()
                        initButtons()
                    }

                    override fun onSubscribe(d: Disposable) {
                        mView.showProgress()
                    }

                    override fun onNext(t: Order) {
                        currentOrder = t
                        mView.updateView(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("TAG", e.message)
                        val errorCode = (e as HttpException).code()
                        if (errorCode == 401) {
                            CurrentUser.token = null
                            mView.showError("Вы не авторизованы")
                        } else if (errorCode == 400) {
                            mView.showError("Внутренняя ошибка сервера")
                        }
                    }
                })
    }

    private fun initButtons() {
        when (currentOrder.status) {
            Constants.Status.AVAILABLE -> {
                mView.setEnabledStartButton(enabledValue = true)
                mView.setEnabledFinishButton(enabledValue = false)
            }
            Constants.Status.PERFORMING -> {
                mView.setEnabledStartButton(enabledValue = false)
                mView.setEnabledFinishButton(enabledValue = true)
            }
            Constants.Status.DONE -> {
                mView.setEnabledFinishButton(enabledValue = false)
                mView.setEnabledStartButton(enabledValue = false)
            }
        }
    }

    override fun sendNewStatus(status: Int) {
        if (CurrentUser.token != null) {
            with(ApiProvider.orderClient) {
                when (status) {
                    Constants.Status.PERFORMING -> {
                        startOrder(CurrentUser.token!!, currentOrder.id!!)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(StatusChangeCallback(mView))
                        //обработка ошибки при нажатии, отмене и т.д.
                    }

                    Constants.Status.DONE -> {
                        finishOrder(CurrentUser.token!!, currentOrder.id!!)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(StatusChangeCallback(mView))
                    }
                    else -> {
                        mView.showError(Constants.UNKNOWN_ERROR)
                        return
                    }
                }
            }
        }
        currentOrder.status = status
        initButtons()
    }

    override fun getDetailOrder(): Order {
        return currentOrder
    }
}


private class StatusChangeCallback(private val presenterView: OrderDetailContract.View) : Observer<StatusChangeResponse> {
    override fun onComplete() {
        presenterView.hideProgress()
    }

    override fun onSubscribe(d: Disposable) {
        presenterView.showProgress()
    }

    override fun onNext(t: StatusChangeResponse) {
        presenterView.showError(t.message ?: "Нулевой ответ, попробуйте еще раз позже.")
    }

    override fun onError(e: Throwable) {
        val errorCode = (e as HttpException).code()
        if (errorCode == 401) {
            CurrentUser.token = null
            presenterView.showError("Вы не авторизованы")
        } else if (errorCode == 400) {
            presenterView.showError("Внутренняя ошибка сервера")
        }
    }
}



package org.application.bigman.fogstreamorderapp


/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 28.02.2018.
 **/
interface MvpView<P : MvpPresenter> {
    fun setPresenter(presenter: P)
    fun showProgress()
    fun hideProgress()
    fun showError(message: String)
}
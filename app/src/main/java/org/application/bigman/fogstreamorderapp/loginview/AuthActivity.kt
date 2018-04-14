package org.application.bigman.fogstreamorderapp.loginview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_auth.*
import org.application.bigman.fogstreamorderapp.R
import org.application.bigman.fogstreamorderapp.data.model.AuthResponse
import org.application.bigman.fogstreamorderapp.data.model.CurrentUser
import org.application.bigman.fogstreamorderapp.data.source.remote.ApiProvider
import org.application.bigman.fogstreamorderapp.orderlist.OrderListActivity
import org.application.bigman.fogstreamorderapp.util.SharedPreferencesHelper

/**
 * org.application.bigman.fogstreamorderapp.auth
 * Created by bigman212 on 10.03.2018.
 **/

// TODO SAVE TOKEN IN SHARED PREFERENCES

class AuthActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_auth)
        val token: String? = SharedPreferencesHelper.getStringValue(applicationContext, getString(R.string.key_auth_token))
        if (token != null) {
            CurrentUser.token = getString(R.string.auth_token, token)
            println(CurrentUser.token)
            startActivity(Intent(baseContext, OrderListActivity::class.java))
            finish()
        }
        b_login.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        SharedPreferencesHelper.valueToNull(applicationContext, getString(R.string.key_auth_token))
    }

    private fun fieldsNotEmpty(loginField: EditText, passwordField: EditText): Boolean {
        return !TextUtils.isEmpty(loginField.text.toString())
                && !TextUtils.isEmpty(passwordField.text.toString())
    }

    override fun onClick(button: View?) {
//        if (fieldsNotEmpty(et_login, et_password)) {
//            CurrentUser.password = et_password.text.toString()
//            CurrentUser.username = et_login.text.toString()
        CurrentUser.username = "driver_1"
        CurrentUser.password = "QWERTY676"

        ApiProvider.orderClient.authorize(CurrentUser.username, CurrentUser.password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<AuthResponse> {
                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onNext(t: AuthResponse) {
                            if (t.token != null) {
                                SharedPreferencesHelper.saveValue(applicationContext, getString(R.string.key_auth_token), t.token!!)
                                CurrentUser.token = getString(R.string.auth_token, t.token!!)
                                startActivity(Intent(baseContext, OrderListActivity::class.java))
                                finish()
                            }
                        }

                        override fun onError(e: Throwable) {
                            Toast.makeText(baseContext, getString(R.string.auth_error), Toast.LENGTH_LONG).show()
                        }

                        override fun onComplete() {
                        }
                    })
        }
//    }

}
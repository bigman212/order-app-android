package org.application.bigman.fogstreamorderapp.loginview

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_auth.*
import org.application.bigman.fogstreamorderapp.R
import org.application.bigman.fogstreamorderapp.data.Constants
import org.application.bigman.fogstreamorderapp.data.model.AuthResponse
import org.application.bigman.fogstreamorderapp.data.model.TokenHolder
import org.application.bigman.fogstreamorderapp.data.source.remote.ApiProviderSingleton
import org.application.bigman.fogstreamorderapp.orderlist.OrderListActivity
import org.application.bigman.fogstreamorderapp.settingsview.SettingsActivity
import org.application.bigman.fogstreamorderapp.util.SharedPreferencesHelper
import retrofit2.HttpException

/**
 * org.application.bigman.fogstreamorderapp.auth
 * Created by bigman212 on 10.03.2018.
 **/

class AuthActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setSupportActionBar(auth_toolbar)
        title = "Приложение заказов"

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        ApiProviderSingleton.changeServerIp(sharedPref.getString(getString(R.string.sp_key_server_ip), ""))

        val token: String? = SharedPreferencesHelper.getStringValue(applicationContext, getString(R.string.key_auth_token))
        if (token != null) {
            TokenHolder.token = getString(R.string.auth_token, token)
            startActivity(Intent(this, OrderListActivity::class.java))
            finish()
        }

        b_login.setOnClickListener(this)
    }

    override fun onRestart() {
        super.onRestart()
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        ApiProviderSingleton.changeServerIp(sharedPref.getString(getString(R.string.sp_key_server_ip), ""))
    }

    override fun onClick(button: View?) {
        if (fieldsNotEmpty(et_login, et_password)) {
            try {
                ApiProviderSingleton.orderApiClient.authorize(et_login.text.toString(), et_password.text.toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<AuthResponse> {
                            override fun onSubscribe(d: Disposable) {}

                            override fun onNext(t: AuthResponse) {
                                if (t.token != null) {
                                    SharedPreferencesHelper.saveStringValue(applicationContext, getString(R.string.key_auth_token), t.token)
                                    TokenHolder.token = getString(R.string.auth_token, t.token!!)
                                    startActivity(Intent(baseContext, OrderListActivity::class.java))
                                    finish()
                                }
                            }

                            override fun onError(e: Throwable) {
                                try {
                                    val errorCode = (e as HttpException).code()
                                    if (errorCode == 400) {
                                        showError(Constants.INVALID_AUTH)
                                    } else {
                                        showError(Constants.UNKNOWN_ERROR)
                                    }
                                } catch (e1: ClassCastException) {
                                    showError(Constants.UNKNOWN_ERROR)
                                }
                            }

                            override fun onComplete() {}
                        })
            } catch (e: IllegalArgumentException) {
                showError(e.localizedMessage.toString())
            }
        }
    }

    private fun showError(e: String) {
        Toast.makeText(this, e, Toast.LENGTH_LONG).show()
    }

    private fun fieldsNotEmpty(loginField: EditText, passwordField: EditText): Boolean {
        return !TextUtils.isEmpty(loginField.text.toString())
                && !TextUtils.isEmpty(passwordField.text.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_auth_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.menu_settings_item -> {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}
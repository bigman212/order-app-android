package org.application.bigman.fogstreamorderapp.loginview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import org.application.bigman.fogstreamorderapp.R
import org.application.bigman.fogstreamorderapp.data.model.CurrentUser
import org.application.bigman.fogstreamorderapp.data.source.remote.TokenResp
import org.application.bigman.fogstreamorderapp.network.ApiProvider
import org.application.bigman.fogstreamorderapp.orderlist.OrderListActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * org.application.bigman.fogstreamorderapp.auth
 * Created by bigman212 on 10.03.2018.
 **/
class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        b_login.setOnClickListener {
            val login = et_login.text.toString()
            val password = et_password.text.toString()

            ApiProvider.orderClient.authorize(login, password)
                    .enqueue(object : Callback<TokenResp?> {
                        override fun onFailure(call: Call<TokenResp?>?, t: Throwable?) {
                            println(call.toString())
                        }

                        override fun onResponse(call: Call<TokenResp?>?, response: Response<TokenResp?>?) {
                            if (response != null) {
                                println(response.raw().toString())
                            }
                            if (response != null) {
                                if (response.body() != null) {
                                    println(response.body()!!.token)
                                    CurrentUser.username = login
                                    CurrentUser.password = password
                                    CurrentUser.token = "Token "
                                    CurrentUser.token = CurrentUser.token + response.body()!!.token!!
                                    val intent = Intent(baseContext, OrderListActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                        }
                    })
        }
    }

}
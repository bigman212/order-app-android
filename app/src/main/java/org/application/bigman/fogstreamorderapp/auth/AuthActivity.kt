package org.application.bigman.fogstreamorderapp.auth

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import org.application.bigman.fogstreamorderapp.R

/**
 * org.application.bigman.fogstreamorderapp.auth
 * Created by bigman212 on 10.03.2018.
 **/
class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        b_login.setOnClickListener {
            //            ApiFactory.getAuthClient().authorize(Credentials.basic("login", "password"))
        }
    }

}
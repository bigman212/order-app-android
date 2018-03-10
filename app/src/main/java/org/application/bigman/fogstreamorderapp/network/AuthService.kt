package org.application.bigman.fogstreamorderapp.network

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET

/**
 * org.application.bigman.fogstreamorderapp.network
 * Created by bigman212 on 10.03.2018.
 **/
interface AuthService {
    @GET
    fun authorize(@Body credentials: String): Observable<String>
}
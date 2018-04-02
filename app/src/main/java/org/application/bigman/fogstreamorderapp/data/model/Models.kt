package org.application.bigman.fogstreamorderapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.application.bigman.fogstreamorderapp.data.Constants


/**
 * org.application.bigman.fogstreamorderapp.data.model
 * Created by bigman212 on 11.03.2018.
 **/

class Address {
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null
    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null
}

class Order : Comparable<Order> {
    override fun compareTo(other: Order): Int {
        return when {
            other.status == Constants.Status.AVAILABLE -> 0
            other.status == Constants.Status.AVAILABLE -> 1
            other.status == Constants.Status.DONE -> -1
            else -> {
                0
            }
        }
    }
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("status")
    @Expose
    var status: Int? = null
    @SerializedName("start_address")
    @Expose
    var startAddress: Address? = null
    @SerializedName("end_address")
    @Expose
    var endAddress: Address? = null
    @SerializedName("date_of_order_creation")
    @Expose
    var dateOfOrderCreation: String? = null
}


class AuthResponse {
    @SerializedName("token")
    @Expose
    var token: String? = null
}

class StatusChangeResponse {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}

object CurrentUser {
    lateinit var username: String
    lateinit var password: String
    var token: String? = null
}
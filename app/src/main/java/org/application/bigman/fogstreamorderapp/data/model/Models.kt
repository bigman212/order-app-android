package org.application.bigman.fogstreamorderapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.application.bigman.fogstreamorderapp.data.Constants


/**
 * org.application.bigman.fogstreamorderapp.data.model
 * Created by bigman212 on 11.03.2018.
 **/

class Order : Comparable<Order> {
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
    @SerializedName("comment")
    @Expose
    var comment: String? = null

    fun statusToString(): String {
        return when (status) {
            Constants.Status.AVAILABLE -> "Доступна"
            Constants.Status.PERFORMING -> "Выполняется"
            Constants.Status.DONE -> "Выполнена"
            else -> "Статус неизвестен"
        }
    }

    override fun compareTo(other: Order): Int {
        return when {
            this.status == Constants.Status.AVAILABLE -> 1
            this.status == Constants.Status.PERFORMING -> -1
            else -> 0
        }
    }
}


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

object TokenHolder {
    var token: String? = null
}
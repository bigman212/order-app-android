package org.application.bigman.fogstreamorderapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


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

class Order {
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
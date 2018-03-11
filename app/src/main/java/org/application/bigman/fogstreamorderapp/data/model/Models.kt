package org.application.bigman.fogstreamorderapp.data.model

/**
 * org.application.bigman.fogstreamorderapp.data.model
 * Created by bigman212 on 11.03.2018.
 **/
data class Address(var name: String,
                   var latitude: Double,
                   var longitude: Double,
                   var actual: Boolean)

data class Order(var date: String,
                 var from: Address,
                 var to: Address,
                 var status: Status)

enum class Status(val value: Int) {
    NOT_STARTED(0), DELIVERING(1), FINISHED(2)
}

data class User(var id: Int)




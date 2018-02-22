package org.application.bigman.fogstreamorderapp

import java.util.*

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 20.02.2018.
 **/
data class Order(val date: Date, val from: String, val to: String, var status: Int)
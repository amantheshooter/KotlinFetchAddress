package com.shooter.kotlinfetchaddress.model

import com.squareup.moshi.Json

class AddressList {

    @Json(name = "id")
    var id: String? = null
    @Json(name = "city")
    var city: String? = null
    @Json(name = "addressString")
    var addressString: String? = null
    @Json(name = "latitude")
    var latitude: Double = 0.toDouble()
    @Json(name = "longitude")
    var longitude: Double = 0.toDouble()
    @Json(name = "label")
    var label: String? = null
    @Json(name = "pinCode")
    var pinCode: String? = null

}
package com.shooter.kotlinfetchaddress.model

import com.squareup.moshi.Json

class Data {

    @Json(name = "autoCompleteRequestString")
    var autoCompleteRequestString: String? = null
    @Json(name = "addressList")
    var addressList: List<AddressList>? = null

}
package com.shooter.kotlinfetchaddress.network

import com.shooter.kotlinfetchaddress.model.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AddressApi {
    @GET("/compassLocation/rest/address/autocomplete")
    fun getAddress(
        @Query("queryString") address: String,
        @Query("city") city: String
    ): Deferred<Response<ApiResponse>>

}
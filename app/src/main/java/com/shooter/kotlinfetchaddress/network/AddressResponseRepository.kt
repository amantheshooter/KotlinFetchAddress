package com.shooter.kotlinfetchaddress.network

import com.shooter.kotlinfetchaddress.model.ApiResponse

internal class AddressResponseRepository(private val api: AddressApi) : BaseRepository() {

    private var addressResponse: ApiResponse? = null

    suspend fun getAddressData(address: String, city: String): ApiResponse? {

        addressResponse = safeApiCall(
            call = { api.getAddress(address, city).await() },
            errorMessage = "Error Fetching Address"
        )

        return addressResponse
    }
}
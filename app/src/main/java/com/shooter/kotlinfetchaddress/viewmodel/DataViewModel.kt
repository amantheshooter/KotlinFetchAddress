package com.shooter.kotlinfetchaddress.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shooter.kotlinfetchaddress.model.AddressList
import com.shooter.kotlinfetchaddress.network.AddressResponseRepository
import com.shooter.kotlinfetchaddress.network.ApiFactory
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DataViewModel : ViewModel(){

    private val parentJob = Job()
    //  read get()
    private val coroutineContext: CoroutineContext = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : AddressResponseRepository =
        AddressResponseRepository(ApiFactory.ADDRESS_API)

    val addressLiveData = MutableLiveData<List<AddressList>>()

    fun fetchAddress(address: String, city : String){
        scope.launch {
            val addressList = repository.getAddressData(address, city)
            addressLiveData.postValue(addressList!!.data.addressList)

        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()

}

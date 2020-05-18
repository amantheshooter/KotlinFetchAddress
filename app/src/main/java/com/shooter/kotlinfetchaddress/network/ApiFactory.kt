package com.shooter.kotlinfetchaddress.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory{
  
    //Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor {chain->
            val newUrl = chain.request().url
                    .newBuilder()
                    .build()

            val newRequest = chain.request()
                    .newBuilder()
                    .url(newUrl)
                    .build()

            chain.proceed(newRequest)
        }

    //Creating logging interceptor to log request and response.
    private fun loggingInterceptor(): HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor
    }

   //OkHttpClient for building http request url.
    private val toToClient = OkHttpClient().newBuilder()
                                .addInterceptor(authInterceptor)
                                .addInterceptor(loggingInterceptor())
                                .build()

    private fun retrofit() : Retrofit = Retrofit.Builder()
                .client(toToClient)
                .baseUrl("https://digi-api.airtel.in/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()   

  
   val ADDRESS_API : AddressApi = retrofit()
       .create(AddressApi::class.java)

}
package com.shooter.kotlinfetchaddress.network

import android.util.Log
import retrofit2.Response
import com.shooter.kotlinfetchaddress.Result
import java.io.IOException
import java.lang.Exception

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {
        val result: Result<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }
        return data
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful)
                return Result.Success(response.body()!!)
            return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
        } catch (e: Exception) {
            return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
        }
    }
}
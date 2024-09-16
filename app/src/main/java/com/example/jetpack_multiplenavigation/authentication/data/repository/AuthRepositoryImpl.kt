package com.example.jetpack_multiplenavigation.authentication.data.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.jetpack_multiplenavigation.authentication.data.local.AuthPreferences
import com.example.jetpack_multiplenavigation.authentication.data.model.UserDto
import com.example.jetpack_multiplenavigation.authentication.data.remote.AuthApi
import com.example.jetpack_multiplenavigation.authentication.data.remote.request.AuthRequest
import com.example.jetpack_multiplenavigation.authentication.data.remote.response.AuthResponse
import com.example.jetpack_multiplenavigation.authentication.domain.repository.AuthRepository
import com.example.jetpack_multiplenavigation.authentication.utils.AuthResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class AuthRepositoryImpl(
    private val context: Context,
    private val authApi: AuthApi,
    private val preferences: AuthPreferences
) : AuthRepository {

    override suspend fun loginUser(body: AuthRequest): AuthResult<AuthResponse> {
        var bodyResponse: AuthResponse? = null
        val call = authApi.loginUser(body)
        call.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (!response.isSuccessful) {
                    AuthResult.Error(null, message = "Response failed: ${response.message()}")
                    Toast.makeText(context, "Response failed: ${response.message()}", Toast.LENGTH_LONG).show()
                }

                bodyResponse = response.body()!!
                if (bodyResponse != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        preferences.saveAuthToken(bodyResponse!!.accessToken)
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                AuthResult.Error(null, message = "Request failed: ${t.message}")
                Toast.makeText(context, "Request failed: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })

        return AuthResult.Success(bodyResponse!!)
    }

    override suspend fun registerUser(body: AuthRequest): AuthResult<AuthResponse> {
        var bodyResponse: AuthResponse? = null
        val call = authApi.registerUser(body)
        call.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (!response.isSuccessful) {
                    Toast.makeText(context, "Response failed: ${response.message()}", Toast.LENGTH_LONG).show()
                    AuthResult.Error(null, message = "Response failed: ${response.message()}")
                    Log.e("LLL", "Error")
                }

                bodyResponse = response.body()
                Log.e("LLL", "${bodyResponse!!.accessToken}")
                if (bodyResponse != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        preferences.saveAuthToken(bodyResponse!!.accessToken)
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.e("LLL", "${t.message}")
                AuthResult.Error(null, message = "Request failed: ${t.message}")
                Toast.makeText(context, "Request failed: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })

        return AuthResult.Success(bodyResponse!!)
    }

    override suspend fun getUser(): Flow<AuthResult<UserDto>> =  flow {
        val user = try {
            authApi.getUser()
        } catch (e: IOException) {
            e.printStackTrace()
            emit(AuthResult.Error(null, message = "IO Exception - ${e.message}"))
            return@flow
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(AuthResult.Error(null, message = "Http Exception - ${e.message}"))
            return@flow
        } catch (e: Exception) {
            e.printStackTrace()
            emit(AuthResult.Error(null, message = "Exception - ${e.message}"))
            return@flow
        }

        emit(AuthResult.Success(user))
    }
}
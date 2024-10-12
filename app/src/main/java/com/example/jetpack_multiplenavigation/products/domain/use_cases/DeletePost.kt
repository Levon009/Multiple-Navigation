package com.example.jetpack_multiplenavigation.products.domain.use_cases

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.example.jetpack_multiplenavigation.products.data.retrofit.data.ProductsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeletePost(
    private val api: ProductsApi
) {
    operator fun invoke(
        context: Context,
        id: Int,
        responseCode: MutableState<String>
    ) {
        val call = api.deleteProduct(id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (!response.isSuccessful) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    return
                }

                responseCode.value = "Response code: ${response.code()}"
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                return
            }
        })
    }
}
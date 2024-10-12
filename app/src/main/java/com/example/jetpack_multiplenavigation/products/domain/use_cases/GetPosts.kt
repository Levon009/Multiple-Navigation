package com.example.jetpack_multiplenavigation.products.domain.use_cases

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.example.jetpack_multiplenavigation.products.data.retrofit.data.ProductsApi
import com.example.jetpack_multiplenavigation.products.domain.data.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPosts(
    private val api: ProductsApi
) {
    operator fun invoke(
        context: Context,
        posts: MutableState<List<Post>>
    ) : MutableState<List<Post>>{
        val params: MutableMap<String?, String?> = HashMap()
        params["id"] = "1"
        params["_sort"] = "title"
        params["_order"] = "Desc"
        val call = api.getProducts(params)
        call.enqueue(object : Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    return
                }

                posts.value = response.body()!!
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        return posts
    }
}
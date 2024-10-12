package com.example.jetpack_multiplenavigation.products.domain.use_cases

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import com.example.jetpack_multiplenavigation.products.data.retrofit.data.ProductsApi
import com.example.jetpack_multiplenavigation.products.domain.data.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePost(
    private val api: ProductsApi
) {
    operator fun invoke(
        context: Context,
        title: MutableState<TextFieldValue>,
        text: MutableState<TextFieldValue>,
        result: MutableState<String>
    ) {
        val post = Post(25, 100, title.value.text, text.value.text)
        val call = api.createProduct(post)
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    return
                }

                val responseBody: Post? = response.body()
                val content = StringBuilder("")
                content.append("Server response code: ${response.code()}\n")
                content.append("Id: ${responseBody?.id}\n")
                content.append("Title: ${responseBody?.title}\n")
                content.append("Text: ${responseBody?.text}\n\n")
                result.value = content.toString()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                return
            }
        })
    }
}
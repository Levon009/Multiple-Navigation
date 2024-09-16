package com.example.jetpack_multiplenavigation.products.retrofit.pagination2.usersPagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpack_multiplenavigation.products.retrofit.pagination2.data.model.User
import com.example.jetpack_multiplenavigation.products.retrofit.pagination2.retrofit2.RetrofitClient2
import retrofit2.HttpException
import java.io.IOException

class UsersPagination : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val nexPage = params.key ?: 1
            val userList = RetrofitClient2.api.getUsersList(page = nexPage)
            LoadResult.Page(
                data = userList.data,
                prevKey = if (nexPage == 1) null else nexPage - 1,
                nextKey = if (userList.data.isEmpty()) null else userList.page + 1
            )
        } catch (e: IOException) {
            e.printStackTrace()
            LoadResult.Error(e)
        } catch (e: HttpException) {
            e.printStackTrace()
            LoadResult.Error(e)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}
package com.example.jetpack_multiplenavigation.products.retrofit.data

import com.example.jetpack_multiplenavigation.products.Helper
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var testProductsApi: TestProductsApi

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        testProductsApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(TestProductsApi::class.java)
    }

    @Test
    fun testGetProducts()  = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = testProductsApi.getProductsTest()
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.isEmpty())
    }

    @Test
    fun testGetProducts_returnProducts()  = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")
        mockResponse.setBody(content)
        mockResponse.setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        val response = testProductsApi.getProductsTest()
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.isEmpty())
        Assert.assertEquals(2, response.size)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
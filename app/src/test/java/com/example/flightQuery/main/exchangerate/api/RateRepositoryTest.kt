package com.example.flightQuery.main.exchangerate.api

import com.example.flightQuery.main.data.exchange.RateApiService
import com.example.flightQuery.main.data.exchange.RateRepository
import com.example.flightQuery.main.data.exchange.RateRetrofitInstance
import com.example.flightQuery.main.domain.exchange.model.RateInfoItem
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response


class RateRepositoryTest {
    private val repository = RateRepository()
    private val fakeClient = mockk<RateRetrofitInstance>()
    private val fakeApi = mockk<RateApiService>()
    private val code = "404"
    private val fakeResponse = Response.error<RateInfoItem>(404, ResponseBody.create(null, ""))

    init {
        // Configure the fake client to use the fake API
        every { fakeClient.api } returns fakeApi
    }

    @Test
    fun test() {
        coEvery { fakeApi.getRate("fca_live_TQcP4fiHbbuay9cZNFEFJ9v7mO7QYPyqTdkIZiRX") } returns fakeResponse
        runTest {
            val result = repository.getFromApiResult(fakeClient)
            assertEquals(result.code().toString(), code)
        }
    }
}
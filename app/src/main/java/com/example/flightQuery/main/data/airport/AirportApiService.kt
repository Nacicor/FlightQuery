package com.example.flightQuery.main.data.airport

import com.example.flightQuery.main.domain.airport.model.AirportInfoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AirportApiService {
    @GET("api/AirPortFlyAPI/{flyType}/{airPortID}")
    suspend fun getFlightAllInfo(
        @Path("flyType") flyType: String,
        @Path("airPortID") airPortID: String
    ): Response<List<AirportInfoItem>>
}
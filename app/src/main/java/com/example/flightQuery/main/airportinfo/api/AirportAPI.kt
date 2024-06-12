package com.example.flightQuery.main.airportinfo.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AirportAPI {
    @GET("api/AirPortFlyAPI/{flyType}/{airPortID}")
    suspend fun getFlightAllInfo(
        @Path("flyType") flyType: String,
        @Path("airPortID") airPortID: String
    ): Response<List<AirportInfoItem>>
}
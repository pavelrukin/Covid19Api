package com.example.covid19.api

import com.example.covid19.model.CountryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidApi {

    @GET("summary")
    suspend fun getCountryList(

    ): Response<CountryResponse>

@GET("summary")
    suspend fun searchForCountry(
    @Query("q")
    searchQuery: String
    ): Response<CountryResponse>

}
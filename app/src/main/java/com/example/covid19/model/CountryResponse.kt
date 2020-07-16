package com.example.covid19.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountryResponse(
    @SerializedName("Countries")
    val countries: List<Country>,
    @SerializedName("Date")
    val date: String, // 2020-04-05T06:37:00Z
    @SerializedName("Global")
    val global: Global
):Serializable
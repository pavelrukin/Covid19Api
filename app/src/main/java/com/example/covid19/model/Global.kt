package com.example.covid19.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Global(
    @SerializedName("NewConfirmed")
    val newConfirmed: Int, // 100282
    @SerializedName("NewDeaths")
    val newDeaths: Int, // 5658
    @SerializedName("NewRecovered")
    val newRecovered: Int, // 15405
    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int, // 1162857
    @SerializedName("TotalDeaths")
    val totalDeaths: Int, // 63263
    @SerializedName("TotalRecovered")
    val totalRecovered: Int // 230845
):Serializable
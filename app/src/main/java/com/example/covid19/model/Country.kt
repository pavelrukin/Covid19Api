package com.example.covid19.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Entity(tableName = "country")
data class Country(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @SerializedName("Country")
    val country: String, // ALA Aland Islands
    @SerializedName("CountryCode")
    val countryCode: String, // AX
    @SerializedName("Date")
    val date: String, // 2020-04-05T06:37:00Z
    @SerializedName("NewConfirmed")
    val newConfirmed: Int, // 0
    @SerializedName("NewDeaths")
    val newDeaths: Int, // 0
    @SerializedName("NewRecovered")
    val newRecovered: Int, // 0
    @SerializedName("Slug")
    val slug: String, // ala-aland-islands
    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int, // 0
    @SerializedName("TotalDeaths")
    val totalDeaths: Int, // 0
    @SerializedName("TotalRecovered")
    val totalRecovered: Int // 0
):Serializable
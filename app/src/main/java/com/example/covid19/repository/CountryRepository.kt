package com.example.covid19.repository

import android.app.DownloadManager
import com.example.covid19.api.RetrofitInstance
import com.example.covid19.db.CountryDatabase

class CountryRepository(val db: CountryDatabase) {
    suspend fun getCountryList() = RetrofitInstance.api.getCountryList()

    suspend fun searchCountry(searchQuery: String) = RetrofitInstance.api.searchForCountry(searchQuery)
}

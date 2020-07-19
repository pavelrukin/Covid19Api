package com.example.covid19.repository

import com.example.covid19.api.RetrofitInstance
import com.example.covid19.db.CountryDatabase
import com.example.covid19.model.Country

class CountryRepository(val db: CountryDatabase) {
    suspend fun getCountryList() = RetrofitInstance.api.getCountryList()

    suspend fun upsert(country: Country) = db.getCountryDao().upsert(country)

    fun getSavedCountry() = db.getCountryDao().getAllCountrys()

    suspend fun deleteCountry(country: Country) = db.getCountryDao().deleteCountry(country)

}
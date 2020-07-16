package com.example.covid19.repository

import com.example.covid19.api.RetrofitInstance
import com.example.covid19.db.CountryDatabase

class CountryRepository (val  db:CountryDatabase){
suspend fun getCountryList() = RetrofitInstance.api.getCountryList()
}

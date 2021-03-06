package com.example.covid19.ui

import android.app.DownloadManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19.model.Country
import com.example.covid19.model.CountryResponse
import com.example.covid19.repository.CountryRepository
import com.example.covid19.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CountryViewModel(
    private val countryRepository: CountryRepository
): ViewModel() {
    val countryList:MutableLiveData<Resource<CountryResponse>> = MutableLiveData()

    init {
        getCountryList()
    }

    fun getCountryList() = viewModelScope.launch {
        countryList.postValue(Resource.Loading())
        val response = countryRepository.getCountryList()
        countryList.postValue(handleCountryListResponse(response))
    }
    private fun handleCountryListResponse(response:Response<CountryResponse>) :Resource<CountryResponse>{
        if(response.isSuccessful){
            response.body()?.let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    fun saveCountry(country: Country) = viewModelScope.launch {
        countryRepository.upsert(country)
    }
    fun getSavedCountry()= countryRepository.getSavedCountry()
    fun deleteCountry(country:Country) = viewModelScope.launch { countryRepository.deleteCountry(country) }
}
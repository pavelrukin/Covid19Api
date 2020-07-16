package com.example.covid19.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19.model.CountryResponse
import com.example.covid19.repository.CountryRepository
import com.example.covid19.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CountryViewModel(
    private val countryRepository: CountryRepository
): ViewModel() {
    val countryList:MutableLiveData<Resource<CountryResponse>> = MutableLiveData()
    var countryListPage = 1
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
}
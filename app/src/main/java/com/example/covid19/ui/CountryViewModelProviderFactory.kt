package com.example.covid19.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.covid19.repository.CountryRepository

class CountryViewModelProviderFactory (val countryRepository: CountryRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountryViewModel(countryRepository )  as T
    }

}
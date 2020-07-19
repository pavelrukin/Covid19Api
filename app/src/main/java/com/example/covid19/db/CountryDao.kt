package com.example.covid19.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.covid19.model.Country

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(country: Country):Long

    @Query("SELECT * FROM country")
    fun getAllCountrys(): LiveData<List<Country>>

    @Delete
    suspend fun deleteCountry(country: Country)


}
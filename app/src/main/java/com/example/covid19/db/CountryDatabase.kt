package com.example.covid19.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.covid19.model.Country

@Database(
    entities = [Country::class],
    version = 1
)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun getCountryDao(): CountryDao

    companion object {
        @Volatile
        private var instance: CountryDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CountryDatabase::class.java,
            "country_db.db"
        ).build()
    }
}
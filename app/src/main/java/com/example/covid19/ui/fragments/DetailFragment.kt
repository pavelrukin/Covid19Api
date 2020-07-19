package com.example.covid19.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.covid19.R
import com.example.covid19.ui.CountryViewModel
import com.example.covid19.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {
    lateinit var viewModel: CountryViewModel
    val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        initView()

    }

    @SuppressLint("SetTextI18n")
    fun initView() {
        val country = args.Country
        detail_country_name.text = country.country
        detail_new_confirmed.text =
            context?.getString(R.string.new_confirmed) + ": " + country.newConfirmed.toString()
        detail_new_death.text =
            context?.getString(R.string.new_deaths) + ": " + country.newDeaths.toString()
        detail_new_recovered.text =
            context?.getString(R.string.new_recovered) + ": " + country.newRecovered.toString()
        detail_total_confirmed.text =
            context?.getString(R.string.total_confirmed) + ": " + country.totalConfirmed.toString()
        detail_total_death.text =
            context?.getString(R.string.total_death) + ": " + country.totalDeaths.toString()
        detail_total_recovered.text =
            context?.getString(R.string.total_recovered) + ": " + country.newRecovered.toString()
detail_date.text = country.date
    }
}
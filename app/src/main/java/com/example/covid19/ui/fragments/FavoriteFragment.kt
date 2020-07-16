package com.example.covid19.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.covid19.R
import com.example.covid19.ui.CountryViewModel
import com.example.covid19.ui.MainActivity


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {


    private lateinit var viewModel:CountryViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

}
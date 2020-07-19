package com.example.covid19.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19.R
import com.example.covid19.ui.CountryAdapter
import com.example.covid19.ui.CountryViewModel
import com.example.covid19.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_country_list.*
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

lateinit var countryAdapter:CountryAdapter

    private lateinit var viewModel:CountryViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()
        onItemClik()

    }


    fun onItemClik() {
        countryAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {

                putSerializable("Country", it)

            }
            findNavController().navigate(
                R.id.action_country_list_fragment_to_detailFragment,
                bundle
            )
        }
    }

    private fun setupRecyclerView() {
        countryAdapter = CountryAdapter()
        rv_favorite_country.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
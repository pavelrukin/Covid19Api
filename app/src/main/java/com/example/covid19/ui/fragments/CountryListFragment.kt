package com.example.covid19.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19.R
import com.example.covid19.ui.CountryAdapter
import com.example.covid19.ui.CountryViewModel
import com.example.covid19.ui.MainActivity
import com.example.covid19.utils.Resource
import kotlinx.android.synthetic.main.fragment_country_list.*


class CountryListFragment : Fragment(R.layout.fragment_country_list) {

    lateinit var countryAdapter: CountryAdapter
    lateinit var viewModel: CountryViewModel

    val TAG = "CountryListFragment"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        /* countryAdapter = CountryAdapter()
         recyclerView?.apply {
             adapter = countryAdapter
             layoutManager = LinearLayoutManager(activity)
         }
 */




        viewModel.countryList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        countryAdapter.differ.submitList(it.countries)
                        Toast.makeText(context, "SUCESS", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(context, "ERRR", Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    Toast.makeText(context, "LOAAA", Toast.LENGTH_SHORT).show()
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        progress_bar_list.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progress_bar_list.visibility = View.VISIBLE
    }


    private fun setupRecyclerView() {
        countryAdapter = CountryAdapter()
        rv_list_country.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
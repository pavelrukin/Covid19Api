package com.example.covid19.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19.R
import com.example.covid19.ui.CountryAdapter
import com.example.covid19.ui.CountryViewModel
import com.example.covid19.ui.MainActivity
import com.example.covid19.utils.Resource
import kotlinx.android.synthetic.main.fragment_country_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class CountryListFragment : Fragment(R.layout.fragment_country_list) {

    lateinit var countryAdapter: CountryAdapter
    lateinit var viewModel: CountryViewModel

    val TAG = "CountryListFragment"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()


//GET LIST
        viewModel.countryList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        countryAdapter.submitList(it.countries)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->

                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {

                    showProgressBar()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView: SearchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

            private var searchJob: Job? = null

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                countryAdapter.filter.filter(newText)
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
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
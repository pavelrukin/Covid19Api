package com.example.covid19.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.ui.CountryAdapter
import com.example.covid19.ui.CountryViewModel
import com.example.covid19.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    lateinit var countryAdapter: CountryAdapter

    private lateinit var viewModel: CountryViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()
        onItemClik()
        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.DOWN
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val country = countryAdapter.differ.currentList[position]
                viewModel.deleteCountry(country)
                Snackbar.make(view, "Successfully deleted country", Snackbar.LENGTH_LONG).apply {
                    setAction("undo") {
                        viewModel.saveCountry(country)
                    }
                    show()
                }
            }

        }
        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(rv_favorite_country)
        }
        viewModel.getSavedCountry().observe(viewLifecycleOwner, Observer { country ->
            countryAdapter.differ.submitList(country)
        })

    }


    fun onItemClik() {
        countryAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {

                putSerializable("Country", it)

            }
            findNavController().navigate(
                R.id.action_favorite_fragment_to_detailFragment,
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
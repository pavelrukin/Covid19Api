package com.example.covid19.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.model.Country
import kotlinx.android.synthetic.main.country_list_item.view.*

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtilCallBack = object : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.newRecovered == newItem.newRecovered &&
             oldItem.newDeaths == newItem.newDeaths &&
             oldItem.newConfirmed == newItem.newConfirmed &&
             oldItem.totalRecovered == newItem.totalRecovered &&
             oldItem.totalDeaths == newItem.totalDeaths &&
             oldItem.totalConfirmed == newItem.totalConfirmed
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffUtilCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.country_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = differ.currentList[position]
        holder.itemView.apply {

            country_name.text = country.country
            total_confirmed.text = country.totalConfirmed.toString()
            total_death.text = country.totalDeaths.toString()
            total_recovered.text = country.totalRecovered.toString()
            new_confirmed.text = country.newConfirmed.toString()
            new_death.text = country.newDeaths.toString()
            new_recovered.text = country.newRecovered.toString()
            setOnClickListener {
                onItemClickListener?.let { it(country) }
            }

        }


    }

    private var onItemClickListener: ((Country) -> Unit)? = null

    fun setOnItemClickListener(listener: (Country) -> Unit) {
        onItemClickListener = listener
    }
}
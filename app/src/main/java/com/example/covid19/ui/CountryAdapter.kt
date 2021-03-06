package com.example.covid19.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.model.Country
import kotlinx.android.synthetic.main.country_list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), Filterable {
    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private lateinit var fullList: List<Country>

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

            country_name.text =   country.country
            total_confirmed.text = context.getString(R.string.total_confirmed)+": " +country.totalConfirmed.toString()
            total_death.text = context.getString(R.string.total_death)+": " +country.totalDeaths.toString()
            total_recovered.text = context.getString(R.string.total_recovered)+": " +country.totalRecovered.toString()
            new_confirmed.text =context.getString(R.string.new_confirmed)+": " + country.newConfirmed.toString()
            new_death.text = context.getString(R.string.new_deaths)+": " +country.newDeaths.toString()
            new_recovered.text = context.getString(R.string.new_recovered)+": " +country.newRecovered.toString()
            setOnClickListener {
                onItemClickListener?.let { it(country) }
            }

        }


    }

    private var onItemClickListener: ((Country) -> Unit)? = null

    fun setOnItemClickListener(listener: (Country) -> Unit) {
        onItemClickListener = listener
    }

    fun submitList(notes: List<Country>) {
        differ.submitList(notes)
        fullList = ArrayList(notes)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<Country>()
                if (constraint.toString().isEmpty())
                    filteredList.addAll(fullList)
                else {
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
                    for (note in fullList) {
                        if (note.country.toLowerCase(Locale.ROOT).contains(filterPattern))
                            filteredList.add(note)
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                differ.submitList(results?.values as MutableList<Country>?)
            }
        }
    }

}



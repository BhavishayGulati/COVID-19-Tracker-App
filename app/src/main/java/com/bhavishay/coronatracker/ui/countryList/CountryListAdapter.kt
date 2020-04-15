package com.bhavishay.coronatracker.ui.countryList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.models.data.Country
import kotlinx.android.synthetic.main.country_item_list.view.*


import java.util.*
import kotlin.collections.HashMap


var countries = HashMap<String, Country>()
class CountryListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_list_fragment,
            parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var code = countries.keys.elementAt(position - 1) as String
        val country = countries[code]
        holder.itemView.countryConfirmedValue.text = country?.activeCases.toString()
        holder.itemView.countryDeadValueTextView.text = country?.deaths.toString()
        holder.itemView.countryCuredValueTextView.text = country?.totalRecovered.toString()
    }
}

class CountryViewHolder(v: View): RecyclerView.ViewHolder(v) {

}

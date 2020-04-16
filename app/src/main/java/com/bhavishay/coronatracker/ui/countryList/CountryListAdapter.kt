package com.bhavishay.coronatracker.ui.countryList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.models.data.Country
import kotlinx.android.synthetic.main.country_item_list.view.*





class CountryListAdapter(private val countries:List<Country> ) : RecyclerView.Adapter<CountryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item_list,
            parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bindView(country)
    }
}

class CountryViewHolder(v: View): RecyclerView.ViewHolder(v) {
    private val activeCasesText = v.stateConfirmedValue
    private val deathsText = v.stateDeadValueTextView
    private val recoveredText = v.stateCuredValueTextView
    private val countryNameText = v.stateTextView

    fun bindView(country:Country){
        with(country){
            activeCasesText.text = cases
            deathsText.text = deaths
            recoveredText.text = totalRecovered
            countryNameText.text = countryName
        }
    }


}

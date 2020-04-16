package com.bhavishay.coronatracker.ui.countryList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.helpers.TimeHelper
import com.bhavishay.coronatracker.models.data.Country
import com.bhavishay.coronatracker.models.data.WorldTotalStats
import kotlinx.android.synthetic.main.country_item_list.view.*
import kotlinx.android.synthetic.main.item_world_stats.view.*
import java.text.SimpleDateFormat
import java.util.*

val TYPE_WORLD_STATS_ITEM = 1
val TYPE_COUNTRY_LIST_ITEM = 2
class CountryListAdapter(private val countries:List<Country>, private val worldTotalStats: WorldTotalStats) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {

        if (viewType == TYPE_WORLD_STATS_ITEM) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_world_stats, parent, false)
            return StatsViewHolder(view)
        }

        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item_list,
            parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size + 1
    }



    override fun getItemViewType(position: Int): Int {
        return if(position == 0)
            TYPE_WORLD_STATS_ITEM
        else TYPE_COUNTRY_LIST_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == TYPE_WORLD_STATS_ITEM)
            (holder as StatsViewHolder).bindView(worldTotalStats)
        else (holder as CountryViewHolder).bindView(countries[position-1])
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

class StatsViewHolder(v:View):RecyclerView.ViewHolder(v){
    private val confirmedCasesText = v.text_confirmed_cases
    private val deceasedText = v.text_deceased_cases
    private val recoveredCasesText = v.text_recovered_cases
    private val lastUpdateText = v.tv_update_date

    fun bindView(worldTotalStats: WorldTotalStats){

        with(worldTotalStats){
            confirmedCasesText.text = totalCases
            deceasedText.text = totalDeaths
            recoveredCasesText.text = totalRecovered
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
            val date = formatter.parse(statsTakenAt)
            lastUpdateText.text = "Updated ${TimeHelper.getTimeAgo(date.time)}"
        }
    }
}

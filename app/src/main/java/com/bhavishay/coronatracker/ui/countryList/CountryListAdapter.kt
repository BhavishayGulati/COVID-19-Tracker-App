package com.bhavishay.coronatracker.ui.countryList

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.activities.CountryActivity
import com.bhavishay.coronatracker.charts.SummaryPieChart
import com.bhavishay.coronatracker.helpers.TimeHelper
import com.bhavishay.coronatracker.models.data.Country
import com.bhavishay.coronatracker.models.data.WorldTotalStats
import kotlinx.android.synthetic.main.activity_country.view.*
import kotlinx.android.synthetic.main.country_item_list.view.*
import kotlinx.android.synthetic.main.item_world_stats.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val TYPE_WORLD_STATS_ITEM = 1
const val TYPE_COUNTRY_LIST_ITEM = 2
class CountryListAdapter()
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable{

    private lateinit var countriesList : List<Country>
    private lateinit var countriesListFiltered : List<Country>
    private lateinit var worldTotalStats: WorldTotalStats

    constructor( countries:List<Country>,  worldTotalStats: WorldTotalStats):this(){
        this.countriesList = countries
        this.countriesListFiltered = countries
        this.worldTotalStats = worldTotalStats
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {

        if (viewType == TYPE_WORLD_STATS_ITEM) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_world_stats, parent, false)
//            val summaryPieChart = LayoutInflater.from(parent.context)
//                .inflate(R.layout.,parent,false)
            return StatsViewHolder(view)
        }

        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item_list,
            parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countriesListFiltered.size + 1
    }



    override fun getItemViewType(position: Int): Int {
        return if(position == 0)
            TYPE_WORLD_STATS_ITEM
        else TYPE_COUNTRY_LIST_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == TYPE_WORLD_STATS_ITEM)
            (holder as StatsViewHolder).bindView(worldTotalStats)
        else (holder as CountryViewHolder).bindView(countriesListFiltered[position-1])

    }

    override fun getFilter(): Filter {
        return object:Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val query = p0.toString()
                countriesListFiltered = if(query.isEmpty())
                    countriesList
                else{
                    val countries = ArrayList<Country>()
                    for(country in countriesList){
                        if(country.countryName.toLowerCase().contains(query.toLowerCase()))
                            countries.add(country)
                    }
                    countries
                }

                val filterResults = FilterResults()
                filterResults.values = countriesListFiltered
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
               countriesListFiltered = p1?.values as ArrayList<Country>
                notifyDataSetChanged()
            }

        }
    }



}



class CountryViewHolder(v: View): RecyclerView.ViewHolder(v) {

    private val activeCasesText = v.stateConfirmedValue
    private val deathsText = v.stateDeadValueTextView
    private val recoveredText = v.stateCuredValueTextView
    private val countryNameText = v.stateTextView
    private val pieChart = v.country_pie_chart
    private val mortalityRatePieChart = v.mortality_rate_country


    fun bindView(country:Country){
        with(country){
            activeCasesText.text = cases
            deathsText.text = deaths
            recoveredText.text = totalRecovered
            countryNameText.text = countryName
//            pieChart.configureCountry(country)
//            mortalityRatePieChart.configureMortality(((deaths.replace(",","").toDouble()/cases.replace(",","").toDouble())*100).toFloat())
        }
        itemView.setOnClickListener{
            val intent = Intent(it.context,CountryActivity::class.java)
            intent.putExtra("name",country.countryName)
            intent.putExtra("cases",country.cases)
            intent.putExtra("active",country.activeCases)
            intent.putExtra("cured",country.totalRecovered)
            intent.putExtra("deaths",country.deaths)
            intent.putExtra("newDeaths",country.newDeaths)
            intent.putExtra("newCases",country.newCases)

            it.context.startActivity(intent)
        }
    }


}

class StatsViewHolder(v:View):RecyclerView.ViewHolder(v){
    private val confirmedCasesText = v.text_confirmed_cases
    private val deceasedText = v.text_deceased_cases
    private val recoveredCasesText = v.text_recovered_cases
    private val lastUpdateText = v.tv_update_date
    private val mortalityRate = v.tv_mortality_rate_value
    private val pieChart = v.summary_pie_chart


    fun bindView(worldTotalStats: WorldTotalStats){

        with(worldTotalStats){
            confirmedCasesText.text = totalCases
            deceasedText.text = totalDeaths
            recoveredCasesText.text = totalRecovered
           mortalityRate.text = ((totalDeaths.replace(",","").toDouble()/totalCases.replace(",","").toDouble())*100).toInt().toString() + "%"
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
            val date = formatter.parse(statsTakenAt)
            lastUpdateText.text = "Updated ${TimeHelper.getTimeAgo(date.time)}"
            pieChart.configure(worldTotalStats)
        }
    }
}

package com.bhavishay.coronatracker.ui.states

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.helpers.TimeHelper
import com.bhavishay.coronatracker.models.data.IndiaTotalStats
import com.bhavishay.coronatracker.models.data.State
import kotlinx.android.synthetic.main.item_india_stats.view.*
import kotlinx.android.synthetic.main.states_item_list.view.*
import java.text.SimpleDateFormat
import java.util.*

const val TYPE_INDIA_STATS_ITEM = 1
const val TYPE_STATE_LIST_ITEM = 2

class StageListAdapter(private val states:List<State>,private val indiaTotalStats: IndiaTotalStats): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == TYPE_INDIA_STATS_ITEM){
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_india_stats,parent,false)
            return IndiaStatsViewHolder(view)
        }

        val view = LayoutInflater.from(parent.context).inflate(R.layout.states_item_list,
        parent,false)
        return StateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return states.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0)
            TYPE_INDIA_STATS_ITEM
        else TYPE_STATE_LIST_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == TYPE_INDIA_STATS_ITEM)
            (holder as IndiaStatsViewHolder).bindView(indiaTotalStats)
        else (holder as StateViewHolder).bindView(states[position - 1])
    }
}

class StateViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val activeCasesText = v.stateConfirmedValue
    private val deathsText = v.stateDeadValueTextView
    private val recovered_Cases = v.stateCuredValueTextView
    private val state_Name = v.stateTextView
    fun bindView(state: State) {
        with(state) {
            activeCasesText.text = activeCases
            deathsText.text = deaths
            state_Name.text = stateName
            recovered_Cases.text = recoveredCases
        }
    }
}

class IndiaStatsViewHolder(v: View):RecyclerView.ViewHolder(v){
    private val casesText = v.txt_cases
    private val recoveredText = v.txt_recovered
    private val deceasedText = v.txt_deaths
    private val lastUpdatedText = v.update_time
    private val mortalityRate = v.mortality_value

    fun bindView(indiaTotalStats: IndiaTotalStats){

        with(indiaTotalStats){
            casesText.text = confirmedCases
            recoveredText.text = recoveredCases
            deceasedText.text = deaths
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH)
            val date = formatter.parse(lastUpdatedTime)
            lastUpdatedText.text = "Updated ${TimeHelper.getTimeAgo(date.time)}"
            mortalityRate.text = ((deaths.replace(",","").toDouble()/confirmedCases.replace(",","").toDouble())*100).toInt().toString() + "%"

        }
    }
}
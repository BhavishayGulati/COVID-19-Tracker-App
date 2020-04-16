package com.bhavishay.coronatracker.ui.states

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.models.data.State
import kotlinx.android.synthetic.main.states_item_list.view.*

class StageListAdapter(private val states:List<State>): RecyclerView.Adapter<StateListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.states_item_list,
        parent,false)
        return StateListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return states.size
    }

    override fun onBindViewHolder(holder: StateListViewHolder, position: Int) {
        val state = states[position]
        holder.bindView(state)
    }
}

class StateListViewHolder(v: View) : RecyclerView.ViewHolder(v) {
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
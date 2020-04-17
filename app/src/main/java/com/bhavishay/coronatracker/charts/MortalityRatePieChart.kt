package com.bhavishay.coronatracker.charts

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.color
import androidx.core.text.scale
import com.bhavishay.coronatracker.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener


class MortalityRatePieChart(context: Context, attributes: AttributeSet): PieChart(context, attributes), OnChartValueSelectedListener{
    fun configureMortality(value: Float) {
        this.centerText = ""
        this.setCenterTextSize(20f)
        this.setUsePercentValues(true)
        this.isRotationEnabled = false
        this.setDrawEntryLabels(false)
        this.setHoleColor(Color.TRANSPARENT)
        this.setCenterTextColor(Color.WHITE)
        val desc = Description()
        desc.isEnabled = false
        this.description = desc
        this.legend.isEnabled = false
        this.holeRadius = 80f
        this.setOnChartValueSelectedListener(this@MortalityRatePieChart)

        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(value*100, resources.getString(R.string.mortality_rate)))
        entries.add(PieEntry((1-value)*100, ""))

        val dataSet = PieDataSet(entries, "summary")
        dataSet.setDrawValues(false)
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 2.5f
        this.data = PieData(dataSet)

        setCenterText(entries[0])
        dataSet.colors = listOf(
            ContextCompat.getColor(this.context!!, R.color.pastelRed),
            ContextCompat.getColor(this.context!!, R.color.pastelLightBlue))

        this.data = PieData(dataSet)
        this.animateXY(500, 500)
        this.highlightValue(Highlight(0f,0f,0))
        this.invalidate()
    }

    private fun setCenterText(entry: PieEntry) {

        if (entry.value.isNaN())  return
        val text = SpannableStringBuilder()
            .color(Color.WHITE) {
                bold {
                    append("${ Math.round(entry.value) }")
                }
            }
            .append("\n")
            .color(ContextCompat.getColor(this.context!!, R.color.lightText)) {
                scale(0.6f) {
                    append("${ entry.label }")
                }
            }

        this.centerText = text
    }

    override fun onNothingSelected() {}

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        setCenterText(e as PieEntry)
    }
}
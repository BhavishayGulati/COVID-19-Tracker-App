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
import com.bhavishay.coronatracker.models.data.Country
import com.bhavishay.coronatracker.models.data.Summary
import com.bhavishay.coronatracker.models.data.WorldTotalStats
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener


class SummaryPieChart(context: Context, attributes: AttributeSet): PieChart(context, attributes), OnChartValueSelectedListener{

    fun configure(summary: Country) {
        this.centerText = ""
        this.setCenterTextSize(20f)
        this.setUsePercentValues(true)
        this.isRotationEnabled = false
        this.setDrawEntryLabels(false)
        this.setHoleColor(Color.TRANSPARENT)
        this.setCenterTextColor(Color.WHITE)
        this.legend.isEnabled = false
        this.holeRadius = 80f
        val desc = Description()
        desc.isEnabled = false
        this.description = desc

        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(summary.activeCases.toFloat(), resources.getString(R.string.confirmed)))
        entries.add(PieEntry(summary.totalRecovered.toFloat(), resources.getString(R.string.cured)))
        entries.add(PieEntry(summary.deaths.toFloat(), resources.getString(R.string.dead)))

        val dataSet = PieDataSet(entries, "summary")
        dataSet.setDrawValues(false)
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 2.5f
        this.data = PieData(dataSet)

        setCenterText(entries[0])
        dataSet.colors = listOf(
            ContextCompat.getColor(this.context!!, R.color.pastelOrange),
            ContextCompat.getColor(this.context!!, R.color.pastelGreen),
            ContextCompat.getColor(this.context!!, R.color.pastelRed),
            Color.YELLOW)

        this.data = PieData(dataSet)
        this.animateXY(500, 500)
        this.highlightValue(Highlight(0f,0f,0))
        this.setOnChartValueSelectedListener(this@SummaryPieChart)
        this.invalidate()
    }

    private fun setCenterText(entry: PieEntry) {
        if (entry.value <= 0) return

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

    override fun onNothingSelected() {
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        setCenterText(e as PieEntry)
    }
}
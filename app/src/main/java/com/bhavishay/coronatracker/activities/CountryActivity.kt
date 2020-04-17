package com.bhavishay.coronatracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.models.data.Country
import kotlinx.android.synthetic.main.activity_country.*
import kotlinx.android.synthetic.main.item_world_stats.*

class CountryActivity : AppCompatActivity() {

    lateinit var country: Country
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        country_title_name.text = intent.getStringExtra("name")
        val cases = intent.getStringExtra("cases")
        val deaths = intent.getStringExtra("deaths")
        val totalRecovered = intent.getStringExtra("cured")
        val mortalityRate = ((deaths.replace(",","").toFloat()/cases.replace(",","").toFloat()))

        txt_cases.text= cases
        txt_deaths.text = deaths
        txt_recovered.text = totalRecovered
        txt_new_cases.text = intent.getStringExtra("newCases")
        txt_new_deaths.text = intent.getStringExtra("newDeaths")
        mortality_rate_country.configureMortality(mortalityRate)
        country_pie_chart.configureCountry(cases!!,deaths!!,totalRecovered!!)
    }
}

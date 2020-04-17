package com.bhavishay.coronatracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.*
import com.bhavishay.coronatracker.R
import kotlinx.android.synthetic.main.news_webview_fragment.*

class NewsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_webview_fragment)
        setSupportActionBar(news_main_toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val url = intent.getStringExtra("url")
        webView.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}


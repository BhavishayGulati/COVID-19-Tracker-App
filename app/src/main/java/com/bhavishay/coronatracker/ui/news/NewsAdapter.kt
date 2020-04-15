package com.bhavishay.coronatracker.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bhavishay.coronatracker.R
import com.bhavishay.coronatracker.helpers.DiffUtilCallBack
import com.bhavishay.coronatracker.helpers.TimeHelper
import com.bhavishay.coronatracker.models.data.NewsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item_list.view.*
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter : PagedListAdapter<NewsItem,NewsAdapter.ViewHolder>(DiffUtilCallBack()) {


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val newsTitleText = itemView.newsTitle
        private val newsSourceText = itemView.newsSource
        private val newsPublishedDate = itemView.newspubDate
        private val newsImage = itemView.newsImage

        fun bindView(newsItem: NewsItem){
            val picasso = Picasso.Builder(itemView.context).build()

                newsTitleText.text = newsItem.newsTitle
                newsSourceText.text = newsItem.source.sourceName
                newsPublishedDate.text = newsItem.timeString
                picasso.load(newsItem.urlToImage).into(newsImage)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindView(it)
        }
    }
}
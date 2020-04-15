package com.bhavishay.coronatracker.helpers

import androidx.recyclerview.widget.DiffUtil
import com.bhavishay.coronatracker.models.data.NewsItem

class DiffUtilCallBack : DiffUtil.ItemCallback<NewsItem>() {
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem.newsTitle == newItem.newsTitle
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem.authorName == newItem.authorName
                && oldItem.content == newItem.content
                && oldItem.url == newItem.url
    }

}
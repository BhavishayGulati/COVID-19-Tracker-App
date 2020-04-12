package com.bhavishay.coronatracker.helpers

import android.annotation.SuppressLint
import android.content.Context
import com.bhavishay.coronatracker.R


private const val SECOND_MILLIS = 1000
private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS


class TimeHelper {
    companion object {
        @SuppressLint("StringFormatMatches")
        fun getTimeAgo(context: Context?, time: Long): String? {
            var time = time
            if (time < 1000000000000L) {
                time *= 1000
            }
            val now: Long = System.currentTimeMillis()
            if (time > now || time <= 0) {
                return null
            }
            val diff = now - time
            return if (diff < MINUTE_MILLIS) {
                context?.resources?.getString(R.string.justnow)
            } else if (diff < 2 * MINUTE_MILLIS) {
                context?.resources?.getString(R.string.aminuteago)
            } else if (diff < 50 * MINUTE_MILLIS) {
                context?.resources?.getString(R.string.xminutesago, diff / MINUTE_MILLIS)
            } else if (diff < 90 * MINUTE_MILLIS) {
                context?.resources?.getString(R.string.anhourago)
            } else if (diff < 24 * HOUR_MILLIS) {
                context?.resources?.getString(R.string.xhoursago, diff / HOUR_MILLIS)
            } else if (diff < 48 * HOUR_MILLIS) {
                context?.resources?.getString(R.string.yesterday)
            } else {
                context?.resources?.getString(R.string.xdaysago, diff / DAY_MILLIS)
            }
        }
    }
}


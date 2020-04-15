package com.bhavishay.coronatracker.ui.info.help

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel

class HelpViewModel (var application:Application): ViewModel() {

     fun getShareIntent(): Intent{

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,"This is the App link")
        return shareIntent
    }
//    fun startWhatsapp(number: String,context: Context) : Intent?{
//        val intent = Intent(Intent.ACTION_VIEW)
//        intent.setPackage("com.whatsapp")
//        val data: String = if(number[0] == '+'){
//            number.substring(1)
//        }else if(number.length == 10){
//            "91" + number
//        }else{
//            number
//        }
//        intent.data = Uri.parse("https://wa.me/$data")
//        if(application.packageManager.resolveActivity(intent,0)!=null){
//            return intent
//        }
//        return null
//    }
}



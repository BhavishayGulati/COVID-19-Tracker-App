package com.bhavishay.coronatracker.ui.info.help

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bhavishay.coronatracker.R
import com.google.android.gms.common.wrappers.Wrappers.packageManager
import kotlinx.android.synthetic.main.help_fragment.*

class HelpFragment : Fragment() {

    companion object {
        fun newInstance() = HelpFragment()
    }

    private lateinit var viewModel: HelpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.help_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(Intent.ACTION_VIEW)

        helpline_no.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                val phoneNumber =  "911123978046"
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(intent)
        }
        lockdown.setOnClickListener {
            val lockdown = Intent(Intent.ACTION_VIEW)
            lockdown.data = Uri.parse("https://1drv.ms/b/s!Avd5iVsATqWEhzvO9oxUXn-p6l6a?e=XDHskY")
            startActivity(lockdown)
        }

        helpline_message.setOnClickListener{
            val message = Intent(Intent.ACTION_VIEW)
            message.data = Uri.parse("smsto:"+1075)
            message.type = "vnd.android-dir/mms-sms"
            startActivity(message)
        }
        mentalhealth_details.setOnClickListener {
            intent.data = Uri.parse("https://1drv.ms/b/s!Avd5iVsATqWEhzoEVCvv0tOyYywZ?e=buomNV")
            startActivity(intent)
        }


        txt_phone_details.setOnClickListener{
             intent.data = Uri.parse("https://www.mohfw.gov.in/pdf/coronvavirushelplinenumber.pdf")
            startActivity(intent)
        }
        whatsapp_imageBtn.setOnClickListener{
            intent.setPackage("com.whatsapp")
            intent.data = Uri.parse("https://wa.me/919013353535")
            startActivity(intent)
        }

        instagram_imageBtn.setOnClickListener{
            intent.data = Uri.parse("https://www.instagram.com/mygovindia/")
            startActivity(intent)
        }

        gmail_imageBtn.setOnClickListener{
            val mail = Intent(Intent.ACTION_SENDTO)
            mail.data = Uri.parse("mailto:"+Uri.encode("ncov2019@govt.in"))
            startActivity(mail)
        }

        twitter_imageBtn.setOnClickListener {
            intent.data = Uri.parse("https://twitter.com/mygovindia")
            startActivity(intent)
        }

        facebook_imageBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.facebook.com/MyGovIndia/")
            startActivity(intent)
        }
    }
}

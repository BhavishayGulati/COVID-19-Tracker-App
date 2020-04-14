package com.bhavishay.coronatracker.ui.info.help

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bhavishay.coronatracker.R
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

        var view = inflater.inflate(R.layout.help_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(Intent.ACTION_VIEW)

        helpline_no.setOnClickListener {
            val phone = Intent(Intent.ACTION_CALL)
            phone.data = Uri.parse("tel:"+911123978046)
            startActivity(phone)
        }

        helpline_message.setOnClickListener{
            val message = Intent(Intent.ACTION_VIEW)
            message.setData(Uri.parse("smsto:"+1075));
            message.setType("vnd.android-dir/mms-sms");
            startActivity(message)
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

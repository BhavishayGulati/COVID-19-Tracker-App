package com.bhavishay.coronatracker.ui.info.precautions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bhavishay.coronatracker.R
import kotlinx.android.synthetic.main.precautions_fragment.*

class PrecautionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.precautions_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
        arrowBtn2.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
        arrowBtn.setOnClickListener {
            if (expandableView.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(
                    cardView,
                    AutoTransition()
                )
                expandableView.visibility = View.VISIBLE
                arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
            } else {
                TransitionManager.beginDelayedTransition(
                    cardView,
                    AutoTransition()
                )
                expandableView.visibility = View.GONE
                arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
            }

            arrowBtn2.setOnClickListener {
                if (expandableView1.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(
                        cardView1,
                        AutoTransition()
                    )
                    expandableView1.visibility = View.VISIBLE
                    arrowBtn2.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
                } else {
                    TransitionManager.beginDelayedTransition(
                        cardView1,
                        AutoTransition()
                    )
                    expandableView1.visibility = View.GONE
                    arrowBtn2.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
                }
            }

            dos_donts_image.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.mohfw.gov.in/pdf/Poster_Corona_ad_Eng.pdf")
                startActivity(intent)
            }


            learn_more_image.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data =
                    Uri.parse("https://cowin.s3.ap-south-1.amazonaws.com/Learn+more+about+COVID-19+English.pdf")
                startActivity(intent)
            }
        }
    }
}

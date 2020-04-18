package com.bhavishay.coronatracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bhavishay.coronatracker.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        lottie.setAnimation("emergency.json")
        lottie.playAnimation()
        lottie.loop(true)
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

}

package com.bhavishay.coronatracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import com.bhavishay.coronatracker.R
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.lang.ref.WeakReference

class SplashScreen : AppCompatActivity() {


    companion object {
        private const val SPLASH_DISPLAY_TIME = 2000L //in milliseconds
    }

    private val mStartNextActivityHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        applyWindowInsets(findViewById(android.R.id.content))

        //start login or main screen (depends on the app logic)
        mStartNextActivityHandler.postDelayed(
            SplashRunnable(
                this,
                Navigation.START_MAIN_SCREEN
            ),
            SPLASH_DISPLAY_TIME
        )


    }

    private fun applyWindowInsets(view: View) {
        //Use Window Insets to set top and bottom paddings to our activity
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            v.updatePadding(
                left = insets.systemWindowInsetLeft,
                top = insets.systemWindowInsetTop,
                right = insets.systemWindowInsetRight,
                bottom = insets.systemWindowInsetBottom
            )
            insets
        }
    }

    private class SplashRunnable(activity: AppCompatActivity, val startScreenCode: Navigation) : Runnable {
        private val mActivityReference: WeakReference<AppCompatActivity?> = WeakReference(activity)
        override fun run() {
            if (mActivityReference.get() != null) {
                val activity = mActivityReference.get()

                val intent: Intent = when (startScreenCode) {
                    Navigation.START_MAIN_SCREEN -> {
                        Intent(activity, MainActivity::class.java)
                    }
                }
                activity!!.startActivity(intent)
                activity.finish()
            }
        }
    }

    private enum class Navigation {
        START_MAIN_SCREEN
    }
}

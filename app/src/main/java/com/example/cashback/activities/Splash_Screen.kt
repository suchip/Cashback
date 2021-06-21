package com.example.cashback.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.cashback.Home_Screen
import com.example.cashback.R

class Splash_Screen : Activity() {
    lateinit var sharedPreferences: SharedPreferences
    var isLogined: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)


        sharedPreferences =
            getSharedPreferences(
                "loginprefs",
                Context.MODE_PRIVATE
            )
        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        isLogined = sharedPreferences.getBoolean("islogin", false)

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({

            if (isLogined) {
                val intent = Intent(this, Home_Screen::class.java)
                startActivity(intent)
                finish()

            }else{
                val intent = Intent(this, Login_Screen::class.java)
                startActivity(intent)
                finish()

            }

        }, 3000) // 3000 is the delayed time in milliseconds.
    }
}
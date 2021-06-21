package com.example.cashback.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.cashback.R
import com.google.firebase.auth.FirebaseAuth

class Login_Screen : Activity() {
    private lateinit var auth: FirebaseAuth
    lateinit var mobilenumber_edittext: EditText
    lateinit var send_button: Button
    lateinit var mobielno_strng: String

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        sharedPreferences =
            getSharedPreferences(
                "loginprefs",
                Context.MODE_PRIVATE
            )

        send_button = findViewById(R.id.send_button)
        mobilenumber_edittext = findViewById(R.id.mobilenumber_edittext)

        auth = FirebaseAuth.getInstance()

        send_button.setOnClickListener {
            mobielno_strng = mobilenumber_edittext.text.toString().trim()

            val editor = sharedPreferences.edit()
            editor.putString("usermobilenumber", mobielno_strng)
            Log.e("usermobile",""+mobielno_strng)
            editor.commit()
            startActivity(
                Intent(
                    this,
                    Otp_Screen::class.java
                )
            )
            finish()
        }
    }



}
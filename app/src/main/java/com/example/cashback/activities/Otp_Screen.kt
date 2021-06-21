package com.example.cashback.activities

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookursalon.util.NetWorkConection
import com.example.cashback.*
import com.example.cashback.R
import com.example.cashback.models.UserLogin.UserLogin
import com.google.android.gms.common.api.Response
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import java.util.concurrent.TimeUnit


class  Otp_Screen : Activity() {
    lateinit var mobile: String
    lateinit var mobilenumber: TextView
    lateinit var otp_button: Button
    lateinit var resendcode: TextView
    lateinit var otpedittext1: EditText
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mVerificationId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.otp_activity)

        sharedPreferences =
            getSharedPreferences(
                "loginprefs",
                Context.MODE_PRIVATE
            )
        otp_button = findViewById(R.id.otp_button)
        mobilenumber = findViewById(R.id.mobilenumber)
        resendcode = findViewById(R.id.resendcode)
        otpedittext1 = findViewById(R.id.otpedittext1)
        mAuth = FirebaseAuth.getInstance()
//        val intent = intent
//        mobile = intent.getStringExtra("usermobile").toString()

        mobile = sharedPreferences.getString("usermobilenumber", "").toString()

        mobilenumber.text="We Have Sent The OTP On "+ mobile

        sendVerificationCode(mobile)

        otp_button.setOnClickListener {
            val otp_stng1 = otpedittext1.text.toString().trim()
            val code = otp_stng1
            if (otp_stng1.isEmpty() || code.length < 6) {
                otpedittext1.error = "Enter valid code"
                otpedittext1.requestFocus()
            } else {
                verifyVerificationCode(code)
            }
        }

        resendcode.setOnClickListener {
            sresendendVerificationCode()
        }


    }

    private fun sresendendVerificationCode() {
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber("+91$mobile")       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)


        Log.e("login mobile", mobile)    }

    private fun verifyVerificationCode(code: String) { //creating the credential
        val credential = PhoneAuthProvider.getCredential(mVerificationId, code)
        //signing the user
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) { //verification successful we will start the profile activity

                    postmobilenumber()

                } else { //verification unsuccessful.. display an error message
                    var message =
                        "Somthing is wrong, we will fix it soon..."
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        message = "Invalid code entered..."
                    }
//                    Toast.makeText(this, "" + message, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun sendVerificationCode(mobile: String) {
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber("+91$mobile")       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)


        Log.e("login mobile", mobile)
    }



    private val mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) { //Getting the code sent by SMS
                val code = phoneAuthCredential.smsCode
                //sometime the code is not detected automatically
//in this case the code will be null
//so user has to manually enter the code
                if (code != null) {
                    otpedittext1.setText(code)
                    //verifying the code
                    verifyVerificationCode(code)
                }
            }
            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@Otp_Screen, e.message, Toast.LENGTH_LONG).show()
            }
            override fun onCodeSent(
                s: String,
                forceResendingToken: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(s, forceResendingToken)
                //storing the verification id that is sent to the user
                mVerificationId = s
            }
        }

    private fun postmobilenumber() {

        if (NetWorkConection.isNEtworkConnected(this)) {

            try {

                var apiServices = APIClient_Main.client.create(Api::class.java)

                Log.e("API : ", "" + apiServices)


                val call = apiServices.Logindetails("mobile_number",mobile)

                Log.e("API Cards : ", "" + call)
                Log.e("mobilen : ", "" + mobile)

//                progressBarotp.visibility = View.VISIBLE

                call.enqueue(object : retrofit2.Callback<UserLogin> {


                    override fun onResponse(
                        call: retrofit2.Call<UserLogin>,
                        response: retrofit2.Response<UserLogin>
                    ) {
                        val loginResponse = response.body()
//                        progressBarotp.visibility = View.GONE

                        Log.e(" Login Response", "" + response.body())

                        if (response.isSuccessful) {


                            Log.e(ContentValues.TAG, response.toString())
                            Log.e(" Login Response", "" + response.body())

                            val token = response.body()?.response?.token

                            val editor = sharedPreferences.edit()
                            editor.putString("token", token)
                            editor.putBoolean("islogin", true)

                            editor.commit()
                            startActivity(
                                Intent(
                                    this@Otp_Screen,
                                   Home_Screen ::class.java
                                )
                            )
                            finish()

                        }

                    }

                    override fun onFailure(call: retrofit2.Call<UserLogin>, t: Throwable) {

//                        progressBarotp.visibility = View.GONE


                        Toast.makeText(
                            this@Otp_Screen,
                            "invalid OTP",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        Log.e("otp response", t.toString())

                    }
                })

            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: UninitializedPropertyAccessException) {
                e.printStackTrace()
            }
        } else {

            Toast.makeText(this, "Please Check your internet", Toast.LENGTH_LONG).show()
        }

    }

}



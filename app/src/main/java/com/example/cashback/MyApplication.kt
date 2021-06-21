package com.example.cashback

import android.app.Application
import com.example.cashback.roomdatabase.CartDatabase

class MyApplication : Application(){

    private val appRunning = false
    override fun onCreate() {
        super.onCreate()
        CartDatabase.getAppDatabase(this) //--AppDatabase_Impl does not exist
    }
}
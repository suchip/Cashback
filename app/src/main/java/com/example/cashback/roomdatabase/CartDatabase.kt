package com.example.cashback.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Cart::class], version = 1)
abstract class CartDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao?

    companion object {
        private const val DATABASE_NAME = "MyCart"
        private var INSTANCE: CartDatabase? = null
        fun getAppDatabase(context: Context): CartDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    CartDatabase::class.java, DATABASE_NAME
                ) // allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
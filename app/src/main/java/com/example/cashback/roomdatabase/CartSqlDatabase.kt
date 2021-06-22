package com.example.cashback.roomdatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CartSqlDatabase(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?
) :
    SQLiteOpenHelper(
        context, DATABASE_NAME,
        factory, DATABASE_VERSION
    ) {


    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME
                + " TEXT" + COLUMN_IMAGE + "TEXT" + COLUMN_ACTUALPRICE + "TEXT" + COLUMN_OFFERPRICE + "TEXT" + ")")
        db!!.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

    }

    //    fun addName(name: String) {
    fun addName(
        wi_id: String,
        wi_name: String,
        wi_image: String,
        wi_actualprice: String,
        wi_offerprice: String
    ) {
        val values = ContentValues()
        values.put(COLUMN_ID, wi_id)
        values.put(COLUMN_NAME, wi_name)
        values.put(COLUMN_IMAGE, wi_image)
        values.put(COLUMN_ACTUALPRICE, wi_actualprice)
        values.put(COLUMN_OFFERPRICE, wi_offerprice)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllName(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    companion object {
        private val DATABASE_NAME = "shopping_cart.db"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "cart"
        val COLUMN_ID = "_id"
        val COLUMN_NAME = "name"
        val COLUMN_IMAGE = "image"
        val COLUMN_ACTUALPRICE = "actual_price"
        val COLUMN_OFFERPRICE = "offer_price"
    }


}
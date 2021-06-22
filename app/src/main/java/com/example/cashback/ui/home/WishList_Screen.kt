package com.example.cashback.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cashback.Adapter.WishlistAdapter
import com.example.cashback.R
import com.example.cashback.roomdatabase.CartSqlDatabase


class WishList_Screen : Fragment() {

    lateinit var nodata_textview: TextView
    lateinit var amazonwishlistrecyler: RecyclerView
    lateinit var progressBaramazon: ProgressBar
    lateinit var sharedPreferences: SharedPreferences
    lateinit var cartarrayList: ArrayList<String>
    var adapter: WishlistAdapter? = null

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.wishlist_screen, container, false)
        sharedPreferences =
            requireActivity().getSharedPreferences(
                "loginprefs",
                Context.MODE_PRIVATE
            )
        amazonwishlistrecyler = root.findViewById(R.id.amazonwishlistrecyler)
        nodata_textview = root.findViewById(R.id.nodata_textview)
        progressBaramazon = root.findViewById(R.id.progressBaramazon)
        // checkout = root.findViewById(R.id.checkout)


        amazonwishlistrecyler.layoutManager =
            LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        getamazonwishlist()


        return root
    }


    private fun getamazonwishlist() {



        val dbHandler = CartSqlDatabase(activity as Activity, null)
        val cursor = dbHandler.getAllName()
        cursor!!.moveToFirst()
        (cursor.getString(cursor.getColumnIndex(CartSqlDatabase.COLUMN_NAME)))
        while (cursor.moveToNext()) {
            cartarrayList.add(cursor.getString(cursor.getColumnIndex(CartSqlDatabase.COLUMN_ID)))
            cartarrayList.add(cursor.getString(cursor.getColumnIndex(CartSqlDatabase.COLUMN_NAME)))
            cartarrayList.add(cursor.getString(cursor.getColumnIndex(CartSqlDatabase.COLUMN_IMAGE)))
            cartarrayList.add(cursor.getString(cursor.getColumnIndex(CartSqlDatabase.COLUMN_ACTUALPRICE)))
            cartarrayList.add(cursor.getString(cursor.getColumnIndex(CartSqlDatabase.COLUMN_OFFERPRICE)))

            Log.e("wishlist item list :",""+cartarrayList)
            nodata_textview.append(cursor.getString(cursor.getColumnIndex(CartSqlDatabase.COLUMN_NAME)))
            nodata_textview.append("\n")



        }

//        adapter = context?.let { WishlistAdapter(cartarrayList, it) }

        amazonwishlistrecyler.adapter=adapter
        cursor.close()

    }


}
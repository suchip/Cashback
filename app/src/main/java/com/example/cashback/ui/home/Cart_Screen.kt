package com.example.cashback.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookursalon.util.NetWorkConection
import com.example.cashback.APIClient_Main
import com.example.cashback.Adapter.Amazon_cart_Adapter
import com.example.cashback.Api
import com.example.cashback.R
import com.example.cashback.models.GetCartListResponse
import com.example.cashback.models.GetCartResponse
import retrofit2.Call
import retrofit2.Response

class Cart_Screen : Fragment() {

    lateinit var nodata_textview: TextView
    lateinit var amazoncartrecyler: RecyclerView
    lateinit var progressBaramazon: ProgressBar
    lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.cart_screen, container, false)
        sharedPreferences =
            requireActivity().getSharedPreferences(
                "loginprefs",
                Context.MODE_PRIVATE
            )
        amazoncartrecyler = root.findViewById(R.id.amazoncartrecyler)
        nodata_textview = root.findViewById(R.id.nodata_textview)
        progressBaramazon = root.findViewById(R.id.progressBaramazon)
       // checkout = root.findViewById(R.id.checkout)


        amazoncartrecyler.layoutManager =
            LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        getamazoncartlist()


        return root
    }


    private fun getamazoncartlist() {

        if (NetWorkConection.isNEtworkConnected(activity as Activity)) {

            try {
                val token = sharedPreferences.getString("token", "")

                var apiServices = APIClient_Main.client.create(Api::class.java)

                Log.e("API : ", "" + apiServices)


                val call =
                    apiServices.addcart_list("Token $token")


                Log.e("API Cards : ", "" + call)

                progressBaramazon.visibility = View.VISIBLE

                call.enqueue(object : retrofit2.Callback<GetCartResponse> {


                    override fun onResponse(
                        call: Call<GetCartResponse>,
                        response: Response<GetCartResponse>
                    ) {
                        val loginResponse = response.body()
                        progressBaramazon.visibility = View.GONE

                        Log.e(" matches Response", "" + response.body())

                        if (response.isSuccessful) {


                            val listOfbrands = response.body()?.results
                            if (listOfbrands!!.isEmpty()) {
                                nodata_textview.visibility = View.VISIBLE
                                amazoncartrecyler.visibility = View.GONE
                            } else {

                                nodata_textview.visibility = View.GONE
                                amazoncartrecyler.visibility = View.VISIBLE
                                val catrecyclerAdapter = Amazon_cart_Adapter(
                                    activity as Activity,
                                    listOfbrands as ArrayList<GetCartListResponse>
                                )
                                amazoncartrecyler.adapter = catrecyclerAdapter
                                catrecyclerAdapter.notifyDataSetChanged()


                                Log.e(ContentValues.TAG, response.toString())
                                Log.e(" cart Response", "" + response.body())


                            }
                        }
                    }

                    override fun onFailure(call: Call<GetCartResponse>, t: Throwable) {

                        progressBaramazon.visibility = View.GONE


                        Log.e(" response", t.toString())


                    }
                })

            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: UninitializedPropertyAccessException) {
                e.printStackTrace()
            }
        } else {

            Toast.makeText(activity, "Please Check your internet", Toast.LENGTH_LONG).show()
        }

    }



}
package com.example.cashback.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.bookursalon.util.NetWorkConection
import com.example.cashback.APIClient
import com.example.cashback.Api
import com.example.cashback.R
import com.example.cashback.models.Flipkart.FlipkartProductsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetail_Screen : Fragment() {
    lateinit var sharedPreferences: SharedPreferences

    lateinit var flipkartprice: TextView
    lateinit var actuallproductprice: TextView
    lateinit var productdiscription: TextView
    lateinit var producttittle: TextView
    lateinit var reviews: TextView
    lateinit var productimage: ImageView


    lateinit var compare_button: Button
    lateinit var product_id: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.productindetail_screen, container, false)

        sharedPreferences =
            requireContext().getSharedPreferences(
                "loginprefs",
                Context.MODE_PRIVATE
            )

        product_id = sharedPreferences.getString("product_id", "").toString()

        compare_button = root.findViewById(R.id.compare_button)
        flipkartprice = root.findViewById(R.id.flipkartprice)
        actuallproductprice = root.findViewById(R.id.actuallproductprice)
        producttittle = root.findViewById(R.id.producttittle)
        productimage = root.findViewById(R.id.productimage)
        productdiscription = root.findViewById(R.id.productdiscription)
        reviews = root.findViewById(R.id.reviews)
        actuallproductprice.paintFlags = actuallproductprice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        compare_button.setOnClickListener {
            val navController =
                Navigation.findNavController(activity as Activity, R.id.nav_host_fragment)
            navController.navigate(R.id.nav_compare)

        }

        getofferdetailss()
        return root
    }

    private fun getofferdetailss() {

        if (NetWorkConection.isNEtworkConnected(activity as Activity)) {

            //Set the Adapter to the RecyclerView//


            var apiServices = APIClient.client.create(Api::class.java)

            val call =
                apiServices.getofferdetailss("makelabsi", "a0e4fa1cb2c04dbdae7aba34ad2f42ab",product_id)

            call.enqueue(object : Callback<FlipkartProductsResponse> {
                @SuppressLint("WrongConstant")
                override fun onResponse(
                    call: Call<FlipkartProductsResponse>,
                    response: Response<FlipkartProductsResponse>
                ) {

                    //progressBarproduct.visibility = View.GONE
                    Log.e(ContentValues.TAG, response.toString())

                    if (response.isSuccessful) {

                        //Set the Adapter to the RecyclerView//

                        try {


                            val listOfcategories = response.body()?.productBaseInfoV1

                            Log.e("listcate", "" + listOfcategories)
//


                            producttittle.text=response.body()?.productBaseInfoV1?.title
                            flipkartprice.text="Rs "+response.body()?.productBaseInfoV1?.flipkartSellingPrice?.amount.toString()
                            actuallproductprice.text="Rs "+response.body()?.productBaseInfoV1?.maximumRetailPrice?.amount.toString()
                            productdiscription.text=response.body()?.productBaseInfoV1?.productDescription
                            reviews.text= response.body()?.productShippingInfoV1?.sellerNoOfRatings.toString()


                            Log.e("Pdiscription",""+productdiscription)
                            Log.e("reviews",""+reviews)


                            productimage?.let { it1 ->
                                context?.let {
                                    Glide.with(it).load(response.body()?.productBaseInfoV1?.imageUrls?.resolutionType2)
                                        .error(R.drawable.ic_flipkart)
                                        .into(it1)
                                }
                            }


                        } catch (e: NullPointerException) {
                            e.printStackTrace()
                        } catch (e: TypeCastException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(
                    call: Call<FlipkartProductsResponse>?,
                    t: Throwable?
                ) {
                    // progressBarproduct.visibility = View.GONE
                    Log.e(ContentValues.TAG, t.toString())
                }
            })


        } else {

            Toast.makeText(context, "Please Check your internet", Toast.LENGTH_LONG).show()
        }


    }

}
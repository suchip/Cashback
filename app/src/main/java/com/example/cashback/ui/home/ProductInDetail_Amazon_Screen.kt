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
import com.example.cashback.APIClient_Main
import com.example.cashback.Api
import com.example.cashback.R
import com.example.cashback.models.Amazon.AmazonResponse
import com.example.cashback.models.Flipkart.FlipkartProductsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductInDetail_Amazon_Screen : Fragment() {
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

        getindetaildata()
        return root
    }

    private fun getindetaildata() {

        if (NetWorkConection.isNEtworkConnected(activity as Activity)) {

            //Set the Adapter to the RecyclerView//

            val url = "amazon/product/$product_id/"

            var apiServices = APIClient_Main.client.create(Api::class.java)

            val call =
                apiServices.productindetail(url)

            call.enqueue(object : Callback<AmazonResponse> {
                @SuppressLint("WrongConstant")
                override fun onResponse(
                    call: Call<AmazonResponse>,
                    response: Response<AmazonResponse>
                ) {

                    //progressBarproduct.visibility = View.GONE
                    Log.e(ContentValues.TAG, response.toString())

                    if (response.isSuccessful) {

                        //Set the Adapter to the RecyclerView//

                        try {


                            val listOfcategories = response.body()

                            Log.e("listcate", "" + listOfcategories)
//


                            producttittle.text=response.body()?.browse_node_info!!.browse_nodes[0].context_free_name
                            flipkartprice.text="Rs "+response.body()?.offers!!.listings[0].price.amount.toString()
                            actuallproductprice.text="Rs "+response.body()?.offers!!.listings[0].saving_basis.display_amount
                            productdiscription.text= response.body()?.item_info!!.features.display_values.toString()
                           // reviews.text= response.body()?.productShippingInfoV1?.sellerNoOfRatings.toString()


                            Log.e("Pdiscription",""+productdiscription)
                            Log.e("reviews",""+reviews)


                            productimage?.let { it1 ->
                                context?.let {
                                    Glide.with(it).load(response.body()?.images!!.primary.large.url)
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
                    call: Call<AmazonResponse>?,
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
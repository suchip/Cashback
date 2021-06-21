package com.example.cashback.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.bookursalon.util.NetWorkConection
import com.example.cashback.APIClient
import com.example.cashback.Adapter.ShopSlideImage_Adapter
import com.example.cashback.Adapter.offerProducts_Adapter
import com.example.cashback.Api
import com.example.cashback.R
import com.example.cashback.models.Flipkart.FlipkartProductsResponse
import com.example.cashback.models.Flipkart.FlipkartResponse
import com.example.cashback.models.ImageModel
import com.viewpagerindicator.CirclePageIndicator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class HomeFragmentFlipkart : Fragment() {

    lateinit var productimage: ImageView
    lateinit var edit_search: EditText
    lateinit var productprice: TextView
    lateinit var favorites: ImageView
    lateinit var product_url: String
    lateinit var productoffsers: GridView
    lateinit var progressBarhome: ProgressBar
    lateinit var sharedPreferences: SharedPreferences
    lateinit var adapter_home: offerProducts_Adapter
    private val myImageList = intArrayOf(
        R.drawable.shoe,
        R.drawable.shoe,
        R.drawable.shoe
    )
    lateinit var shopcircleindicator: CirclePageIndicator
    private var imageModelArrayList: ArrayList<ImageModel>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home_flipkart, container, false)


        sharedPreferences =
            requireContext().getSharedPreferences(
                "loginprefs",
                Context.MODE_PRIVATE
            )

        product_url= sharedPreferences.getString("product_url","").toString()
//        productimage = root.findViewById(R.id.productimage)
        productoffsers = root.findViewById(R.id.productoffsers)
        progressBarhome = root.findViewById(R.id.progressBarhome)
//        edit_search = root.findViewById(R.id.edit_search)
//        productprice = root.findViewById(R.id.productprice)
//        favorites = root.findViewById(R.id.favorites)
////        shopsliderimages = root.findViewById<ViewPager>(R.id.shopsliderimages)
////        shopcircleindicator =root. findViewById<CirclePageIndicator>(R.id.shopcircleindicator)
//
//        productprice = root.findViewById(R.id.productprice) as TextView
//        someTextView.setText(someString)
//        productprice.paintFlags = productprice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//        edit_search.setOnClickListener {
//
//
//          startActivity(Intent(activity,MainActivity::class.java))
//        }


//        shopsliderimages!!.clipToPadding = false
//        imageModelArrayList = ArrayList()
//        imageModelArrayList = populateList()

//        imageSliderView()

        getofferdetails()
        return root
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun imageSliderView() {
        shopsliderimages!!.adapter =
            ShopSlideImage_Adapter(activity as Activity, imageModelArrayList!!)
        shopcircleindicator.setViewPager(shopsliderimages)
        val density = resources.displayMetrics.density
        //Set circle indicator radius
        shopcircleindicator.radius = 4 * density
        NUM_PAGES = imageModelArrayList!!.size
        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            shopsliderimages!!.setCurrentItem(
                currentPage++, true
            )
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 4000, 3000)
        // Pager listener over indicator
        shopcircleindicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {
            }

            override fun onPageScrollStateChanged(pos: Int) {
            }
        })
    }

    private fun populateList(): ArrayList<ImageModel> {
        val list = ArrayList<ImageModel>()
        for (i in 0..2) {
            val imageModel = ImageModel()
            imageModel.setImage_drawables(myImageList[i])
            list.add(imageModel)
        }
        return list
    }

    companion object {
        private var shopsliderimages: ViewPager? = null
        private var currentPage = 0
        private var NUM_PAGES = 0
    }


    private fun getofferdetails() {

        if (NetWorkConection.isNEtworkConnected(activity as Activity)) {

            //Set the Adapter to the RecyclerView//


            var apiServices = APIClient.client.create(Api::class.java)

            val call =
                apiServices.getflipkartofferdetails(product_url+"&inStock=true","makelabsi", "a0e4fa1cb2c04dbdae7aba34ad2f42ab")

            call.enqueue(object : Callback<FlipkartResponse> {
                @SuppressLint("WrongConstant")
                override fun onResponse(
                    call: Call<FlipkartResponse>,
                    response: Response<FlipkartResponse>
                ) {

                    progressBarhome.visibility = View.GONE
                    Log.e(ContentValues.TAG, response.toString())

                    if (response.isSuccessful) {

                        //Set the Adapter to the RecyclerView//

                        try {


                            val listOfcategories = response.body()?.products

                            Log.e("listcate", "" + listOfcategories)
//                        if (listOfcategories.isEmpty()) {
//                            noproducts.visibility = View.VISIBLE
//                            gvuserelectronics.visibility = View.GONE
//                            gvuserproductdetsils.visibility = View.GONE
//                            gvsearchproduct.visibility = View.GONE
//                            gvusercategory.visibility = View.GONE
//                            gvfitness.visibility = View.GONE
//
//                        } else {
//                            noproducts.visibility = View.GONE


                            adapter_home =
                                offerProducts_Adapter(
                                    activity as Activity,
                                    listOfcategories as ArrayList<FlipkartProductsResponse>
                                )

                            productoffsers.adapter = adapter_home

//                            productoffsers.setOnItemClickListener { parent, view, position, id ->
//                                var product_id =
//                                    adapter_home.arrayListImage.get(position).productBaseInfoV1.productId
//                                val editor = sharedPreferences.edit()
//                                editor.putString("product_id", product_id.toString())
//                                editor.commit()
//
//                                val navController =
//                                    Navigation.findNavController(activity as Activity, R.id.nav_host_fragment)
//                                navController.navigate(R.id.nav_productidetails)
//                            }


                        } catch (e: NullPointerException) {
                            e.printStackTrace()
                        } catch (e: TypeCastException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(
                    call: Call<FlipkartResponse>?,
                    t: Throwable?
                ) {
                    progressBarhome.visibility = View.GONE
                    Log.e(ContentValues.TAG, t.toString())
                }
            })


        } else {

            Toast.makeText(context, "Please Check your internet", Toast.LENGTH_LONG).show()
        }


    }

}
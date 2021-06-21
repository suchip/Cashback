package com.example.cashback.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.bookursalon.util.NetWorkConection
import com.example.cashback.APIClient_Main
import com.example.cashback.Adapter.offerProductsAmazon_Adapter
import com.example.cashback.Api
import com.example.cashback.R
import com.example.cashback.models.Amazon.AmazonResponse
import com.example.cashback.models.ImageModel
import com.example.cashback.roomdatabase.CartDatabase
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.viewpagerindicator.CirclePageIndicator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragmentAmazon : Fragment() {

    companion object {
        lateinit var cartDatabase: CartDatabase
    }

    lateinit var edit_search: EditText
    lateinit var favorites: ImageView
    lateinit var product_url: String
    lateinit var productslist_grid: GridView
    lateinit var progressBarhome: ProgressBar
    lateinit var sharedPreferences: SharedPreferences
    var adapter_amazon: offerProductsAmazon_Adapter? = null
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

        val root = inflater.inflate(R.layout.fragment_home_amazon, container, false)


        sharedPreferences =
            requireContext().getSharedPreferences(
                "loginprefs",
                Context.MODE_PRIVATE
            )

        product_url = sharedPreferences.getString("product_url", "").toString()
//        productimage = root.findViewById(R.id.productimage)
        productslist_grid = root.findViewById(R.id.productoffsers)
        progressBarhome = root.findViewById(R.id.progressBarhome)
        edit_search = root.findViewById(R.id.edit_search_amazon)
//        productprice = root.findViewById(R.id.productprice)
//        favorites = root.findViewById(R.id.favorites)
////        shopsliderimages = root.findViewById<ViewPager>(R.id.shopsliderimages)
////        shopcircleindicator =root. findViewById<CirclePageIndicator>(R.id.shopcircleindicator)
//
//        productprice = root.findViewById(R.id.productprice) as TextView
//        someTextView.setText(someString)
//        productprice.paintFlags = productprice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


//        shopsliderimages!!.clipToPadding = false
//        imageModelArrayList = ArrayList()
//        imageModelArrayList = populateList()

//        imageSliderView()


        edit_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                try {


                    adapter_amazon!!.filter.filter(charSequence.toString())

                    Log.e("newtext", charSequence.toString())
                    val editor = sharedPreferences.edit()
                    editor.putString("filtername", charSequence.toString())
                    editor.commit()
                    getSearchCardsResponse()
                } catch (e: java.lang.NullPointerException) {
                    e.printStackTrace()
                }
            }

            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                try {


                    adapter_amazon!!.filter.filter(editable.toString())

                    Log.e("newtext", editable.toString())
                    val editor = sharedPreferences.edit()
                    editor.putString("filtername", editable.toString())
                    editor.commit()

                } catch (e: java.lang.NullPointerException) {
                    e.printStackTrace()
                }
            }
        })

        cartDatabase =
            Room.databaseBuilder(activity as Activity, CartDatabase::class.java, "MyCart")
                .fallbackToDestructiveMigration() // this is only for testing,

                .allowMainThreadQueries().build()
        getofferdetails()
        return root
    }


    private fun getSearchCardsResponse() {

        if (context?.let { NetWorkConection.isNEtworkConnected(it as Activity) }!!) {

            //Set the Adapter to the RecyclerView//
            val token = sharedPreferences.getString("token", "")
            val filtername = sharedPreferences.getString("filtername", "")

//            latituteString = sharedPreferences.getString("latitude", "")
//            longtitudeString = sharedPreferences.getString("longitude", "")
            Log.e("filtername", filtername.toString())


            var apiServices = APIClient_Main.client.create(Api::class.java)


            val call =
                apiServices.amazon(filtername.toString())
            call.enqueue(object : Callback<List<AmazonResponse>> {
                @SuppressLint("WrongConstant")
                override fun onResponse(
                    call: Call<List<AmazonResponse>>,
                    response: Response<List<AmazonResponse>>
                ) {

                    progressBarhome.visibility = View.GONE
                    Log.e(ContentValues.TAG, response.toString())

                    if (response.isSuccessful) {

                        //Set the Adapter to the RecyclerView//

                        try {


                            val listOfcategories = response.body()

                            for (i in 0 until listOfcategories!!.size) {
                                val name: String = listOfcategories.get(i).toString()
                                Log.e("Names", "" + name)
                            }


                            Log.e("listcate", "" + response.body())


                            adapter_amazon = offerProductsAmazon_Adapter(
                                activity as Activity,
                                listOfcategories as ArrayList<AmazonResponse>
                            )

                            productslist_grid.adapter = adapter_amazon


//                            productslist_grid.adapter = offerProductsAmazon_Adapter(activity as Activity,
//                                listOfcategories as ArrayList<AmazonResponse>
//                            )


//                            productslist_grid.setOnItemClickListener { parent, view, position, id ->
//                                var product_id =
//                                    adapter_amazon.arrayListImage.get(position).asin
//                                Log.e("amazon prodcut id", "" + adapter_amazon.arrayListImage.get(position).asin)
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
                        } catch (e: JsonSyntaxException) {
                            e.printStackTrace()
                        } catch (e: IllegalStateException) {
                            e.printStackTrace()
                        }
                    }
                }


                override fun onFailure(
                    call: Call<List<AmazonResponse>>?,
                    t: Throwable?
                ) {
                    progressBarhome.visibility = View.GONE
                    Log.e(ContentValues.TAG, t.toString())
                }
            })


            Toast.makeText(context, "Please Check your internet", Toast.LENGTH_LONG).show()
        }

    }


//    @SuppressLint("UseRequireInsteadOfGet")
//    private fun imageSliderView() {
//        shopsliderimages!!.adapter =
//            ShopSlideImage_Adapter(activity as Activity, imageModelArrayList!!)
//        shopcircleindicator.setViewPager(shopsliderimages)
//        val density = resources.displayMetrics.density
//        //Set circle indicator radius
//        shopcircleindicator.radius = 4 * density
//        NUM_PAGES = imageModelArrayList!!.size
//        // Auto start of viewpager
//        val handler = Handler()
//        val Update = Runnable {
//            if (currentPage == NUM_PAGES) {
//                currentPage = 0
//            }
//            shopsliderimages!!.setCurrentItem(
//                currentPage++, true
//            )
//        }
//        val swipeTimer = Timer()
//        swipeTimer.schedule(object : TimerTask() {
//            override fun run() {
//                handler.post(Update)
//            }
//        }, 4000, 3000)
//        // Pager listener over indicator
//        shopcircleindicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageSelected(position: Int) {
//                currentPage = position
//            }
//
//            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {
//            }
//
//            override fun onPageScrollStateChanged(pos: Int) {
//            }
//        })
//    }

    private fun populateList(): ArrayList<ImageModel> {
        val list = ArrayList<ImageModel>()
        for (i in 0..2) {
            val imageModel = ImageModel()
            imageModel.setImage_drawables(myImageList[i])
            list.add(imageModel)
        }
        return list
    }


    private fun getofferdetails() {

        if (NetWorkConection.isNEtworkConnected(activity as Activity)) {

            //Set the Adapter to the RecyclerView//


            var apiServices = APIClient_Main.client.create(Api::class.java)

            val call =
                apiServices.amazon("deals")

            call.enqueue(object : Callback<List<AmazonResponse>> {
                @SuppressLint("WrongConstant")
                override fun onResponse(
                    call: Call<List<AmazonResponse>>,
                    response: Response<List<AmazonResponse>>
                ) {

                    progressBarhome.visibility = View.GONE
                    Log.e(ContentValues.TAG, response.toString())

                    if (response.isSuccessful) {

                        //Set the Adapter to the RecyclerView//

                        try {


                            val listOfcategories = response.body()

                            for (i in 0 until listOfcategories!!.size) {
                                val name: String = listOfcategories.get(i).toString()
                                Log.e("Names", "" + name)
                            }
                            val gson = GsonBuilder().create()


                            //val homedateList: List<AmazonMainResponse> = gson.fromJson(listOfcategories, Array<AmazonMainResponse>::class.java).toList()

                            Log.e("listcate", "" + response.body())



                            productslist_grid.adapter = offerProductsAmazon_Adapter(
                                activity as Activity,
                                listOfcategories as ArrayList<AmazonResponse>
                            )


//                            productslist_grid.setOnItemClickListener { parent, view, position, id ->
//                                var product_id =
//                                    adapter_amazon.arrayListImage.get(position).asin
//                                Log.e("amazon prodcut id", "" + adapter_amazon.arrayListImage.get(position).asin)
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
                        } catch (e: JsonSyntaxException) {
                            e.printStackTrace()
                        } catch (e: IllegalStateException) {
                            e.printStackTrace()
                        }
                    }
                }


                override fun onFailure(
                    call: Call<List<AmazonResponse>>?,
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
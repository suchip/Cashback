package com.example.cashback.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.example.cashback.Adapter.ShopSlideImage_Adapter
import com.example.cashback.MainActivity
import com.example.cashback.R
import com.example.cashback.models.ImageModel
import com.viewpagerindicator.CirclePageIndicator
import java.util.*
import kotlin.collections.ArrayList


class HomeFragmentSnapdeal : Fragment() {

//    lateinit var productimage: ImageView
    lateinit var edit_search: EditText
    lateinit var favorites: ImageView
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

        val root = inflater.inflate(R.layout.fragment_home, container, false)

//        productimage = root.findViewById(R.id.productimage)
//        edit_search = root.findViewById(R.id.edit_search)
//        favorites = root.findViewById(R.id.favorites)
//        shopsliderimages = root.findViewById<ViewPager>(R.id.shopsliderimages)
//        shopcircleindicator =root. findViewById<CirclePageIndicator>(R.id.shopcircleindicator)

//        someTextView.setText(someString)
//        edit_search.setOnClickListener {
//
//
//          startActivity(Intent(activity,MainActivity::class.java))
//        }

//        productimage.setOnClickListener {
//
//
//            val navController =
//                Navigation.findNavController(activity as Activity, R.id.nav_host_fragment)
//            navController.navigate(R.id.nav_productidetails)
//        }
//        shopsliderimages!!.clipToPadding = false
//        imageModelArrayList = ArrayList()
//        imageModelArrayList = populateList()

//        imageSliderView()
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

}
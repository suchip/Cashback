package com.example.cashback.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.bookursalon.util.NetWorkConection
import com.example.cashback.APIClient
import com.example.cashback.Adapter.offerProductsflipkart_Adapter
import com.example.cashback.Api
import com.example.cashback.R
import com.example.cashback.models.Flipkart.FlipkartCategorymainResponse
import com.example.cashback.models.Flipkart.FlipkartlistResponse
import com.example.cashback.models.Flipkart.FlipkartofferResponse
import com.example.cashback.models.ImageModel
import com.viewpagerindicator.CirclePageIndicator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {


    lateinit var productoffsers: GridView
    lateinit var progressBarhome: ProgressBar
    lateinit var homeslectedlist: String
    lateinit var hometopdeals_list: String
    lateinit var sharedPreferences: SharedPreferences
    lateinit var food_nutrition: String
    lateinit var televisions: String
    lateinit var landline_phones: String
    lateinit var tv_video_accessories: String
    lateinit var software: String
    lateinit var computer_storage: String
    lateinit var fragrances: String
    lateinit var network_components: String
    lateinit var e_learning: String
    lateinit var video_players: String
    lateinit var mens_clothing: String
    lateinit var music_movies_posters: String
    lateinit var furniture: String
    lateinit var bags_wallets_belts: String
    lateinit var kids_clothing: String
    lateinit var kids_footwear: String
    lateinit var pet_supplies: String
    lateinit var mens_footwear: String
    lateinit var air_coolers: String
    lateinit var home_entertainment: String
    lateinit var watches: String
    lateinit var sunglasses: String
    lateinit var eyewear: String
    lateinit var computer_components: String
    lateinit var laptop_accessories: String
    lateinit var womens_clothing: String
    lateinit var mobile_accessories: String
    lateinit var camera_accessories: String
    lateinit var air_conditioners: String
    lateinit var luggage_travel: String
    lateinit var automotive: String
    lateinit var tablets: String
    lateinit var refrigerator: String
    lateinit var home_improvement_tools: String
    lateinit var computer_peripherals: String
    lateinit var stationery_office_supplies: String
    lateinit var sports_fitness: String
    lateinit var baby_care: String
    lateinit var cameras: String
    lateinit var wearable_smart_devices: String
    lateinit var audio_players: String
    lateinit var grooming_beauty_wellness: String
    lateinit var tablet_accessories: String
    lateinit var kitchen_appliances: String
    lateinit var microwave_ovens: String
    lateinit var laptops: String
    lateinit var washing_machine: String
    lateinit var gaming: String
    lateinit var toys: String
    lateinit var home_appliances: String
    lateinit var home_decor_and_festive_needs: String
    lateinit var home_and_kitchen_needs: String
    lateinit var jewellery: String
    lateinit var home_furnishing: String
    lateinit var desktops: String
    lateinit var womens_footwear: String
    lateinit var household_supplies: String

    lateinit var adapter_home: offerProductsflipkart_Adapter
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
        productoffsers = root.findViewById(R.id.productoffsers)
        progressBarhome = root.findViewById(R.id.progressBarhome) as ProgressBar

//

        sharedPreferences =
            requireActivity().getSharedPreferences(
                "loginprefs",
                Context.MODE_PRIVATE
            )
//
        getflipkartcategoryresponse()
        getflipkartofferdetails()
        return root
    }

    private fun getflipkartofferdetails() {

        if (NetWorkConection.isNEtworkConnected(activity as Activity)) {

            //Set the Adapter to the RecyclerView//


            var apiServices = APIClient.client.create(Api::class.java)

            val call =
                apiServices.getofferdetails("makelabsi", "a0e4fa1cb2c04dbdae7aba34ad2f42ab")

            call.enqueue(object : Callback<FlipkartofferResponse> {
                @SuppressLint("WrongConstant")
                override fun onResponse(
                    call: Call<FlipkartofferResponse>,
                    response: Response<FlipkartofferResponse>
                ) {

                    progressBarhome.visibility = View.GONE
                    Log.e(ContentValues.TAG, response.toString())

                    if (response.isSuccessful) {

                        //Set the Adapter to the RecyclerView//

                        try {


                            val listOfcategories = response.body()?.allOffersList

                            //Log.e("listcate", "" + listOfcategories)
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
                                offerProductsflipkart_Adapter(
                                    activity as Activity,
                                    listOfcategories as ArrayList<FlipkartlistResponse>
                                )

                            productoffsers.adapter = adapter_home


                            productoffsers.setOnItemClickListener { adapterView, parent, position, l ->

                                var product_url = adapter_home.arrayListImage.get(position).url
                                var product_desc =
                                    adapter_home.arrayListImage.get(position).description
                                var product_name = adapter_home.arrayListImage.get(position).name
//                                var sub_categories_id =
//                                    adapter.arrayListImage.get(position).sub_categories_id
//                                val editor = sharedPreferences.edit()
//                                editor.putString("product_id", product_id.toString())
//                                editor.putString("sub_categories_id", sub_categories_id.toString())
//                                editor.putInt("product_indetails", 14)
//                                editor.commit()
//


                                val uri: Uri =
                                    Uri.parse(product_url)
                                val protocol: String = uri.scheme!!
                                val server: String = uri.authority!!
                                val path: String = uri.path!!
                                val args: Set<String> =
                                    uri.queryParameterNames
//                            val limit: String = uri.getQueryParameter("limit")!!

                                Log.e("path", "" + path)
                                Log.e("protocol", "" + protocol)



                                homeslectedlist =
                                    path.replace("/", "")
                                        .replace(",", ",")
                                        .replace("/", ",")
                                        .replace("pr", "")
                                        .replace("~cs-3ydw0n6cct", "")

                                Log.e("homeslectedlist", "" + homeslectedlist)


                                getflipkartcategoryresponse()
                            }

                        } catch (e: NullPointerException) {
                            e.printStackTrace()
                        } catch (e: TypeCastException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(
                    call: Call<FlipkartofferResponse>?,
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

    private fun getflipkartcategoryresponse() {

        if (NetWorkConection.isNEtworkConnected(activity as Activity)) {

            //Set the Adapter to the RecyclerView//


            var apiServices = APIClient.client.create(Api::class.java)

            val call =
                apiServices.getallcategorieslist("makelabsi", "a0e4fa1cb2c04dbdae7aba34ad2f42ab")

            call.enqueue(object : Callback<FlipkartCategorymainResponse> {
                @SuppressLint("WrongConstant")
                override fun onResponse(
                    call: Call<FlipkartCategorymainResponse>,
                    response: Response<FlipkartCategorymainResponse>
                ) {

                    progressBarhome.visibility = View.GONE
                    Log.e(ContentValues.TAG, response.toString())

                    if (response.isSuccessful) {

                        //Set the Adapter to the RecyclerView//

                        try {


                            val listOfcategories =
                                response.body()?.apiGroups!!.affiliate.apiListings.air_conditioners.availableVariants.variants.get
                            hometopdeals_list =
                                response.body()?.apiGroups!!.affiliate.apiListings.mobiles.apiName
                            furniture =
                                response.body()?.apiGroups!!.affiliate.apiListings.furniture.apiName
                            food_nutrition =
                                response.body()?.apiGroups!!.affiliate.apiListings.food_nutrition.apiName
                            televisions =
                                response.body()?.apiGroups!!.affiliate.apiListings.televisions.apiName
                            landline_phones =
                                response.body()?.apiGroups!!.affiliate.apiListings.landline_phones.apiName
                            tv_video_accessories =
                                response.body()?.apiGroups!!.affiliate.apiListings.tv_video_accessories.apiName
                            software =
                                response.body()?.apiGroups!!.affiliate.apiListings.software.apiName
                            computer_storage =
                                response.body()?.apiGroups!!.affiliate.apiListings.computer_storage.apiName
                            fragrances =
                                response.body()?.apiGroups!!.affiliate.apiListings.fragrances.apiName
                            network_components =
                                response.body()?.apiGroups!!.affiliate.apiListings.network_components.apiName
                            e_learning =
                                response.body()?.apiGroups!!.affiliate.apiListings.e_learning.apiName
                            video_players =
                                response.body()?.apiGroups!!.affiliate.apiListings.video_players.apiName
                            mens_clothing =
                                response.body()?.apiGroups!!.affiliate.apiListings.mens_clothing.apiName
                            music_movies_posters =
                                response.body()?.apiGroups!!.affiliate.apiListings.music_movies_posters.apiName
                            kids_clothing =
                                response.body()?.apiGroups!!.affiliate.apiListings.mens_clothing.apiName
                            bags_wallets_belts =
                                response.body()?.apiGroups!!.affiliate.apiListings.music_movies_posters.apiName
                            kids_footwear =
                                response.body()?.apiGroups!!.affiliate.apiListings.kids_footwear.apiName
                            pet_supplies =
                                response.body()?.apiGroups!!.affiliate.apiListings.pet_supplies.apiName
                            mens_footwear =
                                response.body()?.apiGroups!!.affiliate.apiListings.mens_footwear.apiName
                            air_coolers =
                                response.body()?.apiGroups!!.affiliate.apiListings.air_coolers.apiName
                            home_entertainment =
                                response.body()?.apiGroups!!.affiliate.apiListings.home_entertainment.apiName
                            watches =
                                response.body()?.apiGroups!!.affiliate.apiListings.watches.apiName
                            sunglasses =
                                response.body()?.apiGroups!!.affiliate.apiListings.sunglasses.apiName
                            eyewear =
                                response.body()?.apiGroups!!.affiliate.apiListings.eyewear.apiName
                            computer_components =
                                response.body()?.apiGroups!!.affiliate.apiListings.computer_components.apiName
                            laptop_accessories =
                                response.body()?.apiGroups!!.affiliate.apiListings.laptop_accessories.apiName
                            womens_clothing =
                                response.body()?.apiGroups!!.affiliate.apiListings.womens_clothing.apiName
                            mobile_accessories =
                                response.body()?.apiGroups!!.affiliate.apiListings.mobile_accessories.apiName
                            camera_accessories =
                                response.body()?.apiGroups!!.affiliate.apiListings.camera_accessories.apiName
                            air_conditioners =
                                response.body()?.apiGroups!!.affiliate.apiListings.air_conditioners.apiName
                            luggage_travel =
                                response.body()?.apiGroups!!.affiliate.apiListings.luggage_travel.apiName
                            automotive =
                                response.body()?.apiGroups!!.affiliate.apiListings.automotive.apiName
                            tablets =
                                response.body()?.apiGroups!!.affiliate.apiListings.tablets.apiName


                            refrigerator =
                                response.body()?.apiGroups!!.affiliate.apiListings.refrigerator.availableVariants.variants.get

                            uri =
                                Uri.parse(refrigerator)
                            val protocol: String = uri.scheme!!
                            val server: String = uri.authority!!
                            dynamic_product_url = uri.path!!
                            val args: Set<String> =
                                uri.queryParameterNames


                            dynamic_param =
                                dynamic_product_url.replace(
                                    "/affiliate/1.0/feeds/makelabsi/category/",
                                    ""
                                )
                                    .replace("-", "")
                                    .replace(".json", "")

                            Log.e("fridge path", "" + dynamic_param)

                            home_improvement_tools =
                                response.body()?.apiGroups!!.affiliate.apiListings.home_improvement_tools.apiName
                            computer_peripherals =
                                response.body()?.apiGroups!!.affiliate.apiListings.computer_peripherals.apiName
                            stationery_office_supplies =
                                response.body()?.apiGroups!!.affiliate.apiListings.stationery_office_supplies.apiName
                            sports_fitness =
                                response.body()?.apiGroups!!.affiliate.apiListings.sports_fitness.apiName
                            baby_care =
                                response.body()?.apiGroups!!.affiliate.apiListings.baby_care.apiName
                            cameras =
                                response.body()?.apiGroups!!.affiliate.apiListings.cameras.apiName
                            sports_fitness =
                                response.body()?.apiGroups!!.affiliate.apiListings.sports_fitness.apiName
                            wearable_smart_devices =
                                response.body()?.apiGroups!!.affiliate.apiListings.wearable_smart_devices.apiName
                            audio_players =
                                response.body()?.apiGroups!!.affiliate.apiListings.audio_players.apiName
                            grooming_beauty_wellness =
                                response.body()?.apiGroups!!.affiliate.apiListings.grooming_beauty_wellness.apiName
                            tablet_accessories =
                                response.body()?.apiGroups!!.affiliate.apiListings.tablet_accessories.apiName
                            kitchen_appliances =
                                response.body()?.apiGroups!!.affiliate.apiListings.kitchen_appliances.apiName
                            microwave_ovens =
                                response.body()?.apiGroups!!.affiliate.apiListings.microwave_ovens.apiName
                            laptops =
                                response.body()?.apiGroups!!.affiliate.apiListings.laptops.apiName
                            washing_machine =
                                response.body()?.apiGroups!!.affiliate.apiListings.washing_machine.apiName
                            gaming =
                                response.body()?.apiGroups!!.affiliate.apiListings.gaming.apiName
                            toys = response.body()?.apiGroups!!.affiliate.apiListings.toys.apiName
                            home_appliances =
                                response.body()?.apiGroups!!.affiliate.apiListings.home_appliances.apiName
                            home_decor_and_festive_needs =
                                response.body()?.apiGroups!!.affiliate.apiListings.home_decor_and_festive_needs.apiName
                            home_and_kitchen_needs =
                                response.body()?.apiGroups!!.affiliate.apiListings.home_and_kitchen_needs.apiName
                            jewellery =
                                response.body()?.apiGroups!!.affiliate.apiListings.jewellery.apiName
                            home_furnishing =
                                response.body()?.apiGroups!!.affiliate.apiListings.home_furnishing.apiName
                            home_appliances =
                                response.body()?.apiGroups!!.affiliate.apiListings.home_appliances.apiName
                            desktops =
                                response.body()?.apiGroups!!.affiliate.apiListings.desktops.apiName
                            womens_footwear =
                                response.body()?.apiGroups!!.affiliate.apiListings.womens_footwear.apiName
                            household_supplies =
                                response.body()?.apiGroups!!.affiliate.apiListings.household_supplies.apiName


                            if (homeslectedlist == "sports") {
                                homeslectedlist = "sports_fitness"
                            }

                            if (homeslectedlist == "bags-wallets-belts~cs-yqpnjob9z8") {
                                homeslectedlist = "bags_wallets_belts"


                            }
                            if (homeslectedlist == "beauty-and-grooming") {
                                homeslectedlist = "grooming_beauty_wellness"

                            }

                            if (homeslectedlist == "mobiles~asus-zenfone-max-o-m2") {
                                homeslectedlist = "mobiles"

                            }
                            if (homeslectedlist == "mobiles~redmi-note-5-o") {
                                homeslectedlist = "mobiles"
                            }
                            if (homeslectedlist == "mobiles~honor-9n") {
                                homeslectedlist = "mobiles"
                            }
                            if (homeslectedlist == "redmi-note-6-o-blue-64-gbpitmd0ee82dbf1830") {
                                homeslectedlist = "mobiles"
                            }
                            Log.e("homelist name", "" + hometopdeals_list)
                            if (homeslectedlist.equals(hometopdeals_list) || (homeslectedlist == hometopdeals_list)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.mobiles.availableVariants.variants.get

                                Log.e("homelist producturl", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()


                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)

                            } else if (homeslectedlist.equals(microwave_ovens) || (homeslectedlist == microwave_ovens)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.microwave_ovens.availableVariants.variants.get

                                Log.e("kitchen_appliances", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(household_supplies) || (homeslectedlist == household_supplies)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.household_supplies.availableVariants.variants.get

                                Log.e("household_supplies", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(womens_footwear) || (homeslectedlist == womens_footwear)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.womens_footwear.availableVariants.variants.get

                                Log.e("womens_footwear", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(desktops) || (homeslectedlist == desktops)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.desktops.availableVariants.variants.get

                                Log.e("desktops", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(home_furnishing) || (homeslectedlist == home_furnishing)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.home_furnishing.availableVariants.variants.get

                                Log.e("home_furnishing", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(jewellery) || (homeslectedlist == jewellery)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.jewellery.availableVariants.variants.get

                                Log.e("jewellery", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(home_and_kitchen_needs) || (homeslectedlist == home_and_kitchen_needs)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.home_and_kitchen_needs.availableVariants.variants.get

                                Log.e("home_and_kitchen_needs", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(home_decor_and_festive_needs) || (homeslectedlist == home_decor_and_festive_needs)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.home_decor_and_festive_needs.availableVariants.variants.get

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(home_appliances) || (homeslectedlist == home_appliances)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.home_appliances.availableVariants.variants.get

                                Log.e("home_appliances", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(toys) || (homeslectedlist == toys)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.toys.availableVariants.variants.get

                                Log.e("toys", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(gaming) || (homeslectedlist == gaming)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.gaming.availableVariants.variants.get

                                Log.e("gaming", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(washing_machine) || (homeslectedlist == washing_machine)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.washing_machine.availableVariants.variants.get

                                Log.e("washing_machine", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(laptops) || (homeslectedlist == laptops)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.laptops.availableVariants.variants.get

                                Log.e("laptops", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(kitchen_appliances) || (homeslectedlist == kitchen_appliances)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.kitchen_appliances.availableVariants.variants.get

                                Log.e("kitchen_appliances", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(kitchen_appliances) || (homeslectedlist == kitchen_appliances)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.kitchen_appliances.availableVariants.variants.get

                                Log.e("kitchen_appliances", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(tablet_accessories) || (homeslectedlist == tablet_accessories)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.tablet_accessories.availableVariants.variants.get

                                Log.e("tablet_accessories", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(grooming_beauty_wellness) || (homeslectedlist == grooming_beauty_wellness)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.grooming_beauty_wellness.availableVariants.variants.get


                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(audio_players) || (homeslectedlist == audio_players)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.audio_players.availableVariants.variants.get

                                Log.e("audio_players", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(wearable_smart_devices) || (homeslectedlist == wearable_smart_devices)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.wearable_smart_devices.availableVariants.variants.get

                                Log.e("wearable_smart_devices", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(cameras) || (homeslectedlist == cameras)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.cameras.availableVariants.variants.get

                                Log.e("cameras", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(baby_care) || (homeslectedlist == baby_care)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.baby_care.availableVariants.variants.get

                                Log.e("baby_care", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(sports_fitness) || (homeslectedlist == sports_fitness)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.sports_fitness.availableVariants.variants.get

                                Log.e("sports_fitness", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(stationery_office_supplies) || (homeslectedlist == stationery_office_supplies)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.stationery_office_supplies.availableVariants.variants.get


                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(computer_peripherals) || (homeslectedlist == computer_peripherals)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.computer_peripherals.availableVariants.variants.get

                                Log.e("computer_peripherals", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(home_improvement_tools) || (homeslectedlist == home_improvement_tools)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.home_improvement_tools.availableVariants.variants.get

                                Log.e("home_improvement_tools", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(dynamic_param) || (homeslectedlist == dynamic_param)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.refrigerator.availableVariants.variants.get

                                Log.e("refrigerator", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(tablets) || (homeslectedlist == tablets)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.tablets.availableVariants.variants.get

                                Log.e("tablets", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(automotive) || (homeslectedlist == automotive)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.automotive.availableVariants.variants.get

                                Log.e("automotive", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(luggage_travel) || (homeslectedlist == luggage_travel)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.luggage_travel.availableVariants.variants.get

                                Log.e("luggage_travel", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(air_conditioners) || (homeslectedlist == air_conditioners)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.air_conditioners.availableVariants.variants.get

                                Log.e("air_conditioners", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(camera_accessories) || (homeslectedlist == camera_accessories)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.camera_accessories.availableVariants.variants.get

                                Log.e("camera_accessories", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(mobile_accessories) || (homeslectedlist == mobile_accessories)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.womens_clothing.availableVariants.variants.get

                                Log.e("mobile_accessories", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(womens_clothing) || (homeslectedlist == womens_clothing)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.womens_clothing.availableVariants.variants.get

                                Log.e("womens_clothing", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(laptop_accessories) || (homeslectedlist == laptop_accessories)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.computer_components.availableVariants.variants.get

                                Log.e("laptop_accessories", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(computer_components) || (homeslectedlist == computer_components)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.computer_components.availableVariants.variants.get

                                Log.e("computer_components", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(eyewear) || (homeslectedlist == eyewear)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.eyewear.availableVariants.variants.get

                                Log.e("eyewear", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(sunglasses) || (homeslectedlist == sunglasses)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.sunglasses.availableVariants.variants.get

                                Log.e("sunglasses", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(watches) || (homeslectedlist == watches)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.watches.availableVariants.variants.get

                                Log.e("watches", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(home_entertainment) || (homeslectedlist == home_entertainment)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.home_entertainment.availableVariants.variants.get

                                Log.e("home_entertainment", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(air_coolers) || (homeslectedlist == air_coolers)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.air_coolers.availableVariants.variants.get

                                Log.e("air_coolers", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(mens_footwear) || (homeslectedlist == mens_footwear)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.mens_footwear.availableVariants.variants.get

                                Log.e("mens_footwear", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(pet_supplies) || (homeslectedlist == pet_supplies)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.pet_supplies.availableVariants.variants.get

                                Log.e("pet_supplies", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(kids_footwear) || (homeslectedlist == kids_footwear)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.kids_footwear.availableVariants.variants.get

                                Log.e("kids_footwear", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(kids_clothing) || (homeslectedlist == kids_clothing)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.kids_clothing.availableVariants.variants.get

                                Log.e("kids_clothing", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(bags_wallets_belts) || (homeslectedlist == bags_wallets_belts)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.bags_wallets_belts.availableVariants.variants.get

                                Log.e("bags_wallets_belts", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(music_movies_posters) || (homeslectedlist == music_movies_posters)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.music_movies_posters.availableVariants.variants.get

                                Log.e("music_movies_posters", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(mens_clothing) || (homeslectedlist == mens_clothing)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.mens_clothing.availableVariants.variants.get

                                Log.e("mens_clothing", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(video_players) || (homeslectedlist == video_players)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.video_players.availableVariants.variants.get

                                Log.e("video_players", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(e_learning) || (homeslectedlist == e_learning)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.e_learning.availableVariants.variants.get

                                Log.e("e_learning", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(network_components) || (homeslectedlist == network_components)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.network_components.availableVariants.variants.get

                                Log.e("network_components", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(fragrances) || (homeslectedlist == fragrances)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.fragrances.availableVariants.variants.get

                                Log.e("fragrances", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(computer_storage) || (homeslectedlist == computer_storage)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.computer_storage.availableVariants.variants.get

                                Log.e("computer_storage", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(software) || (homeslectedlist == software)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.software.availableVariants.variants.get

                                Log.e("software", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(tv_video_accessories) || (homeslectedlist == tv_video_accessories)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.tv_video_accessories.availableVariants.variants.get

                                Log.e("tv_video_accessories", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(landline_phones) || (homeslectedlist == landline_phones)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.landline_phones.availableVariants.variants.get

                                Log.e("landline_phones", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(televisions) || (homeslectedlist == televisions)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.televisions.availableVariants.variants.get

                                Log.e("televisions", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(food_nutrition) || (homeslectedlist == food_nutrition)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.food_nutrition.availableVariants.variants.get

                                Log.e("food_nutrition", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            } else if (homeslectedlist.equals(furniture) || (homeslectedlist == furniture)) {
                                val producturl =
                                    response.body()?.apiGroups!!.affiliate.apiListings.furniture.availableVariants.variants.get

                                Log.e("furniture", "" + producturl)

                                val editor = sharedPreferences.edit()
                                editor.putString("product_url", producturl)

                                editor.commit()

                                val navController =
                                    Navigation.findNavController(
                                        context as Activity,
                                        R.id.nav_host_fragment
                                    )
                                navController.navigate(R.id.nav_flipkart)
                            }


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


//                            gvusercategory.setOnItemClickListener { adapterView, parent, position, l ->
//
//                                var product_id = adapter.arrayListImage.get(position).products_id
//                                var sub_categories_id =
//                                    adapter.arrayListImage.get(position).sub_categories_id
//                                val editor = sharedPreferences.edit()
//                                editor.putString("product_id", product_id.toString())
//                                editor.putString("sub_categories_id", sub_categories_id.toString())
//                                editor.putInt("product_indetails", 14)
//                                editor.commit()
////
//
//                            }
//                        }
//                        val itemDecorator2 = VerticalSpacingItemDecorator(20)
//                        gvsportslist.addItemDecoration(itemDecorator2)

                        } catch (e: NullPointerException) {
                            e.printStackTrace()
                        } catch (e: TypeCastException) {
                            e.printStackTrace()
                        } catch (e: UninitializedPropertyAccessException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(
                    call: Call<FlipkartCategorymainResponse>?,
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


    companion object {

        lateinit var uri: Uri
        lateinit var dynamic_product_url: String
        lateinit var dynamic_param: String
    }
}
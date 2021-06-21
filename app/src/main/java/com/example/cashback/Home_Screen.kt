package com.example.cashback

import android.content.*
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bookursalon.util.NetWorkConection
import com.example.cashback.models.GetCartResponse
import com.example.cashback.models.GetUserResponse
import com.example.cashback.roomdatabase.CartDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home_Screen : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var homesharedPreferences: SharedPreferences
    lateinit var userprofileimage: ImageView
    lateinit var userprofilename: TextView


    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var bottom_nav_view: BottomNavigationView
    lateinit var navController: NavController
    lateinit var navigationView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home__screen)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        bottom_nav_view = findViewById(R.id.navigation)

        homesharedPreferences = getSharedPreferences("loginprefs", Context.MODE_PRIVATE)

        setupNavigation()

        cartDatabase = Room.databaseBuilder(this, CartDatabase::class.java, "MyCart")
            .fallbackToDestructiveMigration() // this is only for testing,
            .allowMainThreadQueries().build()

        //updatacartcount()


        val navController = findNavController(R.id.nav_host_fragment)
        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_home -> {


                        val navController =
                            Navigation.findNavController(this, R.id.nav_host_fragment)
                        navController.navigate(R.id.nav_home)

                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.nav_amazon -> {


                        val navController =
                            Navigation.findNavController(this, R.id.nav_amazon)
                        navController.navigate(R.id.nav_amazon)

                        return@OnNavigationItemSelectedListener true
                    }


                }
                false
            }
        bottom_nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_flipkart, R.id.nav_amazon
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottom_nav_view.setupWithNavController(navController)
        getamazoncartlist()

    }

    override fun onResume() {
        super.onResume()
        getamazoncartlist()
    }

    override fun onStart() {
        super.onStart()
        getamazoncartlist()
    }

    var mMasage: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val cartcount_stng = intent.getStringExtra("cartcount")

            Log.e("countt", cartcount_stng.toString())


        }
    }

    private fun updatacartcount() {
        //\getamazoncartlist()

//        runOnUiThread(Runnable {
//            if (cartDatabase.cartDao()
//                !!.countCart() === 0
//            ) cartcount.visibility = View.INVISIBLE else {
//                cartcount.visibility = View.VISIBLE
//                cartcount.text = java.lang.String.valueOf(
//                    cartDatabase.cartDao()!!.countCart()
//                )
//                Log.e("cartcount", "" + cartDatabase.cartDao()!!.countCart().toString())
//            }
//        })
    }


    private fun getamazoncartlist() {

        if (NetWorkConection.isNEtworkConnected(this)) {

            try {
                val token = homesharedPreferences.getString("token", "")

                var apiServices = APIClient_Main.client.create(Api::class.java)

                Log.e("API : ", "" + apiServices)


                val call =
                    apiServices.addcart_list("Token $token")


                Log.e("API Cards : ", "" + call)


                call.enqueue(object : retrofit2.Callback<GetCartResponse> {


                    override fun onResponse(
                        call: Call<GetCartResponse>,
                        response: Response<GetCartResponse>
                    ) {
                        val loginResponse = response.body()

                        Log.e(" matches Response", "" + response.body())

                        if (response.isSuccessful) {


                            count = response.body()!!.count.toString()


                            Log.e("cartcount res", "" + count)


                        }
                    }

                    override fun onFailure(call: Call<GetCartResponse>, t: Throwable) {


                        Log.e(" response", t.toString())


                    }
                })

            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: UninitializedPropertyAccessException) {
                e.printStackTrace()
            }
        } else {

            Toast.makeText(this, "Please Check your internet", Toast.LENGTH_LONG).show()
        }

    }

    // Setting Up One Time Navigation
    private fun setupNavigation() {

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        drawerLayout = findViewById(R.id.drawer_layout)

        navigationView = findViewById(R.id.nav_view)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        NavigationUI.setupWithNavController(navigationView, navController)

        navigationView.setNavigationItemSelectedListener(this)

        val headerView: View = navigationView.getHeaderView(0)

        userprofileimage = headerView.findViewById(R.id.userprofileimage)

        //getting the headerview username from xml


        userprofilename = headerView.findViewById(R.id.userprofilename)

        getusersProfile()


    }

    companion object {
        lateinit var cartcount: TextView

        lateinit var cartDatabase: CartDatabase
        lateinit var count: String
    }


    private fun getusersProfile() {

        try {

            if (NetWorkConection.isNEtworkConnected(this)) {


                //Set the Adapter to the RecyclerView//

                var apiServices = APIClient_Main.client.create(Api::class.java)

                Log.e("API : ", "" + apiServices)

                var token = homesharedPreferences.getString("token", "")

                Log.e("token : ", "" + token)

                val call = apiServices.get_profile("Token $token")
                Log.e("API Categories : ", "" + call)
//


                call.enqueue(object : Callback<GetUserResponse> {
                    override fun onResponse(
                        call: Call<GetUserResponse>,
                        response: Response<GetUserResponse>
                    ) {

                        if (response.isSuccessful) {

                            try {


                                Log.e("user response", response.toString())

                                Log.e("get user response: ", "" + response.body()!!.profile_pic)

                                val pfname: String? = response.body()!!.first_name
                                val pfemail: String? = response.body()!!.email
//                                val pfcity: String? = response.body()!!.ad
                                val pfmobile = response.body()!!.mobile_number
                                val pfimage: String? = response.body()!!.profile_pic


                                userprofilename.text = pfname

                                Glide.with(
                                    applicationContext
                                ).load(pfimage)
                                    .placeholder(R.drawable.ic_baseline_account_circle_24)
                                    .apply(RequestOptions().centerCrop())

                                    .into(userprofileimage)
                            } catch (e: NullPointerException) {
                                e.printStackTrace()
                            }
                        } else {
                            try {
                                Toast.makeText(this@Home_Screen, "No Data", Toast.LENGTH_LONG)
                                    .show()

                            } catch (e: NullPointerException) {
                                e.printStackTrace()
                            }


                        }
                    }

                    override fun onFailure(call: Call<GetUserResponse>, t: Throwable?) {
                        Log.e("response", t.toString())
                    }
                })


            } else {


                Toast.makeText(this, "Please Check your internet", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "" + e.message, Toast.LENGTH_LONG).show()

        } catch (e1: JSONException) {
            e1.printStackTrace()
        }


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.cart__menu, menu)

        val notificaitons =
            menu.findItem(R.id.cart).actionView

        cartcount =
            notificaitons.findViewById<View>(R.id.cartcount) as TextView
        val iconButtonMessages: ImageView =
            notificaitons.findViewById<View>(R.id.cart_btn) as ImageView

        iconButtonMessages.setOnClickListener {
            val navController =
                Navigation.findNavController(this, R.id.nav_host_fragment)
            navController.navigate(R.id.nav_cart)

        }


        if (NetWorkConection.isNEtworkConnected(this)) {

            try {
                val token = homesharedPreferences.getString("token", "")

                var apiServices = APIClient_Main.client.create(Api::class.java)

                Log.e("API : ", "" + apiServices)


                val call =
                    apiServices.addcart_list("Token $token")


                Log.e("API Cards : ", "" + call)


                call.enqueue(object : retrofit2.Callback<GetCartResponse> {


                    override fun onResponse(
                        call: Call<GetCartResponse>,
                        response: Response<GetCartResponse>
                    ) {
                        val loginResponse = response.body()

                        Log.e(" matches Response", "" + response.body())

                        if (response.isSuccessful) {


                            count = response.body()!!.count.toString()
                            if (count.equals(0)) {
                                cartcount.visibility = View.INVISIBLE
                            } else {
                                cartcount.visibility = View.VISIBLE
                                cartcount.text = count


                            }

                        }
                    }

                    override fun onFailure(call: Call<GetCartResponse>, t: Throwable) {


                        Log.e(" response", t.toString())


                    }
                })

            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: UninitializedPropertyAccessException) {
                e.printStackTrace()
            }
        } else {

            Toast.makeText(this, "Please Check your internet", Toast.LENGTH_LONG).show()
        }


        LocalBroadcastManager.getInstance(this).registerReceiver(mMasage, IntentFilter("mymsg"))

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.cart -> {

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        drawerLayout.closeDrawers()

        val id = item.itemId

        when (id) {


            R.id.nav_profile -> navController.navigate(R.id.nav_profile)


        }
        return true

    }
}
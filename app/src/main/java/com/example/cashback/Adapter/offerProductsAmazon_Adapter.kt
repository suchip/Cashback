package com.example.cashback.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.Navigation
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.bookursalon.util.NetWorkConection
import com.example.cashback.APIClient_Main
import com.example.cashback.Api
import com.example.cashback.R
import com.example.cashback.models.AddCartResponse
import com.example.cashback.models.Amazon.AmazonCashbackResponse
import com.example.cashback.models.Amazon.AmazonResponse
import com.example.cashback.roomdatabase.Cart
import com.example.cashback.roomdatabase.CartDatabase
import com.google.gson.JsonSyntaxException
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class offerProductsAmazon_Adapter(
    context: Context,
    arrayListImage: ArrayList<AmazonResponse>
) :
    BaseAdapter(), Filterable {

    //Passing Values to Local Variables

    var context = context
    var arrayListImage = arrayListImage
    private lateinit var contactListFiltered: List<AmazonResponse>


    //Auto Generated Method
    @SuppressLint("CheckResult")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        var myView = convertView
        var holder: ViewHolder


        if (myView == null) {

            //If our View is Null than we Inflater view using Layout Inflater

            val mInflater = (context as Activity).layoutInflater

            //Inflating our grid_item.
            myView = mInflater.inflate(R.layout.homecard_adapter, parent, false)

            //Create Object of ViewHolder Class and set our View to it

            holder = ViewHolder()


            //Find view By Id For all our Widget taken in grid_item.

            /*Here !! are use for non-null asserted two prevent From null.
             you can also use Only Safe (?.)

            */


            holder.brandimage = myView!!.findViewById<ImageView>(R.id.brandimage) as ImageView
            holder.favorites = myView.findViewById<ImageView>(R.id.favorites) as ImageView
            holder.productimage = myView.findViewById<ImageView>(R.id.productimage) as ImageView
            holder.productname = myView.findViewById<TextView>(R.id.productname) as TextView
            holder.offerprice = myView.findViewById<TextView>(R.id.offerprice) as TextView
            holder.actuallsaleprice =
                myView.findViewById<TextView>(R.id.actuallsaleprice) as TextView
            holder.ratingcount = myView.findViewById<TextView>(R.id.ratingcount) as TextView
            holder.cashbackprice = myView.findViewById<TextView>(R.id.cashbackprice) as TextView
            holder.addtocart = myView.findViewById<Button>(R.id.addtocart) as Button
            holder.amazonitems_ll =
                myView.findViewById<LinearLayout>(R.id.flipkartitems_ll) as LinearLayout


            //Set A Tag to Identify our view.
            myView.tag = holder


        } else {


            //If Our View in not Null than Just get View using Tag and pass to holder Object.

            holder = myView.tag as ViewHolder

            try {


                //Setting name to TextView by position
                holder.productname!!.text =
                    arrayListImage.get(position).browse_node_info.browse_nodes[0].context_free_name
                holder.actuallsaleprice!!.text =
                    arrayListImage.get(position).offers.listings[0].saving_basis.display_amount
                holder.offerprice!!.text =
                    "\u20A8" + arrayListImage.get(position).offers.listings[0].price.amount.toString()
                holder.ratingcount!!.text =
                    arrayListImage.get(position).offers.listings[0].price.savings.percentage.toString() + " %"

                holder.actuallsaleprice!!.paintFlags =
                    holder.actuallsaleprice!!.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


                val image =
                    arrayListImage.get(position).images.primary.medium.url


                holder.productimage?.let { it1 ->
                    Glide.with(context).load(image)
                        .error(R.drawable.ic_flipkart)

                        .into(it1)

                }

                //cashback percentage


                try {

                    if (NetWorkConection.isNEtworkConnected(context as Activity)) {

                        val sharedPreferences =
                            context.getSharedPreferences(
                                "loginprefs",
                                Context.MODE_PRIVATE
                            )
                        //Set the Adapter to the RecyclerView//

                        var apiServices = APIClient_Main.client.create(Api::class.java)

                        Log.e("API : ", "" + apiServices)

                        var token = sharedPreferences.getString("token", "")

                        Log.e("token : ", "" + token)

                        val call = apiServices.get_caskbackpercentage("Token $token")
                        Log.e("API Categories : ", "" + call)
//


                        call.enqueue(object : Callback<AmazonCashbackResponse> {
                            override fun onResponse(
                                call: Call<AmazonCashbackResponse>,
                                response: Response<AmazonCashbackResponse>
                            ) {

                                if (response.isSuccessful) {

                                    try {


                                        val amazonpercentage =
                                            response.body()!!.amazon_cashback_percentage

                                        var percentage =
                                            (arrayListImage.get(position).offers.listings[0].price.amount * (amazonpercentage.toDouble()) / 100)

                                        Log.e("amazon perce", "" + percentage)

                                        holder.cashbackprice!!.text = "" + percentage
                                    } catch (e: java.lang.NumberFormatException) {
                                        e.printStackTrace()
                                    }
                                } else {
                                    try {
                                        Toast.makeText(context, "No Data", Toast.LENGTH_LONG)
                                            .show()

                                    } catch (e: NullPointerException) {
                                        e.printStackTrace()
                                    }


                                }
                            }

                            override fun onFailure(
                                call: Call<AmazonCashbackResponse>,
                                t: Throwable?
                            ) {
                                Log.e("response", t.toString())
                            }
                        })


                    } else {


                        Toast.makeText(context, "Please Check your internet", Toast.LENGTH_LONG)
                            .show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(context, "" + e.message, Toast.LENGTH_LONG).show()

                } catch (e1: JSONException) {
                    e1.printStackTrace()
                }



                holder.addtocart?.setOnClickListener {

                    var product_id = arrayListImage.get(position).asin




                    if (NetWorkConection.isNEtworkConnected(context as Activity)) {

                        //Set the Adapter to the RecyclerView//


                        var apiServices = APIClient_Main.client.create(Api::class.java)

                        val sharedPreferences =
                            context.getSharedPreferences(
                                "loginprefs",
                                Context.MODE_PRIVATE
                            )

                        val token = sharedPreferences.getString("token", "")
                        val call =
                            apiServices.addcart_(product_id, "1", "Token " + token)


                        call.enqueue(object : Callback<AddCartResponse> {
                            @SuppressLint("WrongConstant")
                            override fun onResponse(
                                call: Call<AddCartResponse>,
                                response: Response<AddCartResponse>
                            ) {

                                Log.e(ContentValues.TAG, response.toString())

                                if (response.isSuccessful) {

                                    //Set the Adapter to the RecyclerView//

                                    cartDatabase = Room.databaseBuilder(
                                        context,
                                        CartDatabase::class.java,
                                        "MyCart"
                                    )
                                        .fallbackToDestructiveMigration() // this is only for testing,

                                        .allowMainThreadQueries().build()
                                    try {
                                        val cart = Cart()
                                        val id =
                                            arrayListImage.get(position).browse_node_info.browse_nodes[0].id.toInt()
//                                        cart.id = arrayListImage.get(position).asin.toInt()
                                        cart.id = id
                                        cart.imageid =
                                            arrayListImage.get(position).images.primary.medium.url
                                        cart.name =
                                            arrayListImage.get(position).browse_node_info.browse_nodes[0].context_free_name
                                        cart.actualprice =
                                            arrayListImage.get(position).offers.listings[0].saving_basis.display_amount
                                        cart.offerprice =
                                            arrayListImage.get(position).offers.listings[0].price.amount.toString()
                                        if (cartDatabase.cartDao()!!
                                                .isAddToCart(id) !== 1
                                        ) {
                                            cartDatabase.cartDao()!!.addToCart(cart)

                                            Log.e("add adapter", "" + cart.id)

                                            val intent = Intent("mymsg")
                                            intent.putExtra("cartcount", arrayListImage.size)
                                            LocalBroadcastManager.getInstance(context)
                                                .sendBroadcast(intent)
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "You are Already added to cart!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        Toast.makeText(
                                            context,
                                            "Item Added Sucessfully",
                                            Toast.LENGTH_LONG
                                        ).show()

                                    } catch (e: NullPointerException) {
                                        e.printStackTrace()
                                    } catch (e: TypeCastException) {
                                        e.printStackTrace()
                                    } catch (e: JsonSyntaxException) {
                                        e.printStackTrace()
                                    } catch (e: IllegalStateException) {
                                        e.printStackTrace()
                                    } catch (e: NumberFormatException) {
                                        e.printStackTrace()
                                    }
                                }
                            }


                            override fun onFailure(
                                call: Call<AddCartResponse>?,
                                t: Throwable?
                            ) {
                                Log.e(ContentValues.TAG, t.toString())
                            }
                        })


                    } else {

                        Toast.makeText(context, "Please Check your internet", Toast.LENGTH_LONG)
                            .show()
                    }

                }

                holder.amazonitems_ll?.setOnClickListener {

                    val sharedPreferences =
                        context.getSharedPreferences(
                            "loginprefs",
                            Context.MODE_PRIVATE
                        )
                    var product_id = arrayListImage.get(position).asin
                    val editor = sharedPreferences.edit()
                    editor.putString("product_id", product_id.toString())
                    editor.commit()

                    val navController =
                        Navigation.findNavController(context as Activity, R.id.nav_host_fragment)
                    navController.navigate(R.id.nav_amzonproductidetails)
                }


            } catch (e: ClassCastException) {
                e.printStackTrace()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
//

        }


        return myView

    }

    //Auto Generated Method
    override fun getItem(p0: Int): Any {
        return arrayListImage.get(p0)
    }

    //Auto Generated Method
    override fun getItemId(p0: Int): Long {
        return 0
    }

    //Auto Generated Method
    override fun getCount(): Int {
        return arrayListImage.size
    }


    //Create A class To hold over View like we taken in grid_item.xml

    class ViewHolder {

        var brandimage: ImageView? = null
        var favorites: ImageView? = null
        var productimage: ImageView? = null
        var productname: TextView? = null
        var offerprice: TextView? = null
        var actuallsaleprice: TextView? = null
        var ratingcount: TextView? = null
        var cashbackprice: TextView? = null
        var addtocart: Button? = null
        var amazonitems_ll: LinearLayout? = null


    }

    companion object {
        lateinit var cartDatabase: CartDatabase
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()

            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {

                    contactListFiltered = arrayListImage

                } else {
                    val filteredList: ArrayList<AmazonResponse> = ArrayList<AmazonResponse>()


                    for (row: AmazonResponse in arrayListImage) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.browse_node_info.browse_nodes[0].context_free_name.toLowerCase()
                                .contains(charString.toLowerCase())
                        ) {

                            Log.e(
                                "filter name",
                                row.browse_node_info.browse_nodes[0].context_free_name
                            )
                            filteredList.add(row)

                        }

                    }

                    contactListFiltered = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = contactListFiltered
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: Filter.FilterResults
            ) {

                if (filterResults.count == 0)
                else {
                    contactListFiltered = filterResults.values as List<AmazonResponse>
                    notifyDataSetChanged()
                }


            }
        }
    }

}
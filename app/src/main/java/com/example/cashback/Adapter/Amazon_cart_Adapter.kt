package com.example.cashback.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.bookursalon.util.NetWorkConection
import com.example.cashback.APIClient_Main
import com.example.cashback.Api
import com.example.cashback.Home_Screen
import com.example.cashback.R
import com.example.cashback.models.GetCartListResponse
import com.example.cashback.roomdatabase.CartDatabase
import com.google.gson.JsonSyntaxException
import okhttp3.ResponseBody
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Amazon_cart_Adapter(
    var context: Context,
    val userList: ArrayList<GetCartListResponse>
) :
    RecyclerView.Adapter<Amazon_cart_Adapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Amazon_cart_Adapter.ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.cart_adapter, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(
        holder: Amazon_cart_Adapter.ViewHolder,
        position: Int
    ) {


        holder.productname.text = userList.get(position).product_image
        Glide.with(context).load(userList.get(position).product_image)
            .error(R.drawable.ic_amazon_icon)
            .into(holder.productimage)
        try {


            holder.productname.text = userList.get(position).product_info
            holder.productprice.text = "Rs " + userList.get(position).amount.toString()
            holder.actuallprice.text = "Rs " + userList.get(position).saving.toString()
            holder.percentage.text = "Rs " + userList.get(position).discount_percentage.toString()

            holder.actuallprice.paintFlags =
                holder.actuallprice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            Log.e("size", "" + userList.size)

            holder.checkout_btn.setOnClickListener {

                val product_url = userList.get(position).product_url

                val sharedPreferences =
                    context.getSharedPreferences(
                        "loginprefs",
                        Context.MODE_PRIVATE
                    )

                val editor = sharedPreferences.edit()
                editor.putString("producturl", product_url)
                editor.commit()

                val navController =
                    Navigation.findNavController(context as Activity, R.id.nav_host_fragment)
                navController.navigate(R.id.nav_checkout)
            }

            holder.deleteitem.setOnClickListener {


                var product_id = userList.get(position).product_id

                Log.e("deleproduct id", product_id)
                val url = "delete_wishlist/$product_id/"

                try {


                    userList.removeAt(position)
                    notifyDataSetChanged()
//                    cartDatabase.cartDao()!!.deleteItem(product_id.toInt())
//                    val cartcount: Int = cartDatabase.cartDao()!!.countCart()

//                    val intent = Intent("mymsg")
//                    intent.putExtra("cartcount", cartcount)
//                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
//
//                    Log.e("cartcount dele", "" + cartcount)

                } catch (e: java.lang.NumberFormatException) {
                    e.printStackTrace()
                }
                if (NetWorkConection.isNEtworkConnected(context as Activity)) {

                    //Set the Adapter to the RecyclerView//


                    var apiServices = APIClient_Main.client.create(Api::class.java)

                    val sharedPreferences =
                        context.getSharedPreferences(
                            "loginprefs",
                            Context.MODE_PRIVATE
                        )

                    val token = sharedPreferences.getString("token", "")
                    Log.e("cart delete", "" + token)

                    val call =
                        apiServices.addcart_list_delete("Token $token", url)


                    call.enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(
                            call: Call<ResponseBody>,
                            response: Response<ResponseBody>
                        ) {

                            Log.e(ContentValues.TAG, response.body().toString())

                            if (response.isSuccessful) {

                                //Set the Adapter to the RecyclerView//

                                try {

                                    Toast.makeText(
                                        context,
                                        "Item Deleted Sucessfully",
                                        Toast.LENGTH_LONG).show()

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
                            call: Call<ResponseBody>?,
                            t: Throwable?
                        ) {
                            Log.e(ContentValues.TAG, t.toString())
                        }
                    })


                } else {

                    Toast.makeText(context, "Please Check your internet", Toast.LENGTH_LONG).show()
                }

            }

        } catch (e: NumberFormatException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }


    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productimage = itemView.findViewById(R.id.productimage) as ImageView
        val deleteitem = itemView.findViewById(R.id.deleteitem) as ImageView
        val productname = itemView.findViewById(R.id.productname) as TextView
        val productprice = itemView.findViewById(R.id.productprice) as TextView
        val actuallprice = itemView.findViewById(R.id.actuallprice) as TextView
        val checkout_btn = itemView.findViewById(R.id.checkout) as Button
        val percentage = itemView.findViewById(R.id.percentage) as TextView


    }


}
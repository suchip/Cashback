package com.example.cashback.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.cashback.R
import com.example.cashback.models.Flipkart.FlipkartProductsResponse


class offerProducts_Adapter(
    context: Context,
    arrayListImage: ArrayList<FlipkartProductsResponse>

) :
    BaseAdapter() {

    //Passing Values to Local Variables

    var context = context
    var arrayListImage = arrayListImage


    //Auto Generated Method
    @SuppressLint("CheckResult")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

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
            holder.flipkartitems_ll = myView.findViewById<LinearLayout>(R.id.flipkartitems_ll) as LinearLayout


            //Set A Tag to Identify our view.
            myView.tag = holder


        } else {


            //If Our View in not Null than Just get View using Tag and pass to holder Object.

            holder = myView.tag as ViewHolder

            try {


                //Setting name to TextView by position
                holder.productname!!.text = arrayListImage.get(position).productBaseInfoV1.title
                holder.offerprice!!.text ="Rs "+
                    arrayListImage.get(position).productBaseInfoV1.flipkartSpecialPrice.amount.toString()
                holder.actuallsaleprice!!.text ="Rs "+
                    arrayListImage.get(position).productBaseInfoV1.maximumRetailPrice.amount.toString()
                holder.ratingcount!!.text =
                    arrayListImage.get(position).productShippingInfoV1.sellerNoOfRatings.toString()

                holder.actuallsaleprice!!.paintFlags =
                    holder.actuallsaleprice!!.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                val images =
                    arrayListImage.get(position).productBaseInfoV1.imageUrls.resolutionType1

                var product_id =
                    arrayListImage.get(position).productBaseInfoV1.productId
                Log.e("producid", "" + product_id)

//                val urls=it.resolutionType200x200

                holder.productimage?.let { it1 ->
                    Glide.with(context).load(images)
                        .error(R.drawable.ic_flipkart)
                        .into(it1)
                }
              val  sharedPreferences =
                    context.getSharedPreferences(
                        "loginprefs",
                        Context.MODE_PRIVATE
                    )


                holder.flipkartitems_ll?.setOnClickListener {

                    var product_id = arrayListImage.get(position).productBaseInfoV1.productId
                    val editor = sharedPreferences.edit()
                    editor.putString("product_id", product_id.toString())
                    editor.commit()

                    val navController =
                        Navigation.findNavController(context as Activity, R.id.nav_host_fragment)
                    navController.navigate(R.id.nav_productidetails)


                }

            } catch (e: ClassCastException) {

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
        var flipkartitems_ll: LinearLayout? = null

    }

}
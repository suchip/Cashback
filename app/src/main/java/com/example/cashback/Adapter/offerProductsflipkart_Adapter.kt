package com.example.cashback.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.cashback.R
import com.example.cashback.models.Flipkart.FlipkartlistResponse


class offerProductsflipkart_Adapter(
    context: Context,
    arrayListImage: ArrayList<FlipkartlistResponse>

) :
    BaseAdapter() {

    //Passing Values to Local Variables

    var context = context
    var arrayListImage = arrayListImage


    //Auto Generated Method
    @SuppressLint("CheckResult")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        var myView = convertView
        var holder: ViewHolder


        if (myView == null) {

            //If our View is Null than we Inflater view using Layout Inflater

            val mInflater = (context as Activity).layoutInflater

            //Inflating our grid_item.
            myView = mInflater.inflate(R.layout.home_adapter, parent, false)

            //Create Object of ViewHolder Class and set our View to it

            holder = ViewHolder()


            //Find view By Id For all our Widget taken in grid_item.

            /*Here !! are use for non-null asserted two prevent From null.
             you can also use Only Safe (?.)

            */


            holder.productimage =
                myView.findViewById<ImageView>(R.id.home_productimage) as ImageView
            holder.productname = myView.findViewById<TextView>(R.id.productname) as TextView


            //Set A Tag to Identify our view.
            myView.tag = holder


        } else {


            //If Our View in not Null than Just get View using Tag and pass to holder Object.

            holder = myView.tag as ViewHolder

            try {


                //Setting name to TextView by position
                holder.productname!!.text = arrayListImage.get(position).title

                val image =
                    arrayListImage.get(position).imageUrls.map {

                        val images = it.url

                        holder.productimage?.let { it1 ->
                            Glide.with(context).load(images)
                                .error(R.drawable.ic_flipkart)

                                .into(it1)
                        }
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


        var productimage: ImageView? = null
        var productname: TextView? = null


    }

}
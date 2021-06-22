package com.example.cashback.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cashback.R
import com.example.cashback.models.GetCartListResponse


class WishlistAdapter(wishlists: List<GetCartListResponse>, context: Context) :
    RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {
    var wishlists: List<GetCartListResponse>
    var context: Context
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cart_adapter, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        i: Int
    ) {
        val ld: GetCartListResponse = wishlists[i]
        Glide.with(context).load(ld.product_image)
            .error(R.drawable.ic_amazon_icon)
            .into(viewHolder.productimage)

        viewHolder.productname.text = ld.product_info
        viewHolder.offerprice.text = ld.saving.toString()
        viewHolder.actuallsaleprice.text = ld.amount.toString()
    }

    override fun getItemCount(): Int {
        return wishlists.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productimage: ImageView = itemView.findViewById(R.id.productimage) as ImageView
        val productname: TextView = itemView.findViewById(R.id.productname)
        val offerprice = itemView.findViewById<TextView>(R.id.offerprice) as TextView
        val actuallsaleprice =
            itemView.findViewById<TextView>(R.id.actuallsaleprice) as TextView
    }

    init {
        this.wishlists = wishlists
        this.context = context
    }
}
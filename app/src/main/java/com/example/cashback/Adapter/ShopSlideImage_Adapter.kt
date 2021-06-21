package com.example.cashback.Adapter

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.cashback.R
import com.example.cashback.models.ImageModel

/**
 * Created by suchipeddinti on 10/07/2019.
 */
class ShopSlideImage_Adapter(
    private val context: Context,
    private val imageModelArrayList: ArrayList<ImageModel>
) : PagerAdapter() {
    private val inflater: LayoutInflater
    init {
        inflater = LayoutInflater.from(context)
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
    override fun getCount(): Int {
        return imageModelArrayList.size
    }
    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.shopslider_adapter, view, false)!!
        val imageView = imageLayout
            .findViewById(R.id.shopsliderimage) as ImageView
        imageView.setBackgroundResource(imageModelArrayList.get(position).getImage_drawables())

        return imageLayout
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}
    override fun saveState(): Parcelable? {
        return null
    }
}
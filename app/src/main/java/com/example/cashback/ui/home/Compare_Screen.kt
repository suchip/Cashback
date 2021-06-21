package com.example.cashback.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.cashback.R

class Compare_Screen : Fragment() {

    lateinit var addcart: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.compare_product_adapter, container, false)

        addcart = root.findViewById(R.id.addcart)

        addcart.setOnClickListener {
            val navController =
                Navigation.findNavController(activity as Activity, R.id.nav_host_fragment)
            navController.navigate(R.id.nav_cart)
        }

        return root
    }
}
package com.example.cashback.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.cashback.R

class Payment_Screen : Fragment() {

    lateinit var url: String
    lateinit var webview: WebView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.payment_screen, container, false)


        webview = root.findViewById(R.id.webView)
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.

        val sharedPreferences =
            requireActivity().getSharedPreferences(
                "loginprefs",
                Context.MODE_PRIVATE
            )

        url = sharedPreferences.getString("producturl", "").toString()
        Log.e("url", url)
        webview.webViewClient = WebViewClient()

        // this will load the url of the website
        webview.loadUrl(url)

        // this will enable the javascript settings
        webview.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        webview.settings.setSupportZoom(true)
        return root
    }
}
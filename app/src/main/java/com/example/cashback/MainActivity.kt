package com.example.cashback
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var listView:ListView
    lateinit var searchView:SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        listView=findViewById(R.id.listView) as ListView
        searchView=findViewById(R.id.searchView) as SearchView



        val bestCities =
            listOf("shoe", "mobile", "Laptop", "Bottel", "jbl", "T-shirts", "Watches", "toys","Electronics","Gadgets","Home","Footware","Kids")
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bestCities)
        listView.adapter = adapter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //Performs search when user hit the search button on the keyboard
                if (bestCities.contains(p0)) {
                    adapter.filter.filter(p0)
                } else {
                    Toast.makeText(this@MainActivity, "No match found", Toast.LENGTH_SHORT).show()
                }
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                //Start filtering the list as user start entering the characters
                adapter.filter.filter(p0)
                return false
            }
        })
    }
}








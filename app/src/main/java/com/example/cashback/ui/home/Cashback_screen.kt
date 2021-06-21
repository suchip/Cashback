package com.example.cashback.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cashback.R
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel

class Cashback_screen : Fragment() {

    lateinit var piechart: PieChart
    lateinit var tvR: TextView
    lateinit var tvPython: TextView
    lateinit var tvCPP: TextView
    lateinit var tvJava: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.cashback, container, false)

        piechart = root.findViewById(R.id.piechart)
        tvR = root.findViewById(R.id.tvR)
        tvPython = root.findViewById(R.id.tvPython)
        tvCPP = root.findViewById(R.id.tvCPP)
        tvJava = root.findViewById(R.id.tvJava)

        setData()

        return root
    }
    private fun setData() {

        // Set the percentage of language used
        tvR.setText(Integer.toString(40))
        tvPython.setText(Integer.toString(30))
        tvCPP.setText(Integer.toString(5))
        tvJava.setText(Integer.toString(25))

        // Set the data and color to the pie chart
        piechart.addPieSlice(
            PieModel(
                "R", tvR.getText().toString().toInt().toFloat(),
                Color.parseColor("#FFA726")
            )
        )
        piechart.addPieSlice(
            PieModel(
                "Python", tvPython.getText().toString().toInt().toFloat(),
                Color.parseColor("#66BB6A")
            )
        )
        piechart.addPieSlice(
            PieModel(
                "C++", tvCPP.getText().toString().toInt().toFloat(),
                Color.parseColor("#EF5350")
            )
        )
        piechart.addPieSlice(
            PieModel(
                "Java", tvJava.getText().toString().toInt().toFloat(),
                Color.parseColor("#29B6F6")
            )
        )

        // To animate the pie chart
        piechart.startAnimation()
    }

}
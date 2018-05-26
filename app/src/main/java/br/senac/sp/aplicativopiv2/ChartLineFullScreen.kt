package br.senac.sp.aplicativopiv2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class ChartLineFullScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart_line_full)

        val listData = ArrayList<Entry>()
        listData.add(Entry(0f, 10F))
        listData.add(Entry(1f, 20F))
        listData.add(Entry(2f, 30F))
        listData.add(Entry(3f, 100F))
        listData.add(Entry(4f, 40F))
        listData.add(Entry(5f, 200F))

        val lineDataSet = LineDataSet(listData,"")
        lineDataSet.color = ContextCompat.getColor(applicationContext, R.color.colorAccent)
        lineDataSet.valueTextColor = ContextCompat.getColor(applicationContext, android.R.color.white)

        val lineData = LineData(lineDataSet)
        val lineChart = findViewById<View>(R.id.lineChart) as LineChart

        lineChart.data = lineData
        lineChart.invalidate() //refresh graph

    }
}

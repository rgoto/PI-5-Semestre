package br.senac.sp.aplicativopiv2

import android.R.attr.data
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import br.senac.sp.aplicativopiv2.ChartLineFullScreen.MyYAxisValueFormatter
import android.R.attr.entries
import android.widget.Toast
import br.senac.sp.aplicativopiv2.Utilities.ExternalConnections
import br.senac.sp.aplicativopiv2.Utilities.UserData
import br.senac.sp.aplicativopiv2.Utilities.VolleyCallback
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import org.json.JSONArray
import org.json.JSONObject

class ChartLineFullScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart_line_full)

        val listData = ArrayList<Entry>()

        var x = 0f
        for (value in UserData.getGastoList()) {
            listData.add(Entry(x, value.toFloat()))
            x++
        }

        /*val listData = ArrayList<Entry>()
        listData.add(Entry(0f, 100f))
        listData.add(Entry(0.5f, 200f))
        listData.add(Entry(1f, 75f))
        listData.add(Entry(1.5f, 50f))
        listData.add(Entry(2f, 10f))
        listData.add(Entry(2.5f, 100f))
        listData.add(Entry(3f, 200f))
        listData.add(Entry(3.5f, 75f))
        listData.add(Entry(4f, 75f))
        listData.add(Entry(4.5f, 75f))
        listData.add(Entry(5f, 50f))
        listData.add(Entry(5.5f, 75f))
        listData.add(Entry(6f, 10f))
        listData.add(Entry(6.5f, 75f))
        listData.add(Entry(7f, 100f))
        listData.add(Entry(7.5f, 75f))
        listData.add(Entry(8f, 200f))
        listData.add(Entry(8.5f, 75f))
        listData.add(Entry(9f, 75f))
        listData.add(Entry(9.5f, 75f))
        listData.add(Entry(10f, 50f))
        listData.add(Entry(10.5f, 75f))
        listData.add(Entry(11f, 10f))
        listData.add(Entry(11.5f, 75f))
        listData.add(Entry(12f, 100f))
        listData.add(Entry(12.5f, 75f))
        listData.add(Entry(13f, 200f))
        listData.add(Entry(13.5f, 75f))
        listData.add(Entry(14f, 75f))
        listData.add(Entry(14.5f, 75f))
        listData.add(Entry(15f, 50f))
        listData.add(Entry(15.5f, 75f))
        listData.add(Entry(16f, 10f))
        listData.add(Entry(16.5f, 75f))
        listData.add(Entry(17f, 100f))
        listData.add(Entry(17.5f, 75f))
        listData.add(Entry(18f, 200f))
        listData.add(Entry(18.5f, 75f))
        listData.add(Entry(19f, 75f))
        listData.add(Entry(19.5f, 75f))
        listData.add(Entry(20f, 50f))
        listData.add(Entry(20.5f, 75f))
        listData.add(Entry(21f, 10f))
        listData.add(Entry(21.5f, 75f))
        listData.add(Entry(22f, 100f))
        listData.add(Entry(22.5f, 75f))
        listData.add(Entry(23f, 200f))
        listData.add(Entry(23.5f, 75f))*/

        val lineChart = findViewById<View>(R.id.lineChart) as LineChart

       /* val xAxis = lineChart.xAxis
        xAxis.valueFormatter = MyXAxisValueFormatter()
        xAxis.granularity = 1f

        val yAxis = lineChart.axisLeft
        yAxis.valueFormatter = MyYAxisValueFormatter()*/


        val dataSet = LineDataSet(listData, "Vendas")
        dataSet.color = ContextCompat.getColor(applicationContext, R.color.colorAccent)
        dataSet.valueTextColor = ContextCompat.getColor(applicationContext, android.R.color.white)
        dataSet.setDrawFilled(true)
        dataSet.setDrawValues(false)
        val testeData = LineData(dataSet)

        lineChart.data = testeData
        lineChart.isDoubleTapToZoomEnabled = false

        lineChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, h: Highlight) {
                Toast.makeText(this@ChartLineFullScreen,  "" + e.x + " " + e.y,
                        Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected() {}
        })
        lineChart.animateXY(3000, 3000)
        lineChart.invalidate()

    }


    inner class MyYAxisValueFormatter : IAxisValueFormatter {

        override fun getFormattedValue(value: Float, axis: AxisBase): String {
            return "R$ $value"
        }
    }

    inner class MyXAxisValueFormatter : IAxisValueFormatter {

        private val mValues = arrayOf("00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00",
                                        "07:30", "08:00", "08:30", "09:00" ,"09:30", "10:00", "10:30", "11:00","11:30", "12:00", "12:30", "13:00","13:30", "14:00","14:30",
                                        "15:00" ,"15:30", "16:00","16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00",
                                        "22:30", "23:00", "23:30")

        override fun getFormattedValue(value: Float, axis: AxisBase): String {
            //Log.d("Teste", mValues[value.toInt()])
            return mValues[value.toInt()]
        }
    }

}

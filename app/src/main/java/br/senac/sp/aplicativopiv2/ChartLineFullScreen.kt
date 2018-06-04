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

        val lineChart = findViewById<View>(R.id.lineChart) as LineChart

        val yAxis = lineChart.axisLeft
        yAxis.valueFormatter = MyYAxisValueFormatter()


        val dataSet = LineDataSet(listData, "Potência")
        dataSet.color = ContextCompat.getColor(applicationContext, R.color.colorAccent)
        dataSet.valueTextColor = ContextCompat.getColor(applicationContext, android.R.color.white)
        dataSet.setDrawFilled(true)
        dataSet.setDrawValues(false)
        val testeData = LineData(dataSet)

        lineChart.data = testeData
        lineChart.isDoubleTapToZoomEnabled = false
        lineChart.description.text = "Potência nas últimas 24Horas"

        lineChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, h: Highlight) {
                Toast.makeText(this@ChartLineFullScreen,  "" + UserData.getHorarioList()[e.x.toInt()] + ", Valor: " + e.y,
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

}

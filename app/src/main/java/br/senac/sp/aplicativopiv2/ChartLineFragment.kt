package br.senac.sp.aplicativopiv2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class ChartLineFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chart_line, container, false)

        val listData = ArrayList<Entry>()
        listData.add(Entry(0f, 10F))
        listData.add(Entry(1f, 20F))
        listData.add(Entry(2f, 30F))
        listData.add(Entry(3f, 100F))
        listData.add(Entry(4f, 40F))
        listData.add(Entry(5f, 200F))

        val lineDataSet = LineDataSet(listData,"")
        lineDataSet.color = ContextCompat.getColor(context, R.color.colorAccent)
        lineDataSet.valueTextColor = ContextCompat.getColor(context, android.R.color.white)

        val lineData = LineData(lineDataSet)
        val lineChart = view.findViewById<View>(R.id.lineChart) as LineChart

        lineChart.data = lineData
        lineChart.invalidate() //refresh graph


        return view
    }
}

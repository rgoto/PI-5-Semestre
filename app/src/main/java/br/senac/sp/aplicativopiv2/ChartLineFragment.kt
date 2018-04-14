package br.senac.sp.aplicativopiv2


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_chart_line.*


class ChartLineFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chart_line, container, false)

        val entries = ArrayList<Entry>()
        entries.add(Entry(1.0F, 1))
        entries.add(Entry(2.0F, 2))
        entries.add(Entry(3.0F, 3))
        entries.add(Entry(4.0F, 4))

        val dataset = LineDataSet(entries, "")
        dataset.setDrawCubic(true)
        dataset.setDrawFilled(true)
        dataset.setDrawHighlightIndicators(true)
        dataset.lineWidth = 1.95f
        dataset.circleRadius = 5f
        dataset.color = Color.parseColor("#EFEFFF")
        dataset.setDrawCircleHole(false)
        dataset.setDrawCircles(false)
        dataset.highLightColor = Color.WHITE
        dataset.setDrawValues(false)

        val labels = ArrayList<String>()
        labels.add("A")
        labels.add("B")
        labels.add("C")
        labels.add("D")

        val data = LineData(labels, dataset)

        linechart.data = data
        linechart.xAxis.setDrawGridLines(false)
        linechart.axisLeft.setDrawGridLines(false)
        linechart.xAxis.axisLineColor = resources.getColor(R.color.colorPrimaryLight)//top line
        linechart.xAxis.textColor = resources.getColor(R.color.colorPrimaryLight)
        linechart.axisLeft.axisLineColor = resources.getColor(R.color.colorPrimaryLight)//left line
        linechart.axisLeft.textColor = resources.getColor(R.color.colorPrimaryLight)
        linechart.axisRight.axisLineColor = resources.getColor(R.color.colorPrimaryLight)//right line
        linechart.axisRight.textColor = resources.getColor(R.color.colorPrimaryLight)
        linechart.setDrawBorders(false)
        linechart.setDrawGridBackground(false)
        linechart.setDescriptionColor(Color.WHITE)
        linechart.isAutoScaleMinMaxEnabled = false

        return view
    }

}

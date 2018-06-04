package br.senac.sp.aplicativopiv2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import br.senac.sp.aplicativopiv2.Utilities.ExpenditureForecast
import br.senac.sp.aplicativopiv2.Utilities.ExternalConnections
import br.senac.sp.aplicativopiv2.Utilities.UserData
import br.senac.sp.aplicativopiv2.Utilities.VolleyCallback
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import org.json.JSONObject


class ChartLineFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chart_line, container, false)

        val externalConnections = ExternalConnections.getInstance()

        //Verifica Estado da lampada
        val switchLamp1 = view.findViewById<View>(R.id.switch1) as Switch

        externalConnections.getStateOfLamps(context, UserData.getInstance().id) {
            if (UserData.getInstance().stateLamp1 == 1 ) {
                switchLamp1.isChecked = true
                switchLamp1.text = "Ligada"

            } else {
                switchLamp1.isChecked = false
                switchLamp1.text = "Desligada"
            }
        }

        //popula grafico
        val listData = ArrayList<Entry>()
        var x = 0f
        for (value in UserData.getGasto2minList()) {
            listData.add(Entry(x, value.toFloat()))
            x++
        }

        val lineDataSet = LineDataSet(listData,"Gasto")
        lineDataSet.color = ContextCompat.getColor(context, R.color.colorAccent)
        lineDataSet.valueTextColor = ContextCompat.getColor(context, android.R.color.white)
        lineDataSet.setDrawFilled(true)
        lineDataSet.setDrawValues(false)

        val lineData = LineData(lineDataSet)
        val lineChart = view.findViewById<View>(R.id.lineChart) as LineChart

        lineChart.data = lineData
        lineChart.isDoubleTapToZoomEnabled = false
        lineChart.description.text = "Gasto nas últimas 24Horas (2 min)"

        lineChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, h: Highlight) {
                Toast.makeText(context,  "" + UserData.getHorarioList()[e.x.toInt()] + " " + e.y,
                        Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected() {}
        })
        lineChart.animateXY(3000, 3000)
        lineChart.invalidate()

        lineChart.data = lineData
        lineChart.invalidate() //refresh graph


        //Quando o switch for presionado trocar o label
        switchLamp1.setOnClickListener {
            switchLamp1.isClickable = false

            if (switchLamp1.isChecked) {
                switchLamp1.text = "Ligada"
                externalConnections.setStateOfLamps(context,1, UserData.getInstance().id) { switchLamp1.isClickable = true }
            } else {
                switchLamp1.text = "Desligada"
                externalConnections.setStateOfLamps(context, 0, UserData.getInstance().id) { switchLamp1.isClickable = true }
            }
        }

        //adicionando a previsão no codigo
        val previsao = view.findViewById<View>(R.id.previsao) as TextView
        ExpenditureForecast.getInstance().monthlyPrediction(UserData.getInstance().id, context)

        val textPrevisao = "R$ " + UserData.getInstance().previsao
        previsao.text = textPrevisao

        return view
    }
}

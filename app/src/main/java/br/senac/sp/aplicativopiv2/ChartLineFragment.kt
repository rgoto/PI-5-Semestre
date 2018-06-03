package br.senac.sp.aplicativopiv2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import br.senac.sp.aplicativopiv2.Utilities.ExternalConnections
import br.senac.sp.aplicativopiv2.Utilities.UserData
import br.senac.sp.aplicativopiv2.Utilities.VolleyCallback
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import org.json.JSONObject


class ChartLineFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chart_line, container, false)

        val externalConnections = ExternalConnections.getInstance()

        //Verifica Estado da lampada
        val switchLamp1 = view.findViewById<View>(R.id.switch1) as Switch

        ExternalConnections.getInstance().getStateOfLamps(context, UserData.getInstance().id, object: VolleyCallback {
            override fun onSuccess(result: JSONObject?) {
                if (UserData.getInstance().stateLamp1 == 1 ) {
                    switchLamp1.isChecked = true
                    switchLamp1.text = "Ligada"

                } else {
                    switchLamp1.isChecked = false
                    switchLamp1.text = "Desligada"
                }
            }
        })

        //popula graficos
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


        //Quando o switch for presionado trocar o label
        switchLamp1.setOnClickListener {
            switchLamp1.isClickable = false

            if (switchLamp1.isChecked) {
                switchLamp1.text = "Ligada"
                externalConnections.setStateOfLamps(context,1, UserData.getInstance().id, object: VolleyCallback {
                    override fun onSuccess(result: JSONObject?) {
                        switchLamp1.isClickable = true
                    }
                })
            } else {
                switchLamp1.text = "Desligada"
                externalConnections.setStateOfLamps(context, 0, UserData.getInstance().id, object: VolleyCallback {
                    override fun onSuccess(result: JSONObject?) {
                        switchLamp1.isClickable = true
                    }
                })
            }
        }

        return view
    }
}

package br.senac.sp.aplicativopiv2

import android.app.usage.UsageEvents
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import br.senac.sp.aplicativopiv2.Utilities.ExternalConnections
import br.senac.sp.aplicativopiv2.Utilities.UserData
import kotlinx.android.synthetic.main.fragment_suporte.*

class RecyclerView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager (this, LinearLayout.VERTICAL, false)

        val users = ArrayList<UserRecycler>()

        for ((i, horario) in UserData.getHorarioList().withIndex()) {
            users.add(UserRecycler(horario, "Potência: " + UserData.getPotenciaList()[i], "Gasto: " + UserData.getGastoList()[i]))
        }

        val adapter = CustomAdpater(users)
        recyclerView.adapter = adapter

    }
}

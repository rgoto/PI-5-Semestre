package br.senac.sp.aplicativopiv2

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_suporte.*

class RecyclerView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager (this, LinearLayout.VERTICAL, false)

        val users = ArrayList<UserRecycler>()

        users.add(UserRecycler("10/15/1995", 15.5, 45 ))
        users.add(UserRecycler("10/15/1995", 15.5, 45 ))
        users.add(UserRecycler("10/15/1995", 15.5, 45 ))
        users.add(UserRecycler("10/15/1995", 15.5, 45 ))
        users.add(UserRecycler("10/15/1995", 15.5, 45 ))
        users.add(UserRecycler("10/15/1995", 15.5, 45 ))
        users.add(UserRecycler("10/15/1995", 15.5, 45 ))

        val adapter = CustomAdpater(users)
        recyclerView.adapter = adapter




    }
}

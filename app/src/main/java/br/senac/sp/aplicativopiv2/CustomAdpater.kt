package br.senac.sp.aplicativopiv2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CustomAdpater (val userList: ArrayList<UserRecycler>) : RecyclerView.Adapter<CustomAdpater.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val UserRecycler: UserRecycler = userList[position]

        holder?.textViewDate?.text = UserRecycler.date
        holder?.textViewPotencia?.text = UserRecycler.potencia.toString()
        holder?.textViewGasto?.text = UserRecycler.gasto.toString()

    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDate = itemView.findViewById<TextView>(R.id.labelDate)
        val textViewPotencia = itemView.findViewById<TextView>(R.id.labelPotencia)
        val textViewGasto = itemView.findViewById<TextView>(R.id.labelGasto)
    }
}
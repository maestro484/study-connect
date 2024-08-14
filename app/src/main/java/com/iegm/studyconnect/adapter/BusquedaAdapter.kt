package com.iegm.studyconnect.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.R

class BusquedaAdapter: RecyclerView.Adapter<BusquedaAdapter.BusquedaViewHolder>() {

    val resultados: MutableList<String> = mutableListOf()

    class BusquedaViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val resultadoTV: TextView = itemView.findViewById(R.id.textView4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusquedaViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.item_resultados,
            parent,false)
        return BusquedaViewHolder(viewLayout)
    }

    override fun getItemCount(): Int {
        return resultados.size
    }

    override fun onBindViewHolder(holder: BusquedaViewHolder, position: Int) {
        Log.d("busqueda", "resultados: " + resultados[position])
        holder.resultadoTV.text = resultados[position]
    }

}
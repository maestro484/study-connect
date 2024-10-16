package com.iegm.studyconnect.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.model.Materia
import com.iegm.studyconnect.model.Resultados
import com.iegm.studyconnect.model.Tipo

class BusquedaAdapter(val context: Context) : RecyclerView.Adapter<BusquedaAdapter.BusquedaViewHolder>() {

//esta es la lista que contiene los resultados de busqueda
    val resultados: MutableList<Resultados> = mutableListOf()
//esta clase continen los resultados de busqueda y actualiza los resultados atiempo real
    class BusquedaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val resultadoTV: TextView = itemView.findViewById(R.id.textView4)
        val resultadoView: CardView = itemView.findViewById(R.id.resultadoView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusquedaViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.item_resultados,

            parent, false
        )
        return BusquedaViewHolder(viewLayout)

    }


    override fun getItemCount(): Int {
        return resultados.size


    }

    override fun onBindViewHolder(holder: BusquedaViewHolder, position: Int) {
        Log.d("busqueda", "resultados: " + resultados[position])
        val resultado = resultados[position]
        holder.resultadoTV.text = resultado.resultado
        holder.resultadoView.setOnClickListener {
            when (resultado.tipo) {
                Tipo.MATERIA -> {
                    (context as MainActivity).abrirHomeFragment()
                }

                Tipo.APUNTE -> {
                    (context as MainActivity).abrirApuntesFragment()
                }

                Tipo.FECHA -> {
                    (context as MainActivity).abrirApuntesFragment()

                }

                Tipo.PROFESOR -> {
                    (context as MainActivity).abrirApuntesFragment()
                }

            }

        }
    }


}


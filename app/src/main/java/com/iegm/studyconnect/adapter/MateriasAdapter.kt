package com.iegm.studyconnect.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.R

class MateriasAdapter: RecyclerView.Adapter<MateriasAdapter.MateriaViewModel>() {

    var materias: List<String> = listOf()

   class MateriaViewModel(itemView: View): RecyclerView.ViewHolder(itemView)
   {                                                       //duda
       val materiaBtn: Button = itemView.findViewById(R.id.button3)
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MateriaViewModel{
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            //duda
             R.layout.item_materias,
            parent,false)
        return MateriaViewModel(viewLayout)
    }

    override fun onBindViewHolder(holder: MateriasAdapter.MateriaViewModel, position: Int) {
        Log.d("busqueda", "resultados: " + materias[position])
        holder.materiaBtn.text = materias[position]
    }

    override fun getItemCount(): Int {
        return materias.size
    }
}

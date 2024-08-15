package com.iegm.studyconnect.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.R

class ComentariosAdapter() :
    RecyclerView.Adapter<ComentariosAdapter.ViewHolder>() {

    var dataset: MutableList<String> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val TextView: TextView
        val ImageView: ImageView

        init {
            TextView = view.findViewById(R.id.textView11)
            ImageView = view.findViewById(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_comentarios,
            parent, false
        )

        return ViewHolder(view)
    }

    override fun getItemCount() = dataset.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.TextView.text = dataset[position]
    }


}




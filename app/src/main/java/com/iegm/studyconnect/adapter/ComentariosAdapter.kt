package com.iegm.studyconnect.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.R
import com.iegm.studyconnect.model.Comentario

class ComentariosAdapter() : RecyclerView.Adapter<ComentariosAdapter.ViewHolder>() {

    val avatars = listOf(
        R.drawable.ardilla,
        R.drawable.ballena,
        R.drawable.buho,
        R.drawable.caballo,
        R.drawable.capibara,
        R.drawable.chita,
        R.drawable.cocodrilo,
        R.drawable.delfin,
        R.drawable.huron,
        R.drawable.leon,
        R.drawable.mapache,
        R.drawable.oso_pardo,
        R.drawable.oso_polar,
        R.drawable.oveja,
        R.drawable.pajaro,
        R.drawable.puma,
        R.drawable.serpiente,
        R.drawable.tiburon,
        R.drawable.tortuga,
        R.drawable.vaca,
        R.drawable.zorro
    )

    var dataset: MutableList<Comentario> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreUsuario: TextView
        val comentarioUsuario: TextView
        val avatarUsuario: ImageView

        init {
            comentarioUsuario = view.findViewById(R.id.comentario_usuario)
            avatarUsuario = view.findViewById(R.id.avatar_image)
            nombreUsuario = view.findViewById(R.id.nombre_usuario)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_comentarios, parent, false
        )

        return ViewHolder(view)
    }

    override fun getItemCount() = dataset.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comentario = dataset[position]
        holder.comentarioUsuario.text = comentario.descripcion
        holder.avatarUsuario.setImageResource(avatars[comentario.avatar])
        holder.nombreUsuario.text = comentario.usuario
    }
}




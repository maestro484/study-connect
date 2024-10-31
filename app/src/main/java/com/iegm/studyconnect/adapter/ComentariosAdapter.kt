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
    //esto es para que los usuarios cuando escriban el comentario aparesca con la imagen
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
    var dataset: MutableList<Comentario> =
        mutableListOf() // Inicializa una lista mutable de objetos Comentario.

    // Aquí llamamos los elementos de item_comentarios.
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Declara variables para referirse a los elementos de la vista item_comentarios.
        val nombreUsuario: TextView
        val comentarioUsuario: TextView
        val avatarUsuario: ImageView

        // Inicializa las variables y busca los elementos de la vista item_comentarios.
        init {
            comentarioUsuario = view.findViewById(R.id.comentario_usuario)
            avatarUsuario = view.findViewById(R.id.avatar_image)
            nombreUsuario = view.findViewById(R.id.nombre_usuario)
        }
    }

    // Crea y devuelve un nuevo ViewHolder para una vista dada.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla la vista layout para el ViewHolder usando el LayoutInflater.
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_comentarios, // El layout de la vista que se utilizará para este ViewHolder.
            parent, false // Indica que no se debe agregar la vista a la jerarquía de vistas.
        )

        // Devuelve un nuevo ViewHolder con la vista inflada.
        return ViewHolder(view)
    }

    // Devuelve el número total de elementos en la lista de dataset.
    override fun getItemCount() = dataset.size

    // Asocia una vista con un elemento de la lista de dataset y actualiza la vista según sea necesario.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Obtiene el objeto Comentario correspondiente a la posición actual en la lista de dataset.
        val comentario = dataset[position]

        // Establece el texto del TextView comentarioUsuario con la descripción del comentario.
        holder.comentarioUsuario.text = comentario.descripcion

        // Establece la imagen del ImageView avatarUsuario con el avatar correspondiente al objeto Comentario.
        holder.avatarUsuario.setImageResource(avatars[comentario.avatar])

        // Establece el texto del TextView nombreUsuario con el nombre del usuario que hizo el comentario.
        holder.nombreUsuario.text = comentario.usuario
    }
}
package com.iegm.studyconnect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.R
import com.iegm.studyconnect.ui.fragments.OnAvatarSelected
import de.hdodenhof.circleimageview.CircleImageView
// Adaptador para mostrar una lista de avatares en un RecyclerView
class AvatarsAdapter(val onAvatarSelected: OnAvatarSelected) : RecyclerView.Adapter<AvatarsAdapter.ViewHolder>() {

    // Lista de recursos de imágenes de avatares
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

    // Clase interna que representa un elemento de la lista (ViewHolder)
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Referencia a la vista del avatar
        val profileAvatar: CircleImageView = itemView.findViewById(R.id.profile_image)
    }

    // Crea nuevas vistas para el RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla el layout del item de avatar
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
        return ViewHolder(itemView) // Devuelve el ViewHolder
    }

    // Devuelve el número total de avatares
    override fun getItemCount(): Int {
        return avatars.size
    }

    // Asocia los datos del avatar a las vistas
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.profileAvatar.setImageResource(avatars[position]) // Establece la imagen del avatar

        // Configura el evento de clic para seleccionar el avatar
        holder.profileAvatar.setOnClickListener {
            onAvatarSelected.onAvatarClick(avatars[position]) // Llama al método del callback con el avatar seleccionado
        }
    }
}

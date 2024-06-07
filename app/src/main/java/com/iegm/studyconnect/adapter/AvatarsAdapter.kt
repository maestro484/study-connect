package com.iegm.studyconnect.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.R
import de.hdodenhof.circleimageview.CircleImageView

class AvatarsAdapter(val context: Context): RecyclerView.Adapter<AvatarsAdapter.ViewHolder>()  {

    val avatars = listOf("leon.png", "puma.png", "chita.png", "mapache.png", "buho.png",
        "huron.png", "tiburon.png", "vaca.png",
        "ardilla.png", "oso_pardo.png", "oso_polar.png", "oso_rojo.png" ,
    "caballo.png", "tortuga.png", "cocodrilo.png", "oveja.png", "pajaro.png", "zorro.png", "serpiente.png", "ballena.png",
        "capibara.png", "delfin.png")

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val profileAvatar : CircleImageView = itemView.findViewById(R.id.profile_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return avatars.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val iconResId = context.resources.getIdentifier(avatars[position], "drawable", context.packageName)
        holder.profileAvatar.setImageResource(iconResId)
    }

}
package com.iegm.studyconnect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.R
import de.hdodenhof.circleimageview.CircleImageView

class AvatarsAdapter: RecyclerView.Adapter<AvatarsAdapter.ViewHolder>()  {

    val avatars = listOf("león.png", "puma.png", "chita.png", "mapache.png", "búho.png",
        "hurón.png", "tiburón.png", "vaca.png",
        "ardilla.png", "oso lunar.png", "oso panda.png", "oso polar.png", "oso rojo.png", "oso grizzly.png",
    "caballo.png", "tortuga.png", "cocodrilo.png", "oveja.png", "pájaro.png", "zorro.png", "serpiente.png", "kiwi.png",
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

    }

}
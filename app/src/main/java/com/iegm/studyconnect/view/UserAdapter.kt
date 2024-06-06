package com.iegm.studyconnect.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.R
import com.iegm.studyconnect.model.UserData
import com.iegm.studyconnect.ui.fragments.apuntesFragment

class UserAdapter(val c: apuntesFragment, val userList:ArrayList<UserData> ):RecyclerView.Adapter<UserAdapter.UserViewHolder>()

{

    inner class UserViewHolder(val v: View) : RecyclerView.ViewHolder(v){
        val name = v.findViewById<TextView>(R.id.mTitle)
        val mbNum = v.findViewById<TextView>(R.id.mSubTitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from (parent.context )

        val v = inflater.inflate(R.layout.item_apunte,parent,false )

         return UserViewHolder(v)
    }

    override fun getItemCount(): Int {
        return  userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList [position]
        holder.name.text = newList.userName
        holder.mbNum.text = newList.userMb

    }


}
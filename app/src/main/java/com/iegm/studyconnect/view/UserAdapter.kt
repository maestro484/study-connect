package com.iegm.studyconnect.view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.model.UserData
import com.iegm.studyconnect.ui.fragments.ApuntesFragment
import com.iegm.studyconnect.ui.fragments.OnAvatarSelected

class UserAdapter(val context: Context, val c: ApuntesFragment, val userList: ArrayList<UserData>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    inner class UserViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        var name: TextView
        var mbNum: TextView
        var mMenus: ImageView
        val itemCard : CardView

        init {
            name = v.findViewById<TextView>(R.id.mTitle)
            mbNum = v.findViewById<TextView>(R.id.mSubTitle)
            mMenus = v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener { popupMenus(it) }

            itemCard = v.findViewById(R.id.item_card)

        }

        private fun popupMenus(v: View) {
            val position = userList[adapterPosition]
            val popupMenus = PopupMenu(context, v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editText -> {
                        val v = LayoutInflater.from(context).inflate(R.layout.add_item,null)
                        val name = v.findViewById<EditText>(R.id.userName)
                        val number = v.findViewById<EditText>(R.id.userNo)

                        AlertDialog.Builder(context)
                            .setView(v)
                            .setPositiveButton("Ok"){
                                dialog,_ ->
                                position.userName = name.text.toString()
                                position.userMb = number.text.toString()
                                notifyDataSetChanged()
                                Toast.makeText(context, "Estas editando", Toast.LENGTH_SHORT)
                                dialog.dismiss()
                            }
                            .setNegativeButton( "cancelar"){
                                dialog,_->
                                dialog.dismiss()

                            }
                            .create()
                            .show()
                        true
                    }
                    R.id.delete -> {
                         AlertDialog.Builder(context)
                             .setTitle("Eliminar")
                             .setIcon(R.drawable.ic_warning)
                             .setMessage("Estás seguro de eliminar esta información?")
                             .setPositiveButton("Sí"){
                                     dialog,_->
                                 userList.removeAt(adapterPosition)
                                 notifyDataSetChanged()
                                 Toast.makeText(context, "Eliminar está información", Toast.LENGTH_SHORT)
                                     .show()
                                 dialog.dismiss()
                             }
                             .setNegativeButton("No"){
                                 dialog,_ ->
                                 dialog.dismiss()
                             }
                             .create()
                             .show()

                        true
                    }

                    else -> true
                }

            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val v = inflater.inflate(R.layout.item_apunte, parent, false,  )

        return UserViewHolder(v)



    }

    override fun getItemCount(): Int {
        return userList.size
    }
    //




    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.name.text = newList.userName
        holder.mbNum.text = newList.userMb
        holder.itemCard.setOnClickListener {
            (context as  MainActivity).apply {
                abrirPdfFragment(holder.name.text.toString())
            }
        }
    }




}
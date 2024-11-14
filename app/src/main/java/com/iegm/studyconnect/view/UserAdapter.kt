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
import com.iegm.studyconnect.model.Apunte
import com.iegm.studyconnect.model.UserData
import com.iegm.studyconnect.ui.fragments.ApuntesFragment
import com.iegm.studyconnect.ui.fragments.OnAvatarSelected

class UserAdapter(val context: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // Lista mutable de apuntes
    var userList: MutableList<Apunte> = mutableListOf()

    // ViewHolder interno para la representación de cada item en la lista
    inner class UserViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        var name: TextView  // Texto que muestra el nombre
        var mbNum: TextView  // Texto que muestra el número del mes
        var mMenus: ImageView  // Icono de menú para opciones
        val itemCard: CardView  // CardView que representa el contenedor del item

        init {
            // Inicialización de las vistas
            name = v.findViewById<TextView>(R.id.mTitle)
            mbNum = v.findViewById<TextView>(R.id.mSubTitle)
            mMenus = v.findViewById(R.id.mMenus)

            // Asignamos un listener al ícono de menú para mostrar opciones
            mMenus.setOnClickListener { popupMenus(it) }

            // Inicializamos el CardView
            itemCard = v.findViewById(R.id.item_card)
        }

        // Función que maneja la visualización de un PopupMenu con opciones (editar y eliminar)
        private fun popupMenus(v: View) {
            val position = userList[adapterPosition]  // Obtiene el apunte en la posición actual
            val popupMenus = PopupMenu(context, v)  // Crea el PopupMenu
            popupMenus.inflate(R.menu.show_menu)  // Infla el menú de opciones
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editText -> {  // Opción para editar un apunte
                        val v = LayoutInflater.from(context).inflate(R.layout.add_item, null)
                        val name = v.findViewById<EditText>(R.id.userName)
                        val number = v.findViewById<EditText>(R.id.userNo)

                        // Mostrar un diálogo para editar el nombre y número
                        AlertDialog.Builder(context)
                            .setView(v)
                            .setPositiveButton("Ok") { dialog, _ ->
                                // Al presionar OK, se actualizan los valores
                                position.nombre = name.text.toString()
                                position.mes = number.text.toString()
                                notifyDataSetChanged()  // Notificar que los datos han cambiado
                                Toast.makeText(context, "Estas editando", Toast.LENGTH_SHORT)
                                dialog.dismiss()
                            }
                            .setNegativeButton("cancelar") { dialog, _ ->
                                dialog.dismiss()  // Cierra el diálogo si se cancela
                            }
                            .create()
                            .show()  // Muestra el diálogo
                        true
                    }
                    R.id.delete -> {  // Opción para eliminar un apunte
                        // Mostrar un diálogo de confirmación para eliminar el apunte
                        AlertDialog.Builder(context)
                            .setTitle("Eliminar")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Estás seguro de eliminar esta información?")
                            .setPositiveButton("Sí") { dialog, _ ->
                                // Al presionar Sí, se elimina el apunte
                                userList.removeAt(adapterPosition)
                                notifyDataSetChanged()  // Notificar que los datos han cambiado
                                Toast.makeText(context, "Eliminar está información", Toast.LENGTH_SHORT)
                                    .show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No") { dialog, _ ->
                                dialog.dismiss()  // Cierra el diálogo si se presiona No
                            }
                            .create()
                            .show()  // Muestra el diálogo
                        true
                    }
                    else -> true  // Si se selecciona otra opción, no se hace nada
                }
            }
            popupMenus.show()  // Muestra el PopupMenu

            // Forzar la visualización de los íconos en el PopupMenu
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        }
    }

    // Método para crear la vista de cada ítem del RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_apunte, parent, false)
        return UserViewHolder(v)  // Retorna un nuevo ViewHolder
    }

    // Método para obtener la cantidad de elementos en la lista
    override fun getItemCount(): Int {
        return userList.size  // Retorna el tamaño de la lista de apuntes
    }

    // Método para vincular los datos con las vistas en cada ítem
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]  // Obtiene el apunte en la posición actual
        holder.name.text = newList.nombre  // Asigna el nombre al TextView
        holder.mbNum.text = newList.mes  // Asigna el mes al TextView
        holder.itemCard.setOnClickListener {
            // Abre un fragmento de PDF al hacer clic en el card
            (context as MainActivity).apply {
                abrirPdfFragment(holder.name.text.toString())
            }
        }
    }
}

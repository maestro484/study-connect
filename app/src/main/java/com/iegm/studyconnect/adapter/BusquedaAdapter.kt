package com.iegm.studyconnect.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.model.Resultados
import com.iegm.studyconnect.model.Tipo

class BusquedaAdapter(val context: Context?) : RecyclerView.Adapter<BusquedaAdapter.BusquedaViewHolder>() {

    // Esta es la lista que contiene los resultados de búsqueda.
    val resultados: MutableList<Resultados> = mutableListOf()

    // Clase que contiene los resultados de búsqueda y actualiza los resultados en tiempo real.
    class BusquedaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val resultadoTV: TextView = itemView.findViewById(R.id.textView4)
        val resultadoView: CardView = itemView.findViewById(R.id.resultadoView)
    }

    // Crea y devuelve un nuevo ViewHolder para una vista dada.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusquedaViewHolder {
        // Infla la vista layout para el ViewHolder usando el LayoutInflater.
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.item_resultados, // El layout de la vista que se utilizará para este ViewHolder.
            parent, false // Indica que no se debe agregar la vista a la jerarquía de vistas.
        )
        // Devuelve un nuevo BusquedaViewHolder con la vista inflada.
        return BusquedaViewHolder(viewLayout)
    }

    // Devuelve el número total de elementos en la lista de resultados.
    override fun getItemCount(): Int {
        return resultados.size
    }

    // Asocia una vista con un elemento de la lista de resultados y actualiza la vista según sea necesario.
    override fun onBindViewHolder(holder: BusquedaViewHolder, position: Int) {
        // Obtiene el resultado correspondiente a la posición actual en la lista de resultados.
        val resultado = resultados[position]

        // Establece el texto del TextView resultadoTV con el resultado obtenido.
        holder.resultadoTV.text = resultado.resultado

        // Establece un oyente de clics en el CardView resultadoView para manejar el evento cuando se hace clic en el elemento.
        holder.resultadoView.setOnClickListener {
            // Cuando se hace clic en el elemento, verifica el tipo de resultado y abre el fragmento correspondiente.
            when (resultado.tipo) {
                Tipo.MATERIA -> {
                    // Abre el fragmento Home si el tipo de resultado es MATERIA.
                    (context as MainActivity).abrirHomeFragment()
                }

                Tipo.APUNTE -> {
                    // Abre el fragmento Apuntes si el tipo de resultado es APUNTE.
                    (context as MainActivity).abrirApuntesFragment()
                }

                Tipo.FECHA -> {
                    // Abre el fragmento Apuntes si el tipo de resultado es FECHA.
                    (context as MainActivity).abrirApuntesFragment()
                }

                Tipo.PROFESOR -> {
                    // Abre el fragmento Apuntes si el tipo de resultado es PROFESOR.
                    (context as MainActivity).abrirApuntesFragment()
                }
            }
        }
    }
}
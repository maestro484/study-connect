package com.iegm.studyconnect.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import de.hdodenhof.circleimageview.CircleImageView

// Clase adaptadora para el RecyclerView que muestra las materias
class MateriasAdapter(val context: Context) : RecyclerView.Adapter<MateriasAdapter.MateriaViewModel>() {

    val Materia = listOf(

        R.drawable.zorro
    )
    // Lista que contiene los nombres de las materias
    var materias: List<String> = listOf()

    // Clase interna ViewHolder que almacena las referencias a las vistas de cada ítem
    class MateriaViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Referencia al botón que representa la materia
        val materiaBtn: Button = itemView.findViewById(R.id.button2)
        val profileMateria: CircleImageView = itemView.findViewById(R.id.profile_image)

    }

    // Método para crear nuevas vistas (invocado por el layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriaViewModel {
        // Infla el layout para cada ítem de la lista
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.item_materias, // Layout que se utilizará para cada ítem
            parent, false
        )
        return MateriaViewModel(viewLayout) // Devuelve el ViewHolder con la vista inflada
    }


    // Método para reemplazar el contenido de una vista (invocado por el layout manager)
    override fun onBindViewHolder(holder: MateriasAdapter.MateriaViewModel, position: Int) {
        // Imprime el nombre de la materia en logcat para depuración
        Log.d("busqueda", "resultados: " + materias[position])
        holder.materiaBtn.text = materias[position] // Asigna el nombre de la materia al botón

        // Configura el listener para el botón
        holder.materiaBtn.setOnClickListener {
            // Llama al método abrirPeriodoFragment en la actividad actual
            (context as MainActivity).apply {
                abrirPeriodoFragment()
            }
        }
        holder.profileMateria.setImageResource(Materia[position])

    }

    // Método que devuelve el número de elementos en la lista
    override fun getItemCount(): Int {
        return materias.size // Devuelve la cantidad de materias en la lista
    }
}


package com.iegm.studyconnect.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import de.hdodenhof.circleimageview.CircleImageView

// Clase adaptadora para el RecyclerView que muestra las materias
class MateriasAdapter(val context: Context?) : RecyclerView.Adapter<MateriasAdapter.MateriaViewModel>() {

    val imagenes = listOf(

        R.drawable.lengua,
        R.drawable.matematicas,
        R.drawable.economia,
        R.drawable.comprension_lectora,
        R.drawable.tecnologia,
        R.drawable.etica,
        R.drawable.etnoeducacion,
        R.drawable.geometria,
        R.drawable.estadistica,
        R.drawable.ingles,
        R.drawable.ciencias_sociales,
        R.drawable.ciencias_naturales,
        R.drawable.educacion_fisica,
        R.drawable.artistica,
        R.drawable.quimica,
        R.drawable.calculo,
        R.drawable.fisica_cuantica,
        R.drawable.orientacion_profesional,
        R.drawable.religion
    )

    // Lista que contiene los nombres de las materias
    var materias: List<String> = listOf()

    // Clase interna ViewHolder que almacena las referencias a las vistas de cada ítem
    class MateriaViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Referencia al botón que representa la materia
        val materiaBtn: ConstraintLayout = itemView.findViewById(R.id.layout1708)
        val materiaTv: TextView = itemView.findViewById(R.id.textView13)
        val materiaImg: CircleImageView = itemView.findViewById(R.id.button2)
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

    override fun onBindViewHolder(holder: MateriasAdapter.MateriaViewModel, position: Int) {
        Log.d("busqueda", "resultados: " + materias[position])

        // Asigna el nombre de la materia al TextView
        holder.materiaTv.text = materias[position]

        // Configura el listener para el botón (manteniendo el código original)
        holder.materiaBtn.setOnClickListener {
            (context as MainActivity).apply {
                abrirPeriodoFragment()
            }
        }

        holder.materiaImg.setImageResource(imagenes[position])
    }

    // Método que devuelve el número de elementos en la lista
    override fun getItemCount(): Int {
        return materias.size // Devuelve la cantidad de materias en la lista
    }
}

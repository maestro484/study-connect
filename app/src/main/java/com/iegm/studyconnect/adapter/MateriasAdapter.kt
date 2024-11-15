package com.iegm.studyconnect.adapter

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.icu.text.Transliterator.Position
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.utils.DataManager
import de.hdodenhof.circleimageview.CircleImageView

// Clase adaptadora para el RecyclerView que muestra las materias
class MateriasAdapter(val context: Context?) : RecyclerView.Adapter<MateriasAdapter.MateriaViewModel>() {

    val imagenes8 = listOf(

        R.drawable.matematicas,
        R.drawable.religion,
        R.drawable.educacion_fisica,
        R.drawable.ciencias_naturales,
        R.drawable.artistica,
        R.drawable.lengua,
        R.drawable.comprension_lectora,
        R.drawable.ciencias_sociales,
        R.drawable.geometria,
        R.drawable.etica,
        R.drawable.ingles,
        R.drawable.etnoeducacion,
        R.drawable.tecnologia
    )

    val imagenes9 = listOf(

        R.drawable.matematicas,
        R.drawable.religion,
        R.drawable.educacion_fisica,
        R.drawable.ciencias_naturales,
        R.drawable.artistica,
        R.drawable.lengua,
        R.drawable.comprension_lectora,
        R.drawable.ciencias_sociales,
        R.drawable.geometria,
        R.drawable.etica,
        R.drawable.ingles,
        R.drawable.etnoeducacion,
        R.drawable.tecnologia
    )


    val imagenes10 = listOf(

        R.drawable.religion,
        R.drawable.educacion_fisica,
        R.drawable.quimica,
        R.drawable.artistica,
        R.drawable.lengua,
        R.drawable.comprension_lectora,
        R.drawable.ciencias_sociales,
        R.drawable.calculo,
        R.drawable.etica,
        R.drawable.ingles,
        R.drawable.fisica_cuantica,
        R.drawable.estadistica,
        R.drawable.economia,
        R.drawable.filosofia,
        R.drawable.tecnologia
    )

    val imagenes11 = listOf(

        R.drawable.economia,
        R.drawable.religion,
        R.drawable.educacion_fisica,
        R.drawable.quimica,
        R.drawable.artistica,
        R.drawable.lengua,
        R.drawable.comprension_lectora,
        R.drawable.ciencias_sociales,
        R.drawable.estadistica,
        R.drawable.etica,
        R.drawable.ingles,
        R.drawable.calculo,
        R.drawable.orientacion_profesional,
        R.drawable.filosofia,
        R.drawable.fisica_cuantica,
        R.drawable.tecnologia,
        R.drawable.ciencias_naturales,
        R.drawable.geometria,
        R.drawable.etnoeducacion
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
        // Asigna el nombre de la materia al TextView
        holder.materiaTv.text = materias[position]

        // Configura el listener para el botón (manteniendo el código original)
        holder.materiaBtn.setOnClickListener {
            DataManager.materia = position
            (context as MainActivity).apply {
                abrirPeriodoFragment()
            }
        }

        val sharePref = context?.getSharedPreferences(context?.packageName, MODE_PRIVATE)
        val grado = sharePref?.getInt("GRADO_USUARIO", 0)

        when(grado){
            0 -> holder.materiaImg.setImageResource(imagenes11[position])
            1 -> holder.materiaImg.setImageResource(imagenes10[position])
            2 -> holder.materiaImg.setImageResource(imagenes9[position])
            3 -> holder.materiaImg.setImageResource(imagenes8[position])
        }

    }

    // Método que devuelve el número de elementos en la lista
    override fun getItemCount(): Int {
        return materias.size // Devuelve la cantidad de materias en la lista
    }
}
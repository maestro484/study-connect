package com.iegm.studyconnect.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.rajat.pdfviewer.PdfRendererView

class PdfFragment : Fragment() {

    // Inicialización de variables para las vistas
    lateinit var pdfView: PdfRendererView
    private var selectedUri: String? = null // URI del PDF seleccionado
    lateinit var descripcion: EditText
    lateinit var atras: ImageView
    lateinit var fileTitleTextView: TextView
    lateinit var Relative: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout del fragmento
        return inflater.inflate(R.layout.fragment_pdf2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Asignación de las vistas a las variables
        pdfView = view.findViewById(R.id.pdfView)
        descripcion = view.findViewById(R.id.descripcion)
        atras = view.findViewById(R.id.Atras)
        fileTitleTextView = view.findViewById(R.id.fileTitleTextView)
        Relative = view.findViewById(R.id.relative)
//
        // Recuperar valores guardados
        selectedUri = getSavedPdfUri()
        val savedDescription = getSavedDescription() ?: ""
        val savedTitle = getSavedTitle() ?: ""

        // Asignar los valores recuperados a los campos
        descripcion.setText(savedDescription)
        fileTitleTextView.text = savedTitle

        // Cargar el PDF si existe una URI guardada y se tienen los permisos adecuados
        if (!selectedUri.isNullOrEmpty()) {
            val uri = Uri.parse(selectedUri)
            if (hasUriPermission(uri)) {
                loadPdfInView(uri) // Cargar el PDF en la vista
            } else {
                // Si no se tienen permisos, se pide acceso nuevamente
                Toast.makeText(context, "No se tiene acceso al PDF, selecciona de nuevo.", Toast.LENGTH_LONG).show()
            }
        }

        // Abrir el selector de archivos al tocar el PDF view
        pdfView.setOnClickListener {
            launchFilePicker()
        }

        // Navegar al fragmento anterior al presionar "Atrás"
        atras.setOnClickListener {
            (activity as MainActivity).abrirApunteFragment()
        }

        // Inicializar el PDF view (aunque este método parece estar incorrecto)
        pdfView.initWithUrl(
            url = pdfView.toString(),
            lifecycleCoroutineScope = lifecycleScope,
            lifecycle = lifecycle
        )
        pdfView.jumpToPage(3) // Salto a la página 3 (esto podría ser revisado)
    }

    override fun onPause() {
        super.onPause()
        // Guardar los valores cuando el fragmento entre en pausa
        selectedUri?.let { savePdfUri(Uri.parse(it)) }
        saveDescription(descripcion.text.toString())
        saveTitle(fileTitleTextView.text.toString())
    }

    // Registro del resultado del selector de archivos
    private val filePicker =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val selectedFileUri = result.data?.data
                if (selectedFileUri != null) {
                    // Guardar la URI seleccionada y persistir los permisos
                    savePdfUri(selectedFileUri)
                    requireContext().contentResolver.takePersistableUriPermission(
                        selectedFileUri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                    // Cargar el PDF seleccionado directamente en la vista
                    loadPdfInView(selectedFileUri)
                } else {
                    Toast.makeText(context, "Error al seleccionar el archivo", Toast.LENGTH_SHORT).show()
                }
            }
        }

    // Método para lanzar el selector de archivos
    private fun launchFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf" // Solo permite seleccionar archivos PDF
        }
        filePicker.launch(intent) // Inicia el selector de archivos
    }

    // Método para cargar el PDF en la vista
    private fun loadPdfInView(uri: Uri) {
        pdfView.statusListener = object : PdfRendererView.StatusCallBack {
            override fun onPdfLoadStart() {
                Log.i("statusCallBack", "onPdfLoadStart") // Log de inicio de carga
            }

            override fun onPdfLoadProgress(progress: Int, downloadedBytes: Long, totalBytes: Long?) {}

            override fun onPdfLoadSuccess(absolutePath: String) {
                Log.i("statusCallBack", "onPdfLoadSuccess") // Log de éxito de carga
            }

            override fun onError(error: Throwable) {
                Log.i("statusCallBack", "onError: ${error.message}") // Log de error
                Toast.makeText(context, "Error al cargar el PDF", Toast.LENGTH_SHORT).show()
            }

            override fun onPageChanged(currentPage: Int, totalPage: Int) {}
        }

        // Inicializa el PDF view con la URI del PDF
        pdfView.initWithUri(uri)
    }

    // Guardar la URI del PDF en SharedPreferences
    private fun savePdfUri(uri: Uri) {
        val sharedPreferences = requireContext().getSharedPreferences("PdfPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("saved_pdf_uri", uri.toString())
        editor.apply() // Aplicar cambios
        selectedUri = uri.toString() // Actualizar la URI seleccionada
        Toast.makeText(context, "PDF guardado correctamente", Toast.LENGTH_SHORT).show()
    }

    // Verificar si aún tenemos permisos sobre la URI guardada
    private fun hasUriPermission(uri: Uri): Boolean {
        val takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        try {
            requireContext().contentResolver.takePersistableUriPermission(uri, takeFlags)
            return true // Si se puede tomar el permiso
        } catch (e: SecurityException) {
            return false // Si se lanza una excepción, no se tienen permisos
        }
    }

    // Guardar el título en SharedPreferences
    private fun saveTitle(title: String) {
        val sharedPreferences = requireContext().getSharedPreferences("PdfPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("file_title", title)
        editor.apply() // Aplicar cambios
    }

    // Guardar la descripción en SharedPreferences
    private fun saveDescription(description: String) {
        val sharedPreferences = requireContext().getSharedPreferences("PdfPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("description", description)
        editor.apply() // Aplicar cambios
    }

    // Obtener la URI guardada de SharedPreferences
    private fun getSavedPdfUri(): String? {
        val sharedPreferences = requireContext().getSharedPreferences("PdfPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("saved_pdf_uri", null) // Recuperar la URI guardada
    }

    // Obtener el título guardado de SharedPreferences
    private fun getSavedTitle(): String? {
        val sharedPreferences = requireContext().getSharedPreferences("PdfPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("file_title", "") // Recuperar el título guardado
    }

    // Obtener la descripción guardada de SharedPreferences
    private fun getSavedDescription(): String? {
        val sharedPreferences = requireContext().getSharedPreferences("PdfPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("description", "") // Recuperar la descripción guardada
    }
}

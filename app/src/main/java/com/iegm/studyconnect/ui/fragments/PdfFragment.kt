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

class PdfFragment(private val nombre: String) : Fragment() {

    // Inicialización perezosa de vistas para mejorar el rendimiento
    private val pdfView by lazy { view?.findViewById<PdfRendererView>(R.id.pdfView) }
    private val descripcion by lazy { view?.findViewById<EditText>(R.id.descripcion) }
    private val atras by lazy { view?.findViewById<ImageView>(R.id.Atras) }
    private val fileTitleTextView by lazy { view?.findViewById<TextView>(R.id.fileTitleTextView) }
    private val relativeLayout by lazy { view?.findViewById<RelativeLayout>(R.id.relative) }

    private var selectedUri: String? = null // URI del PDF seleccionado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflar el layout del fragmento
        return inflater.inflate(R.layout.fragment_pdf2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Establecer el título del archivo en el TextView
        fileTitleTextView?.text = nombre

        // Recuperar la URI guardada del PDF
        selectedUri = getSavedPdfUri()

        // Cargar el PDF si existe una URI guardada
        if (!selectedUri.isNullOrEmpty()) {
            loadPdfIfPermitted(Uri.parse(selectedUri))
        }

        // Abrir el selector de archivos al tocar el PDF view
        pdfView?.setOnClickListener { launchFilePicker() }

        // Navegar al fragmento anterior al presionar "Atrás"
        atras?.setOnClickListener { (activity as MainActivity).abrirApuntesFragment() }

        // Inicializar el PDF view
        pdfView?.initWithUrl(url = pdfView.toString(), lifecycleCoroutineScope = lifecycleScope, lifecycle = lifecycle)
    }

    override fun onPause() {
        super.onPause()
        // Guardar la URI, descripción y título al pausar el fragmento
        selectedUri?.let { savePdfUri(Uri.parse(it)) }
        saveDescription(descripcion?.text.toString())
        saveTitle(fileTitleTextView?.text.toString())
    }

    // Registro del resultado del selector de archivos
    private val filePicker = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            // Si se seleccionó un archivo, guardar la URI y cargar el PDF
            result.data?.data?.let { selectedFileUri ->
                savePdfUri(selectedFileUri)
                requireContext().contentResolver.takePersistableUriPermission(selectedFileUri, Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                loadPdfInView(selectedFileUri)
            } ?: Toast.makeText(context, "Error al seleccionar el archivo", Toast.LENGTH_SHORT).show()
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

    // Cargar el PDF si se tiene permiso
    private fun loadPdfIfPermitted(uri: Uri) {
        if (hasUriPermission(uri)) {
            loadPdfInView(uri)
        } else {
            Toast.makeText(context, "No se tiene acceso al PDF, selecciona de nuevo.", Toast.LENGTH_LONG).show()
        }
    }

    // Método para cargar el PDF en la vista
    private fun loadPdfInView(uri: Uri) {
        pdfView?.statusListener = object : PdfRendererView.StatusCallBack {
            override fun onPdfLoadStart() {
                Log.i("statusCallBack", "onPdfLoadStart") // Log de inicio de carga
            }

            override fun onPdfLoadSuccess(absolutePath: String) {
                Log.i("statusCallBack", "onPdfLoadSuccess") // Log de éxito de carga
            }

            override fun onError(error: Throwable) {
                Log.e("statusCallBack", "onError: ${error.message}") // Log de error
                Toast.makeText(context, "Error al cargar el PDF", Toast.LENGTH_SHORT).show()
            }

            override fun onPageChanged(currentPage: Int, totalPage: Int) {}
        }

        // Inicializa el PDF view con la URI del PDF
        pdfView?.initWithUri(uri)
    }

    // Guardar la URI del PDF en SharedPreferences
    private fun savePdfUri(uri: Uri) {
        requireContext().getSharedPreferences("PdfPrefs", Context.MODE_PRIVATE).edit().apply {
            putString("saved_pdf_uri", uri.toString())
            apply() // Aplicar cambios
        }
        selectedUri = uri.toString() // Actualizar la URI seleccionada
        Toast.makeText(context, "PDF guardado correctamente", Toast.LENGTH_SHORT).show()
    }

    // Verificar si aún tenemos permisos sobre la URI guardada
    private fun hasUriPermission(uri: Uri): Boolean {
        val takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        return try {
            requireContext().contentResolver.takePersistableUriPermission(uri, takeFlags)
            true // Si se puede tomar el permiso
        } catch (e: SecurityException) {
            false // Si se lanza una excepción, no se tienen permisos
        }
    }

    // Guardar el título en SharedPreferences
    private fun saveTitle(title: String) {
        requireContext().getSharedPreferences("PdfPrefs", Context.MODE_PRIVATE).edit().apply {
            putString("file_title", title)
            apply() // Aplicar cambios
        }
    }

    // Guardar la descripción en SharedPreferences
    private fun saveDescription(description: String) {
        requireContext().getSharedPreferences("PdfPrefs", Context.MODE_PRIVATE).edit().apply {
            putString("description", description)
            apply() // Aplicar cambios
        }
    }

    // Obtener la URI guardada de SharedPreferences
    private fun getSavedPdfUri(): String? {
        return requireContext().getSharedPreferences("PdfPrefs", Context.MODE_PRIVATE).getString("saved_pdf_uri", null)
    }
}

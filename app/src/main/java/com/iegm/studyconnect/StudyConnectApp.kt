package com.iegm.studyconnect

import android.app.Application
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// ID de la aplicación de OneSignal (debe ser reemplazado por el ID de tu propia aplicación)
const val ONESIGNAL_APP_ID = "335e2f0f-5f50-4378-896f-3eeda23a1c41"

class StudyConnectApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Verbose Logging set to help debug issues, remove before releasing your app.
        // Habilita el nivel de log VERBOSE para la depuración, útil para identificar problemas
        // Se recomienda desactivarlo antes de lanzar la aplicación en producción
        OneSignal.Debug.logLevel = LogLevel.VERBOSE

        // Inicialización de OneSignal con el contexto de la aplicación y el ID de la aplicación
        // OneSignal.initWithContext requiere el contexto de la aplicación y el ID de la app de OneSignal
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)

        // Solicitar permiso para mostrar notificaciones push de forma asíncrona
        // Usamos una corutina para que la solicitud se haga en un hilo IO (de entrada/salida)
        CoroutineScope(Dispatchers.IO).launch {
            // Nota: Se recomienda usar un mensaje in-app en lugar de esta solicitud nativa
            OneSignal.Notifications.requestPermission(false)
        }

        // Etiquetado del usuario en OneSignal. Aquí asignamos el valor "11" al tag "Grade"
        // Las etiquetas se pueden usar para segmentar usuarios y personalizar las notificaciones
        OneSignal.User.addTag("Grade", "11")
    }
}

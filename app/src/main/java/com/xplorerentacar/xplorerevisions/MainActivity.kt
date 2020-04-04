package com.xplorerentacar.xplorerevisions

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.webkit.WebView
import com.xplorerentacar.xplorerevisions.services.CustomWebViewClient
import com.xplorerentacar.xplorerevisions.services.NetworkStatusManager.status
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()

        checkNetwork()
    }

    private fun initComponents() {
        progress!!.setBackgroundColor(Color.TRANSPARENT)
        progress!!.loadUrl("file:///android_asset/html/loader.html")
        val webView = findViewById<View>(R.id.loader) as WebView
        webView.webViewClient = CustomWebViewClient(progress!!)
        webView.setBackgroundColor(Color.TRANSPARENT)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("http://192.168.2.119/RevisionesXplore/index.aspx")


    }


    /***
     * VERIFICA SI HAY ACCESO A INTERNET
     */
    private fun checkNetwork() {
        when (status(Objects.requireNonNull(applicationContext))) {
            "Offline" -> showMensaje(resources.getDrawable(R.drawable.baseline_signal_wifi_off_24), getString(R.string.sin_internet))
            "Online" ->
                showWebView()
        }
    }

    fun MainActivity.showNoInternet() {
        contenedorWebView!!.visibility = View.GONE
        contenedorMensaje!!.visibility = View.VISIBLE
    }

    /***
     * MUESTRA UN MENSAJE EN CASO DE ERROR
     */
    private fun showMensaje(imagMensaje: Drawable, mensaje: String) {
        imagenMensaje!!.setImageDrawable(imagMensaje)
        tvMensaje!!.text = mensaje
        TransitionManager.beginDelayedTransition(contenedorPadre)
        contenedorWebView!!.visibility = View.GONE
        contenedorMensaje!!.visibility = View.VISIBLE
    }

    /***
     * MUESTRA EL WEBVIEW QUE CARGA LA PÁGINA DE REVISIONES
     */
    private fun showWebView() {
        TransitionManager.beginDelayedTransition(contenedorPadre)
        contenedorMensaje!!.visibility = View.GONE
    }
}

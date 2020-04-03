package com.xplorerentacar.xplorerevisions.services

import android.net.http.SslError
import android.util.Log
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient


class CustomWebViewClient(private val progress: WebView) : WebViewClient() {
    
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean { // TODO Auto-generated method stub
        view.loadUrl(url)
        return true
    }

    /***
     * INTERCEPTA ERRORES AL CARGAR LA WEB
     */
    override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
        view!!.loadUrl("file:///android_asset/html/errorPage.html")
        Log.d("Error: ", ""+description)
    }

    override fun onPageFinished(view: WebView, url: String) { // TODO Auto-generated method stub
        super.onPageFinished(view, url)
        progress.visibility = View.GONE
    }

    override fun onReceivedSslError(
        view: WebView?,
        handler: SslErrorHandler?,
        error: SslError?
    ) { // ignore ssl error
        if (handler != null) {
            handler.proceed()
        } else {
            super.onReceivedSslError(view, null, error)
        }
    }

    init {
        progress.visibility = View.VISIBLE
    }
}
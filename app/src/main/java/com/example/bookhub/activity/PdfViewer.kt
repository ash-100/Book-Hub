package com.example.bookhub.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.bookhub.R

class PdfViewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)
        val bookUrl=intent.getStringExtra("bookUrl").toString()
//        val webView:WebView=findViewById(R.id.webView)
//        webView.webViewClient= WebViewClient()
//        webView.settings.setSupportZoom(true)
//        webView.settings.javaScriptEnabled = true
//
//        webView.loadUrl()
        val displayIntent= Intent(Intent.ACTION_VIEW, Uri.parse(bookUrl))
//        displayIntent.setData(Uri.parse(bookUrl))
//        displayIntent.setType("application/pdf")
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(displayIntent)

    }
}
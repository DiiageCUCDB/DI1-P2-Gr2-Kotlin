package com.diiage.edusec.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.diiage.edusec.R
import com.diiage.edusec.data.source.SampleLayerDataSource
import android.widget.LinearLayout
import android.widget.TextView

class ArchitectureLayersActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architecture_layers)

        val container = findViewById<LinearLayout>(R.id.container)
        val layers = SampleLayerDataSource.getLayers()
        layers.forEach { layer ->
            val textView = TextView(this).apply {
                text = "• ${layer.name}: ${layer.description}"
                textSize = 16f
            }
            container.addView(textView)
        }
    }
}

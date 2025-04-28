package com.example.calc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calc.databinding.ActivityMain2Binding

class MainActivity2: AppCompatActivity() {
    lateinit var binding2: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding2 = ActivityMain2Binding.inflate(layoutInflater)
        val view2: LinearLayout = binding2.root
        setContentView(view2)

        binding2.Calcul.setOnClickListener {
            val swap = Intent(this, MainActivity::class.java)
            startActivity(swap)
        }
        binding2.mp3.setOnClickListener {
            val swap = Intent(this, MainActivity3::class.java)
            startActivity(swap)
        }
    }
}
package com.example.spiikuseigame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.spiikuseigame.databinding.ActivityMainBinding

class Graph : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //ホーム画面に遷移
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val back = Intent(this, MainActivity::class.java)
            startActivity(back)
        }
    }
}
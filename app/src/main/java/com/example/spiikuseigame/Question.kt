package com.example.spiikuseigame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Question : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)


        //ホーム画面に遷移
        val kokugoButton = findViewById<Button>(R.id.kokugo)
        kokugoButton.setOnClickListener {
            val back = Intent(this, Question2::class.java)
            startActivity(back)
        }

        //ホーム画面に遷移
        val backButton3 = findViewById<Button>(R.id.backButton3)
        backButton3.setOnClickListener {
            val back = Intent(this, Earn::class.java)
            startActivity(back)
        }
    }
}
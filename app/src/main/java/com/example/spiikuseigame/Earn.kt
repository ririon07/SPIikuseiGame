package com.example.spiikuseigame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Earn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earn)

        //出題画面に遷移
        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            val next = Intent(this, Question::class.java)
            startActivity(next)
        }

        //ホーム画面に遷移
        val backButton2 = findViewById<Button>(R.id.backButton2)
        backButton2.setOnClickListener {
            val back = Intent(this, MainActivity::class.java)
            startActivity(back)
        }

    }
}
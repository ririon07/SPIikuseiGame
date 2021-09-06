package com.example.spiikuseigame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Question2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question2)


        //ホーム画面に遷移
        val backButton = findViewById<Button>(R.id.backbutton)
        backButton.setOnClickListener {
            val back = Intent(this, Question::class.java)
            startActivity(back)
        }
    }
}
package com.example.spiikuseigame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import kotlin.random.Random

class Question2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question2)

        

        val textView = findViewById<TextView>(R.id.question)
        val ans1 = findViewById<Button>(R.id.ans1)
        ans1.setOnClickListener {
            textView.setText("Hello")
        }

        //ホーム画面に遷移
        val backButton = findViewById<Button>(R.id.backbutton)
        backButton.setOnClickListener {
            val back = Intent(this, Question::class.java)
            startActivity(back)
        }
    }
}
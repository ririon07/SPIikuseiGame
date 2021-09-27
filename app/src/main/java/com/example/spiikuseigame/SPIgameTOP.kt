package com.example.spiikuseigame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SPIgameTOP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s_p_igame_t_o_p)

        //グラフ画面へ画面遷移
        val Button = findViewById<Button>(R.id.button)
        Button.setOnClickListener {
            val bu = Intent(this, MainActivity::class.java)
            startActivity(bu)
        }
    }
}
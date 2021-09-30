package com.example.spiikuseigame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.spiikuseigame.databinding.ActivityMainBinding


class Shop : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        //ホーム画面に遷移
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val back = Intent(this, MainActivity::class.java)
            startActivity(back)
        }

        val icebutton = findViewById<ImageButton>(R.id.icebutton)
        icebutton.setOnClickListener {
            val get = Intent (this,MainActivity::class.java)
            startActivity(get)
        }
    }
}
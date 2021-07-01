package com.example.spiikuseigame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class Graph : AppCompatActivity() {

    private lateinit var db: GraphDataBase
    private lateinit var dao:GraphDAO
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        this.db = Room.databaseBuilder(
            this,
            GraphDataBase::class.java,
            "graph.db"
        ).build()
        this.dao = this.db.GraphDAO()

        setContentView(R.layout.activity_graph)

        //ホーム画面に遷移
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val back = Intent(this, MainActivity::class.java)
            startActivity(back)

        }
    }
}
package com.example.spiikuseigame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // サイズ
    /*private val frameHeight = 0
    private val boxSize = 0
    private val screenWidth = 0
    private val screenHeight = 0

    // 位置
    private val boxY = 0f*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //グラフ画面へ画面遷移
        val graphButton = findViewById<Button>(R.id.graphButton)
        graphButton.setOnClickListener {
            val graph = Intent(this, Graph::class.java)
            startActivity(graph)
        }

        //問題選択画面へ画面遷移
        val earnButton = findViewById<Button>(R.id.earnButton)
        earnButton.setOnClickListener {
            val earn = Intent(this, Earn::class.java)
            startActivity(earn)

        }
        val database = GraphDataBase.getInstance(this)
        val grdao = database.gDAO()
        val newGraph = GraphData(1, 2, 3)
        grdao.insert(newGraph)

    }
}
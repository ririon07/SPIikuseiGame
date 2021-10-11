package com.example.spiikuseigame

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.spiikuseigame.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dbName: String = "DB"
    private val tableName: String = "sumTable"
    private val tableName2: String = "monthTable"
    private val char: String = "character"
    private val dbVersion: Int = 1
    private var arrayListId: ArrayList<String> = arrayListOf()
    private var arrayListCorrect: ArrayList<Int> = arrayListOf()
    private var arrayListIncorrect: ArrayList<Int> = arrayListOf()
    private var arrayListAnswer: ArrayList<Int> = arrayListOf()
    private var arrayListDays: ArrayList<String> = arrayListOf()

    private var arrayListCharId: ArrayList<String> = arrayListOf()
    private var arrayListCharHp: ArrayList<String> = arrayListOf()
    private var arrayListCharTake: ArrayList<String> = arrayListOf()
    private var arrayListCharEat: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //グラフ画面へ画面遷移
        val graphButton = findViewById<Button>(R.id.graphButton)
        graphButton.setOnClickListener {
            val graph = Intent(this, Graph::class.java)
            startActivity(graph)
        }


        //問題選択画面へ画面遷移
        val earnButton = findViewById<Button>(R.id.earnButton)
        earnButton.setOnClickListener {
            val earn = Intent(this, Question::class.java)
            startActivity(earn)
        }

        //ショップ画面へ画面遷移
        val shopButton = findViewById<Button>(R.id.shopButton)
        shopButton.setOnClickListener {
            val shop = Intent(this, Shop::class.java)
            startActivity(shop)
        }

        //キャラクター
        val char = findViewById<ImageView>(R.id.character)
        val change = findViewById<Button>(R.id.cleanButton)
        change.setOnClickListener {
            char.setImageResource(R.drawable.rika2)
        }
        selectcharData()


    }

    private fun selectcharData() {
        try {
            arrayListCharId.clear();arrayListCharHp.clear();arrayListCharTake.clear();arrayListCharEat.clear();

            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion)
            val database = dbHelper.readableDatabase

            val sql = "select * from " + char + ";"

            val cursor = database.rawQuery(sql, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    arrayListCharId.add(cursor.getString(0))
                    arrayListCharHp.add(cursor.getString(1))
                    arrayListCharTake.add(cursor.getString(2))
                    arrayListCharEat.add(cursor.getString(3))

                    cursor.moveToNext()
                }
            }
        }catch(exception: Exception) {
            Log.e("selectData", exception.toString());
            print("エラーのキャッチしたお")
        }
    }
}
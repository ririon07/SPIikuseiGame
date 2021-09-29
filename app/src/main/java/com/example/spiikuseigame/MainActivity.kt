package com.example.spiikuseigame

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.spiikuseigame.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dbName: String = "DB"
    private val tableName: String = "sumTable"
    private val tableName2: String = "monthTable"
    private val dbVersion: Int = 1
    private var arrayListId: ArrayList<String> = arrayListOf()
    private var arrayListCorrect: ArrayList<Int> = arrayListOf()
    private var arrayListIncorrect: ArrayList<Int> = arrayListOf()
    private var arrayListAnswer: ArrayList<Int> = arrayListOf()
    private var arrayListDays: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //insertData("国語",1,0,1,"yyyy/MM/ss")
        //insertData2("国語",1,0,1,"yyyy/MM/ss")
        selectData()
        for(i in arrayListId.indices){
            println(arrayListId[i])
        }
        //selectData2()
        //arrayListId.get(0)


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

    }


    private fun insertData(id: String,correct: Int,incorrect: Int,answer: Int,days: String) {
        try {
            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion);
            val database = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("id", id)
            values.put("correct", correct)
            values.put("incorrect", incorrect)
            values.put("answer", answer)
            values.put("days",days)

            database.insertOrThrow(tableName, null, values)
        }catch(exception: Exception) {
            Log.e("insertData", exception.toString())
        }
    }


    private fun insertData2(id: String,correct: Int,incorrect: Int,answer: Int,days: String) {
        try {
            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion);
            val database = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("id", id)
            values.put("correct", correct)
            values.put("incorrect", incorrect)
            values.put("answer", answer)
            values.put("days", days)

            database.insertOrThrow(tableName2, null, values)
        }catch(exception: Exception) {
            Log.e("insertData2", exception.toString())
        }
    }


    private fun selectData() {
        try {
            arrayListId.clear();arrayListCorrect.clear();arrayListIncorrect.clear();arrayListAnswer.clear();arrayListDays.clear()

            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion)
            val database = dbHelper.readableDatabase

            val sql = "select id, correct, incorrect, answer, days from " + tableName + ";"

            val cursor = database.rawQuery(sql, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    arrayListId.add(cursor.getString(0))
                    arrayListCorrect.add(cursor.getInt(1))
                    arrayListIncorrect.add(cursor.getInt(2))
                    arrayListAnswer.add(cursor.getInt(3))
                    arrayListDays.add(cursor.getString(4))
                    cursor.moveToNext()
                }
            }
        }catch(exception: Exception) {
            Log.e("selectData", exception.toString());
        }
    }

    private fun selectData2() {
        try {
            arrayListId.clear();arrayListCorrect.clear();arrayListIncorrect.clear();arrayListAnswer.clear();arrayListDays.clear()

            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion)
            val database = dbHelper.readableDatabase

            val sql = "select id, correct, incorrect, answer, days from " + tableName2 + ";"

            val cursor = database.rawQuery(sql, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    arrayListId.add(cursor.getString(0))
                    arrayListCorrect.add(cursor.getInt(1))
                    arrayListIncorrect.add(cursor.getInt(2))
                    arrayListAnswer.add(cursor.getInt(3))
                    arrayListDays.add(cursor.getString(4))
                    cursor.moveToNext()
                }
            }
        }catch(exception: Exception) {
            Log.e("selectData2", exception.toString());
        }
    }
}
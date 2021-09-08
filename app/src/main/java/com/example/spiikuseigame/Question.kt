package com.example.spiikuseigame

import Answer
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.spiikuseigame.databinding.ActivityMainBinding
import java.util.ArrayList

class Question : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dbName: String = "DB"
    private val tableName: String = "QusetionTable"
    private val dbVersion: Int = 1
    private var arrayListId: ArrayList<String> = arrayListOf()
    private var arrayListCorrect: ArrayList<Int> = arrayListOf()
    private var arrayListIncorrect: ArrayList<Int> = arrayListOf()
    private var arrayListAnswer: ArrayList<Int> = arrayListOf()
    private var arrayListDays: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)



        //問題解答画面に遷移
        val kokugoButton = findViewById<Button>(R.id.kokugo)
        kokugoButton.setOnClickListener {
            val back = Intent(this, Question2::class.java)
            startActivity(back)
        }

        //問題をダウンロード
        val downButton = findViewById<Button>(R.id.download)
        downButton.setOnClickListener {
            insertData(1,1,"最初に示された語と最もはっきりとした反対関係にある語はどれか【低俗】","神聖","高貴","上等","高尚","高尚")
        }
        //ホーム画面に遷移
        val backButton3 = findViewById<Button>(R.id.backButton3)
        backButton3.setOnClickListener {
            val back = Intent(this, Earn::class.java)
            startActivity(back)
        }
    }
    private fun deleteData(whereId: String) {
        try {
            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion);
            val database = dbHelper.writableDatabase

            val whereClauses = "id = ?"
            val whereArgs = arrayOf(whereId)
            database.delete(tableName, whereClauses, whereArgs)
        }catch(exception: Exception) {
            Log.e("deleteData", exception.toString())
        }
    }

    private fun insertData(id: Int,genre: Int,question: String,ans1: String,ans2: String,ans3: String,ans4: String,Answer: String) {
        try {
            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion);
            val database = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("id", id)
            values.put("genre", genre)
            values.put("question", question)
            values.put("ans1", ans1)
            values.put("ans2", ans2)
            values.put("ans3", ans3)
            values.put("ans4", ans4)
            values.put("Ansewr",Answer)

            database.insertOrThrow(tableName, null, values)
        }catch(exception: Exception) {
            Log.e("insertData", exception.toString())
        }
    }
}
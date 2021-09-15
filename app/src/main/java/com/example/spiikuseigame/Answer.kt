package com.example.spiikuseigame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.spiikuseigame.databinding.ActivityMainBinding
import java.util.ArrayList

class Answer : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dbName: String = "DB"
    private val tableName: String = "QuestionTable"
    private val dbVersion: Int = 1
    private var arrayListId: ArrayList<String> = arrayListOf()
    private var arrayListGenre: ArrayList<String> = arrayListOf()
    private var arrayListQuestion: ArrayList<String> = arrayListOf()
    private var arrayListans1: ArrayList<String> = arrayListOf()
    private var arrayListans2: ArrayList<String> = arrayListOf()
    private var arrayListans3: ArrayList<String> = arrayListOf()
    private var arrayListans4: ArrayList<String> = arrayListOf()
    private var arrayListAnswer: ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        binding = ActivityMainBinding.inflate(layoutInflater)

        selectData()
        val string = arrayListAnswer[0]
        //受け取った変数を入れる
        val text = intent.getStringExtra("TEXT_KEY")

        val textView = findViewById<TextView>(R.id.Ans)

        if(string == text){
            textView.setText("正解")
        }else{
            textView.setText("不正解")
        }

        //次へ
        val next = findViewById<Button>(R.id.next)
        next.setOnClickListener {
            val nex = Intent(this, Question2::class.java)
            startActivity(nex)
        }

        //終了
        val end = findViewById<Button>(R.id.end)
        end.setOnClickListener {
            val end1 = Intent(this, Question2::class.java)
            startActivity(end1)
        }

    }

    private fun selectData() {
        try {
            arrayListId.clear();arrayListGenre.clear();arrayListQuestion.clear();arrayListans1.clear();
            arrayListans2.clear();arrayListans3.clear();arrayListans4.clear();arrayListAnswer.clear();


            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion)
            val database = dbHelper.readableDatabase

            val sql = "select id, genre, question, ans1, ans2, ans3, ans4, Answer from " + tableName + ";"

            val cursor = database.rawQuery(sql, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    arrayListId.add(cursor.getString(0))
                    arrayListGenre.add(cursor.getString(1))
                    arrayListQuestion.add(cursor.getString(2))
                    arrayListans1.add(cursor.getString(3))
                    arrayListans2.add(cursor.getString(4))
                    arrayListans3.add(cursor.getString(5))
                    arrayListans4.add(cursor.getString(6))
                    arrayListAnswer.add(cursor.getString(7))
                    cursor.moveToNext()
                }
            }
        }catch(exception: Exception) {
            Log.e("selectData", exception.toString());
        }
    }
}
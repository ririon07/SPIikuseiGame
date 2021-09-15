package com.example.spiikuseigame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.spiikuseigame.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.util.ArrayList
import kotlin.random.Random

class Question2 : AppCompatActivity() {
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
        setContentView(R.layout.activity_question2)
        binding = ActivityMainBinding.inflate(layoutInflater)

        selectData()
        val textView = findViewById<TextView>(R.id.question)
        val string = arrayListQuestion[0]
        textView.setText(string)

        //解答１
        val ans1 = findViewById<Button>(R.id.ans1)
        val an1 = "ア." + arrayListans1[0]
        ans1.setText(an1)
        ans1.setOnClickListener {
            //textは受け渡す変数
            val text = arrayListans1[0]
            val Answer1 = Intent(this, Answer::class.java)
            //intent変数をつなげる(第一引数はキー，第二引数は渡したい変数)
            Answer1.putExtra("TEXT_KEY",text)
            startActivity(Answer1)
        }

        //解答２
        val ans2 = findViewById<Button>(R.id.ans2)
        val an2 = "イ." + arrayListans2[0]
        ans2.setText(an2)
        ans2.setOnClickListener {
            //textは受け渡す変数
            val text = arrayListans2[0]
            val Answer2 = Intent(this, Answer::class.java)
            //intent変数をつなげる(第一引数はキー，第二引数は渡したい変数)
            Answer2.putExtra("TEXT_KEY",text)
            startActivity(Answer2)
        }

        //解答３
        val ans3 = findViewById<Button>(R.id.ans3)
        val an3 = "ウ." + arrayListans3[0]
        ans3.setText(an3)
        ans3.setOnClickListener {
            //textは受け渡す変数
            val text = arrayListans3[0]
            val Answer3 = Intent(this, Answer::class.java)
            //intent変数をつなげる(第一引数はキー，第二引数は渡したい変数)
            Answer3.putExtra("TEXT_KEY",text)
            startActivity(Answer3)
        }

        //解答４
        val ans4 = findViewById<Button>(R.id.ans4)
        val an4 = "エ." + arrayListans4[0]
        ans4.setText(an4)
        ans4.setOnClickListener {
            //textは受け渡す変数
            val text = arrayListans4[0]
            val Answer4 = Intent(this, Answer::class.java)
            //intent変数をつなげる(第一引数はキー，第二引数は渡したい変数)
            Answer4.putExtra("TEXT_KEY",text)
            startActivity(Answer4)
        }

        //ホーム画面に遷移
        val backButton = findViewById<Button>(R.id.backbutton)
        backButton.setOnClickListener {
            val back = Intent(this, Question::class.java)
            startActivity(back)
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
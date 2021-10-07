package com.example.spiikuseigame

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.spiikuseigame.databinding.ActivityMainBinding
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.util.ArrayList

class Answer : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dbName: String = "DB"
    private val tableName: String = "QuestionTable"
    private val tableName2: String = "sumTable"
    private val tableName3: String = "moneyTable"
    private val dbVersion: Int = 1
    private var arrayListId: ArrayList<String> = arrayListOf()
    private var arrayListGenre: ArrayList<String> = arrayListOf()
    private var arrayListQuestion: ArrayList<String> = arrayListOf()
    private var arrayListans1: ArrayList<String> = arrayListOf()
    private var arrayListans2: ArrayList<String> = arrayListOf()
    private var arrayListans3: ArrayList<String> = arrayListOf()
    private var arrayListans4: ArrayList<String> = arrayListOf()
    private var arrayListAnswer: ArrayList<String> = arrayListOf()
    private var arrayListMoney: ArrayList<Int> = arrayListOf()
    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //受け取った変数を入れる
        val genre = intent.getStringExtra("Genre")
        val text = intent.getStringExtra("TEXT_KEY")
        val id = intent.getStringExtra("Id")

        //日付け取得
        val LocalDate = LocalDate.now()

        var tr = 0
        var fa = 0

        if (id != null && genre != null) {
            selectData(id,genre)
        }
        val string = arrayListAnswer[0]

        val textView = findViewById<TextView>(R.id.Ans)
        //おっぱいもみもみしたい。
        if(string == text){
            textView.setText("正解")
            tr = 1
            insertMoney()
        }else{
            textView.setText("不正解")
            fa = 1
        }

        if (id != null && genre != null) {
            insertData(id, genre, tr, fa, LocalDate.toString())
        }

        //次へ
        val next = findViewById<Button>(R.id.next)
        next.setOnClickListener {
            val nex = Intent(this, Question2::class.java)
            nex.putExtra("Genre",genre)
            startActivity(nex)
        }

        //終了
        val end = findViewById<Button>(R.id.end)
        end.setOnClickListener {
            val end1 = Intent(this, Question::class.java)
            startActivity(end1)
        }

    }

    private fun selectData(id: String, genre: String) {
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
                    if (cursor.getString(1) == genre && cursor.getString(0) == id) {
                        arrayListId.add(cursor.getString(0))
                        arrayListGenre.add(cursor.getString(1))
                        arrayListQuestion.add(cursor.getString(2))
                        arrayListans1.add(cursor.getString(3))
                        arrayListans2.add(cursor.getString(4))
                        arrayListans3.add(cursor.getString(5))
                        arrayListans4.add(cursor.getString(6))
                        arrayListAnswer.add(cursor.getString(7))
                    }
                    cursor.moveToNext()
                }
            }
        } catch (exception: Exception) {
            Log.e("selectData", exception.toString());
        }
    }

    private fun insertData(id: String, genre: String, tr: Int, fa: Int, LocalDate: String) {
        try {
            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion);
            val database = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("id", id)
            values.put("genre", genre)
            values.put("correct", tr)
            values.put("incorrect", fa)
            values.put("days", LocalDate)

            database.insertOrThrow(tableName2, null, values)
        }catch(exception: Exception) {
            Log.e("insertData", exception.toString())
        }
    }

    //お金処理
    private fun insertMoney() {
        try {
            var id = 1
            var money = 1
            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion);
            val database = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("id", id)
            values.put("Money", money)

            database.insertOrThrow(tableName3, null, values)
        }catch(exception: Exception) {
            Log.e("insertMoney", exception.toString())
        }
    }

    private fun updateMoney(Money: Int) {
        try {
            val whereId = "1"
            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion);
            val database = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("Money", Money)

            val whereClauses = "id = ?"
            val whereArgs = arrayOf(whereId)
            database.update(tableName3, values, whereClauses, whereArgs)
        }catch(exception: Exception) {
            Log.e("updateData", exception.toString())
        }
    }

    private fun selectMoney() {
        try {
            arrayListMoney.clear();

            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion)
            val database = dbHelper.readableDatabase

            val sql = "select * from " + tableName3 + ";"

            val cursor = database.rawQuery(sql, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                        arrayListMoney.add(cursor.getInt(0))
                    cursor.moveToNext()
                }
            }
        } catch (exception: Exception) {
            Log.e("selectMoney", exception.toString());
        }
    }
}
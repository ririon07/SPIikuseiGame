package com.example.spiikuseigame

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.spiikuseigame.databinding.ActivityMainBinding
import java.util.*

class Question : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dbName: String = "DB"
    private val tableName: String = "QuestionTable"
    private val tableName2: String = "moneyTable"
    private val tableName3: String = "itemTable"
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
        setContentView(R.layout.activity_question)
        binding = ActivityMainBinding.inflate(layoutInflater)


        //国語問題解答画面に遷移
        val kokugoButton = findViewById<Button>(R.id.kokugo)
        kokugoButton.setOnClickListener {
            val Que2 = Intent(this, Question2::class.java)
            startActivity(Que2)
        }

        //社会問題解答画面に遷移
        val shakaiButton = findViewById<Button>(R.id.shakai)
        shakaiButton.setOnClickListener {
            val Que2 = Intent(this, Question2::class.java)
            startActivity(Que2)
        }

        //理科問題解答画面に遷移
        val rikaButton = findViewById<Button>(R.id.rika)
        rikaButton.setOnClickListener {
            val Que2 = Intent(this, Question2::class.java)
            startActivity(Que2)
        }

        //数学問題解答画面に遷移
        val suugakuButton = findViewById<Button>(R.id.suugaku)
        suugakuButton.setOnClickListener {
            val Que2 = Intent(this, Question2::class.java)
            startActivity(Que2)
        }

        //英語問題解答画面に遷移
        val eigoButton = findViewById<Button>(R.id.eigo)
        eigoButton.setOnClickListener {
            val Que2 = Intent(this, Question2::class.java)
            startActivity(Que2)
        }

        //一般問題解答画面に遷移
        val ippanButton = findViewById<Button>(R.id.ippan)
        ippanButton.setOnClickListener {
            val Que2 = Intent(this, Question2::class.java)
            startActivity(Que2)
        }

        //問題をダウンロード
        val downButton = findViewById<Button>(R.id.download)
        downButton.setOnClickListener {
            deleteData("1")
            insertData("1",1,"最初に示された語と最もはっきりとした反対関係にある語はどれか【低俗】","神聖","高貴","上等","高尚","高尚")
            AlertDialog.Builder(this)
                    .setTitle(R.string.dialog_title)
                    .setMessage(R.string.dialog_del_message)
                    .setPositiveButton(R.string.dialog_ok) { dialog, which ->
                    }
                .show()
        }
        //ホーム画面に遷移
        val backButton3 = findViewById<Button>(R.id.backButton3)
        backButton3.setOnClickListener {
            val back = Intent(this, MainActivity::class.java)
            startActivity(back)
        }
    }

    private fun deleteData(Id: String) {
        try {
            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion);
            val database = dbHelper.writableDatabase

            val whereClauses = "id = ?"
            val whereArgs = arrayOf(Id)
            database.delete(tableName, whereClauses, whereArgs)
        }catch(exception: Exception) {
            Log.e("deleteData", exception.toString())
        }
    }


    private fun insertData(id: String,genre: Int,question: String,ans1: String,ans2: String,ans3: String,ans4: String,Answer: String) {
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
            values.put("Answer",Answer)

            database.insertOrThrow(tableName, null, values)
        }catch(exception: Exception) {
            Log.e("insertData", exception.toString())
        }
    }

    private fun insertData2(id: String,moneys: Int) {
        try {
            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion);
            val database = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("id", id)
            values.put("moneys", moneys)

            database.insertOrThrow(tableName2, null, values)
        }catch(exception: Exception) {
            Log.e("insertData2", exception.toString())
        }
    }

    private fun insertData3(id: String, money: Int, item_name:String) {
        try {
            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion);
            val database = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("id", id)
            values.put("money", money)
            values.put("item_name", item_name)

            database.insertOrThrow(tableName3, null, values)
        }catch(exception: Exception) {
            Log.e("insertData3", exception.toString())
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
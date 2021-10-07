package com.example.spiikuseigame

import android.content.ContentValues
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.spiikuseigame.databinding.ActivityMainBinding


class Shop : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dbName: String = "DB"
    private val tableName3: String = "moneyTable"
    private val dbVersion: Int = 1
    private val getid: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        val gettext = findViewById<TextView>(R.id.gettext)

        //ホーム画面に遷移
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val back = Intent(this, MainActivity::class.java)
            startActivity(back)
        }

        val onigiributton = findViewById<ImageButton>(R.id.onigiributton)
        onigiributton.setOnClickListener {
            gettext.setText("おにぎりを選択中")
        }

        val panbutton = findViewById<ImageButton>(R.id.panbutton)
        panbutton.setOnClickListener {
            gettext.setText("コッペパンを選択中")
        }

        val waterbutton = findViewById<ImageButton>(R.id.waterbutton)
        waterbutton.setOnClickListener {
            gettext.setText("クリスタルカイザーを選択中")
            gettext.setTextSize(26.0f)
        }

        val icebutton = findViewById<ImageButton>(R.id.icebutton)
        icebutton.setOnClickListener {
            gettext.setText("アイスを選択中")
        }

        val getButton = findViewById<Button>(R.id.getButton)
        getButton.setOnClickListener {
            gettext.setText("購入しました")
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
}

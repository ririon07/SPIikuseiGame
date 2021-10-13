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
    private var arrayListGenre: ArrayList<String> = arrayListOf()
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
        var cha_count = 0
        val char = findViewById<ImageView>(R.id.character)
        val change = findViewById<Button>(R.id.cleanButton)
        change.setOnClickListener {
            selectData()
            var num = 0   //国語
            var num2 = 0  //数学
            var num3 = 0  //社会
            var num4 = 0  //理科
            var num5 = 0  //英語
            var num6 = 0  //SPI

            var count = 0

            //各教科ごとの合計を表示
            for (i in arrayListCorrect){

                if(arrayListCorrect[count]==1){
                    if (arrayListGenre[count].toInt()==1){
                        num = num + 1
                    }
                    else if(arrayListGenre[count].toInt()==2){
                        num2 = num2 + 1
                    }
                    else if(arrayListGenre[count].toInt()==3){
                        num3 = num3 + 1
                    }
                    else if(arrayListGenre[count].toInt()==4){
                        num4 = num4 + 1
                    }
                    else if(arrayListGenre[count].toInt()==5){
                        num5 = num5 + 1
                    }
                    else if(arrayListGenre[count].toInt()==6){
                        num6 = num6 + 1
                    }
                }
                count = count + 1
            }
            val set  = listOf(num, num2, num3, num4, num5, num6)
            val list = listOf(num, num2, num3, num4, num5, num6).max()

            val a = set.all{it == 0}
            var f = set.indexOf(list)

            println(list)
            println(set)
            println(a)
            println(f)

            //結果
            if(cha_count<1){
                char.setImageResource((R.drawable.poteto2))
            }else{
                if(cha_count<2){
                    if(a == true){
                        char.setImageResource((R.drawable.azarasi))
                    }else{
                        if(f == 0){
                            char.setImageResource((R.drawable.kokugo1))
                        }
                        if(f == 1){
                            char.setImageResource((R.drawable.suugaku1))
                        }
                        if(f == 2){
                            char.setImageResource((R.drawable.syakai1))
                        }
                        if(f == 3){
                            char.setImageResource((R.drawable.rika1))
                        }
                        if(f == 4){
                            char.setImageResource((R.drawable.eigo1))
                        }
                        if(f == 5){
                            char.setImageResource((R.drawable.ippan1))
                        }
                    }

                }else if(cha_count >= 2){
                    if(a == true){
                        char.setImageResource((R.drawable.azarasi))
                    }else {
                        if (f == 0) {
                            char.setImageResource((R.drawable.kokugo2))
                        }
                        if (f == 1) {
                            char.setImageResource((R.drawable.suugaku2))
                        }
                        if (f == 2) {
                            char.setImageResource((R.drawable.syakai2))
                        }
                        if (f == 3) {
                            char.setImageResource((R.drawable.rika2))
                        }
                        if (f == 4) {
                            char.setImageResource((R.drawable.eigo2))
                        }
                        if (f == 5) {
                            char.setImageResource((R.drawable.ippan2))
                        }
                    }
                }
            }
            cha_count = cha_count + 1
        }
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

    private fun selectData() {
        try {
            arrayListId.clear();arrayListGenre.clear();arrayListCorrect.clear();arrayListIncorrect.clear();arrayListDays.clear()

            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion)
            val database = dbHelper.readableDatabase

            val sql = "select id,genre, correct, incorrect, days from " + tableName + ";"

            val cursor = database.rawQuery(sql, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    arrayListId.add(cursor.getString(0))
                    arrayListGenre.add(cursor.getString(1))
                    arrayListCorrect.add(cursor.getInt(2))
                    arrayListIncorrect.add(cursor.getInt(3))
                    arrayListDays.add(cursor.getString(4))
                    cursor.moveToNext()
                }
            }
        } catch (exception: Exception) {
            Log.e("selectData", exception.toString());
        }
    }
}
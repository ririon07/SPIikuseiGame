package com.example.spiikuseigame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.spiikuseigame.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import java.util.ArrayList

class Graph : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dbName: String = "DB"
    private val tableName: String = "sumTable"
    private val tableName2: String = "monthTable"
    private val dbVersion: Int = 1
    private var arrayListId: ArrayList<String> = arrayListOf()
    private var arrayListCorrect: ArrayList<Int> = arrayListOf()
    private var arrayListIncorrect: ArrayList<Int> = arrayListOf()
    private var arrayListDays: ArrayList<String> = arrayListOf()
    private var arrayListGenre: ArrayList<String> = arrayListOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //ホーム画面に遷移
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val back = Intent(this, MainActivity::class.java)
            startActivity(back)
        }

        selectData()
        var cor = arrayListCorrect.sum()
        var inc = arrayListIncorrect.sum()
//        var text1 = findViewById<TextView>(R.id.text)
//        text1.setText(cor.toString())
//        var text2 = findViewById<TextView>(R.id.text)
//        text2.setText(inc.toString())

        //表示用サンプルデータの作成//
        val x = listOf<Float>(1f, 2f, 3f, 4f, 6f, 7f)//X軸データ
        val y = x.map{it*it}//Y軸データ（X軸の2乗）

        //①Entryにデータ格納
        var entryList = mutableListOf<BarEntry>()
        for(i in x.indices){
            entryList.add(
                BarEntry(x[i], y[i])
            )
        }

        //BarDataSetのリスト
        val barDataSets = mutableListOf<IBarDataSet>()
        //②DataSetにデータ格納
        val barDataSet = BarDataSet(entryList, "国語")
        //③DataSetのフォーマット指定
        barDataSet.color = Color.GREEN
        //リストに格納
        barDataSets.add(barDataSet)

        //④BarDataにBarDataSet格納
        val barData = BarData(barDataSets)
        //⑤BarChartにBarData格納
        val barChart = findViewById<BarChart>(R.id.barChartExample2)
        barChart.data = barData
        //⑥Chartのフォーマット指定
        //X軸の設定
        barChart.xAxis.apply {
            isEnabled = true
            textColor = Color.BLACK
        }
        //⑦barchart更新
        barChart.invalidate()
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
        }catch(exception: Exception) {
            Log.e("selectData", exception.toString());
        }
    }

}

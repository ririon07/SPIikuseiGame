package com.example.spiikuseigame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.spiikuseigame.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
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
    private var arrayListAnswer: ArrayList<Int> = arrayListOf()
    private var arrayListDays: ArrayList<String> = arrayListOf()

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
        for(i in arrayListId.indices){
            val text1 = findViewById<TextView>(R.id.text)
            text1.setText(arrayListCorrect[i])

        }


        //折れ線グラフ
            //表示用サンプルデータの作成//
            val x = listOf<Float>(1f, 2f, 3f, 4f, 5f, 6f, 7f)//X軸データ
            val y = listOf<Float>(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f)//Y軸データ

            //①Entryにデータ格納
            var entryList = mutableListOf<Entry>()//1本目の線
            for(i in x.indices){
                entryList.add(
                    Entry(x[i], y[i])
                )
            }

            //LineDataSetのList
            val lineDataSets = mutableListOf<ILineDataSet>()
            //②DataSetにデータ格納
            val lineDataSet = LineDataSet(entryList, "国語")
            //③DataSetにフォーマット指定(3章で詳説)
            lineDataSet.color = Color.BLUE
            //リストに格納
            lineDataSets.add(lineDataSet)

            //④LineDataにLineDataSet格納
            val lineData = LineData(lineDataSets)
            //⑤LineChartにLineData格納
            var lineChart = findViewById<LineChart>(R.id.lineChartExample2)
            lineChart.data = lineData
            //⑥Chartのフォーマット指定(3章で詳説)
            //X軸の設定
            lineChart.xAxis.apply {
                isEnabled = true
                textColor = Color.BLACK
            }
            //⑦linechart更新
            lineChart.invalidate()

//        //LineDataSetのList
//        val lineDataSets = mutableListOf<ILineDataSet>()
//        //②線1本目のデータ格納
//        val lineDataSet1 = LineDataSet(entryList1, "国語")
//        //③線1本目のフォーマット指定
//        lineDataSet1.color = Color.GREEN
//        //②線2本目のデータ格納
//        val lineDataSet2 = LineDataSet(entryList2, "数学")
//        //③線2本目のフォーマット指定
//        lineDataSet2.color = Color.BLUE
//        //②線3本目のデータ格納
//        val lineDataSet3 = LineDataSet(entryList3, "理科")
//        //③線3本目のフォーマット指定
//        lineDataSet3.color = Color.rgb(255, 0, 255)
//        //②線4本目のデータ格納
//        val lineDataSet4 = LineDataSet(entryList4, "英語")
//        //③線4本目のフォーマット指定
//        lineDataSet4.color = Color.RED
//        //②線5本目のデータ格納
//        val lineDataSet5 = LineDataSet(entryList5, "社会")
//        //③線5本目のフォーマット指定
//        lineDataSet5.color = Color.YELLOW
//        //②線6本目のデータ格納
//        val lineDataSet6 = LineDataSet(entryList6, "SPI")
//        //③線6本目のフォーマット指定
//        lineDataSet6.color = Color.rgb(0, 100, 100)
//
//        //リストに格納
//        lineDataSets.add(lineDataSet1)
//        lineDataSets.add(lineDataSet2)
//        lineDataSets.add(lineDataSet3)
//        lineDataSets.add(lineDataSet4)
//        lineDataSets.add(lineDataSet5)
//        lineDataSets.add(lineDataSet6)
//
//        //④LineDataにLineDataSet格納
//        val lineData = LineData(lineDataSets)
//        //⑤LineChartにLineData格納
//        val lineChart = findViewById<LineChart>(R.id.lineChartExample2)
//        lineChart.data = lineData
//        lineChart.description.text = "今月の結果"
//
//        //⑥Chartのフォーマット指定
//        //X軸の設定 → フォーマット指定処理
//        lineChart.xAxis.apply {
//            isEnabled = true
//            textColor = Color.BLACK
//        }
//        //左Y軸の設定 → フォーマット指定処理
//        lineChart.axisLeft.apply {
//            isEnabled = true
//            textColor = Color.BLACK
//        }
//        //右Y軸の設定 → フォーマット指定処理
//        lineChart.axisRight.apply {
//            isEnabled = false
//        }
//        //lineChart更新
//        lineChart.invalidate()
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

}

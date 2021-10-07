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
import com.github.mikephil.charting.utils.ColorTemplate
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
        var num = 0
        var num2 = 0
        var num3 = 0
        var num4 = 0
        var num5 = 0
        var num6 = 0

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

//        var cor = arrayListCorrect.sum()
//        var inc = arrayListIncorrect.sum()
        var text2 = findViewById<TextView>(R.id.text2)
        text2.setText(num.toString())
        var text3 = findViewById<TextView>(R.id.text3)
        text3.setText(num2.toString())
        var text4 = findViewById<TextView>(R.id.text4)
        text4.setText(num3.toString())
        var text5 = findViewById<TextView>(R.id.text5)
        text5.setText(num4.toString())
        var text6 = findViewById<TextView>(R.id.text6)
        text6.setText(num5.toString())
        var text7 = findViewById<TextView>(R.id.text7)
        text7.setText(num6.toString())

        val barChart = findViewById<BarChart>(R.id.barChartExample2)
        val Data = ArrayList<BarEntry>()

//        Data.add(num)
//        Data.add(num2)
//        Data.add(num3)
//        Data.add(num4)
//        Data.add(num5)
//        Data.add(num6)

        Data.add(BarEntry(num.toFloat(), 10f))
        Data.add(BarEntry(num2.toFloat(), 20f))
        Data.add(BarEntry(num3.toFloat(), 30f))
        Data.add(BarEntry(num4.toFloat(), 40f))
        Data.add(BarEntry(num5.toFloat(), 50f))
        Data.add(BarEntry(num6.toFloat(), 60f))
        val bardataset = BarDataSet(Data, "教科ごとの正解数")
        barChart.animateY(50)
        val data = BarData(bardataset)
        bardataset.setColors(*ColorTemplate.COLORFUL_COLORS)
        barChart.setData(data)
//        //⑥Chartのフォーマット指定
//        //X軸の設定
//        barChart.xAxis.apply {
//            isEnabled = true
//            textColor = Color.BLACK
//        }
//        //⑦barchart更新
//        barChart.invalidate()
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

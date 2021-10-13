package com.example.spiikuseigame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.spiikuseigame.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*


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

        val barChart = findViewById<BarChart>(R.id.barChartExample2)
        val Data = ArrayList<BarEntry>()

        Data.add(BarEntry(1f, num.toFloat()))
        Data.add(BarEntry(2f, num2.toFloat()))
        Data.add(BarEntry(3f, num3.toFloat()))
        Data.add(BarEntry(4f, num4.toFloat()))
        Data.add(BarEntry(5f, num5.toFloat()))
        Data.add(BarEntry(6f, num6.toFloat()))

        //X軸レイアウト
        val xAxis: XAxis = barChart.getXAxis()
        //X軸に表示するLabelのリスト(最初の""は原点の位置)
        val labels = arrayOf("", "国語", "数学", "社会", "理科", "英語", "SPI")
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        //x軸の値表示を下の方に表示する。
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        //拡大縮小ができないようにする。
        barChart.setPinchZoom(false)
        //グラフの色
        val bardataset = BarDataSet(Data, "教科ごとの正解数")
        barChart.animateY(50)
        bardataset.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = BarData(bardataset)
        barChart.setData(data)
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

package com.example.spiikuseigame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.spiikuseigame.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class Graph : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //ホーム画面に遷移
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val back = Intent(this, MainActivity::class.java)
            startActivity(back)
        }

        //表示用サンプルデータの作成//
        val x = listOf<Float>(1f, 2f, 3f, 5f, 8f, 13f, 21f, 34f)//X軸データ
        val y1 = x.map{it}//Y軸データ1（X軸の1乗）
        val y2 = x.map{it*it}//Y軸データ2（X軸の2乗）

        //①Entryにデータ格納
        var entryList1 = mutableListOf<Entry>()//1本目の線
        var entryList2 = mutableListOf<Entry>()//2本目の線
        for(i in x.indices){
            entryList1.add(
                Entry(x[i], y1[i])
            )
            entryList2.add(
                Entry(x[i], y2[i])
            )
        }

        //LineDataSetのList
        val lineDataSets = mutableListOf<ILineDataSet>()
        //②線1本目のデータ格納
        val lineDataSet1 = LineDataSet(entryList1, "linear")
        //③線1本目のフォーマット指定
        lineDataSet1.color = Color.BLUE
        //②線2本目のデータ格納
        val lineDataSet2 = LineDataSet(entryList2, "square")
        //③線2本目のフォーマット指定
        lineDataSet2.color = Color.RED
        //リストに格納
        lineDataSets.add(lineDataSet1)
        lineDataSets.add(lineDataSet2)

        //④LineDataにLineDataSet格納
        val lineData = LineData(lineDataSets)
        //⑤LineChartにLineData格納
        val lineChart = findViewById<LineChart>(R.id.lineChartExample2)
        lineChart.data = lineData
        //⑥Chartのフォーマット指定
        //X軸の設定 → フォーマット指定処理
        lineChart.xAxis.apply {
            isEnabled = true
            textColor = Color.BLACK
        }
        //左Y軸の設定 → フォーマット指定処理
        lineChart.axisLeft.apply {
            isEnabled = true
            textColor = Color.BLACK
        }
        //右Y軸の設定 → フォーマット指定処理
        lineChart.axisRight.apply {
            isEnabled = false
        }
        //lineChart更新
        lineChart.invalidate()
    }
}
package com.example.spiikuseigame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.spiikuseigame.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
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

        //折れ線グラフ
        //表示用サンプルデータの作成//
        val x = listOf<Float>(1f, 2f, 3f, 5f, 8f, 13f, 21f, 34f)//X軸データ
        val y1 = x.map{it}//Y軸データ1（X軸の1乗）
        val y2 = x.map{it*it}//Y軸データ2（X軸の2乗）
        val y3 = x.map{it*7}//Y軸データ3（X軸の）
        val y4 = x.map{it*2}//Y軸データ4（X軸の）
        val y5 = x.map{it*10}//Y軸データ2（X軸の2乗）
        val y6 = x.map{it*5}//Y軸データ2（X軸の2乗）

        //①Entryにデータ格納
        var entryList1 = mutableListOf<Entry>()//1本目の線
        var entryList2 = mutableListOf<Entry>()//2本目の線
        var entryList3 = mutableListOf<Entry>()//3本目の線
        var entryList4 = mutableListOf<Entry>()//4本目の線
        var entryList5 = mutableListOf<Entry>()//5本目の線
        var entryList6 = mutableListOf<Entry>()//6本目の線

        for(i in x.indices){
            entryList1.add(
                Entry(x[i], y1[i])
            )
            entryList2.add(
                Entry(x[i], y2[i])
            )
            entryList3.add(
                Entry(x[i], y3[i])
            )
            entryList4.add(
                Entry(x[i], y4[i])
            )
            entryList5.add(
                Entry(x[i], y5[i])
            )
            entryList6.add(
                Entry(x[i], y6[i])
            )

        }

        //LineDataSetのList
        val lineDataSets = mutableListOf<ILineDataSet>()
        //②線1本目のデータ格納
        val lineDataSet1 = LineDataSet(entryList1, "国語")
        //③線1本目のフォーマット指定
        lineDataSet1.color = Color.GREEN
        //②線2本目のデータ格納
        val lineDataSet2 = LineDataSet(entryList2, "数学")
        //③線2本目のフォーマット指定
        lineDataSet2.color = Color.BLUE
        //②線3本目のデータ格納
        val lineDataSet3 = LineDataSet(entryList3, "理科")
        //③線3本目のフォーマット指定
        lineDataSet3.color = Color.rgb(255,0,255)
        //②線4本目のデータ格納
        val lineDataSet4 = LineDataSet(entryList4, "英語")
        //③線4本目のフォーマット指定
        lineDataSet4.color = Color.RED
        //②線5本目のデータ格納
        val lineDataSet5 = LineDataSet(entryList5, "社会")
        //③線5本目のフォーマット指定
        lineDataSet5.color = Color.YELLOW
        //②線6本目のデータ格納
        val lineDataSet6 = LineDataSet(entryList6, "SPI")
        //③線6本目のフォーマット指定
        lineDataSet6.color = Color.rgb(0,100,100)

        //リストに格納
        lineDataSets.add(lineDataSet1)
        lineDataSets.add(lineDataSet2)
        lineDataSets.add(lineDataSet3)
        lineDataSets.add(lineDataSet4)
        lineDataSets.add(lineDataSet5)
        lineDataSets.add(lineDataSet6)

        //④LineDataにLineDataSet格納
        val lineData = LineData(lineDataSets)
        //⑤LineChartにLineData格納
        val lineChart = findViewById<LineChart>(R.id.lineChartExample2)
        lineChart.data = lineData
        lineChart.description.text = "今月の結果"

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

        //棒グラフ
        //表示用サンプルデータの作成
        val x2 = listOf<Float>(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f)//X軸データ
        val y7 = x.map{it+200}//Y軸データ1（X軸の1乗）
        val y8 = x.map{it*it+10}//Y軸データ2（X軸の2乗）
        val y9 = x.map{it*2+300}//Y軸データ3
        val y10 = x.map{it+500}//Y軸データ4
        val y11 = x.map{it+100}//Y軸データ5
        val y12 = x.map{it*30}//Y軸データ6

        //①Entryにデータ格納
        var entryList7 = mutableListOf<BarEntry>()//1本目の棒
        var entryList8 = mutableListOf<BarEntry>()//2本目の棒
        var entryList9 = mutableListOf<BarEntry>()//3本目の棒
        var entryList10 = mutableListOf<BarEntry>()//4本目の棒
        var entryList11 = mutableListOf<BarEntry>()//5本目の棒
        var entryList12 = mutableListOf<BarEntry>()//6本目の棒

        for(i in x.indices){
            entryList7.add(
                BarEntry(x2[i], y7[i])
            )
            entryList8.add(
                BarEntry(x2[i], y8[i])
            )
            entryList9.add(
                BarEntry(x2[i], y9[i])
            )
            entryList10.add(
                BarEntry(x2[i], y10[i])
            )
            entryList11.add(
                BarEntry(x2[i], y11[i])
            )
            entryList12.add(
                BarEntry(x2[i], y12[i])
            )

        }

        //BarDataSetのリスト
        val barDataSets = mutableListOf<IBarDataSet>()
        //②棒1本目のデータ格納
        val barDataSet1 = BarDataSet(entryList7, "国語")
        //③棒1本目のフォーマット指定
        barDataSet1.color = Color.GREEN
        //②棒2本目のデータ格納
        val barDataSet2 = BarDataSet(entryList8, "数学")
        //③棒2本目のフォーマット指定
        barDataSet2.color = Color.BLUE
        //②棒2本目のデータ格納
        val barDataSet3 = BarDataSet(entryList9, "理科")
        //③棒2本目のフォーマット指定
        barDataSet3.color = Color.rgb(255,0,255)
        //②棒2本目のデータ格納
        val barDataSet4 = BarDataSet(entryList10, "英語")
        //③棒2本目のフォーマット指定
        barDataSet4.color = Color.RED
        //②棒2本目のデータ格納
        val barDataSet5 = BarDataSet(entryList11, "社会")
        //③棒2本目のフォーマット指定
        barDataSet5.color = Color.YELLOW
        //②棒2本目のデータ格納
        val barDataSet6 = BarDataSet(entryList12, "SPI")
        //③棒2本目のフォーマット指定
        barDataSet6.color = Color.rgb(0,100,100)

        //リストに格納
        barDataSets.add(barDataSet1)
        barDataSets.add(barDataSet2)
        barDataSets.add(barDataSet3)
        barDataSets.add(barDataSet4)
        barDataSets.add(barDataSet5)
        barDataSets.add(barDataSet6)


        //④BarDataにBarDataSet格納
        val barData = BarData(barDataSets)
        //棒のグルーピング用処理
        val xAxisSpan = 1f//データのX軸間隔
        val barNumber = 2//棒の本数
        val groupSpace = 0.2f//グループの間隔
        val barSpace = 0.05f//同グループの棒同士の間隔
        val barWidth = (xAxisSpan - groupSpace)/barNumber.toFloat() - barSpace//棒の幅
        barData.barWidth = barWidth//棒の幅をbarDataに指定
        //⑤BarChartにBarData格納
        val barChart = findViewById<BarChart>(R.id.barChartExample)
        barChart.data = barData
        barChart.description.text = "正解数・不正解数累計"
        //⑥Chartのフォーマット指定
        //X軸の設定
        barChart.xAxis.apply {
            isEnabled = true
            textColor = Color.BLACK
        }
        //棒のグルーピング
        val startX = barChart.barData.dataSets.first().xMin - 0.5f//Xの最小値 - 0.5
        barChart.groupBars(startX, groupSpace, barSpace)
        //⑦barChart更新
        barChart.invalidate()

    }
}
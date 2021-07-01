package com.example.spiikuseigame

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "graphs")
data class GraphData(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var sum: Long,
    var answer: Long
)
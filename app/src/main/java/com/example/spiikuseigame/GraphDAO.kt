package com.example.spiikuseigame

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GraphDAO {
    @Query("SELECT * FROM graphs WHERE sum LIKE :taskName")
    fun find(taskName: String): List<GraphData>

    @Insert
    fun insert(graph: GraphData)

    @Update
    fun update(graph: GraphData): Int

    @Delete
    fun delete(graph: GraphData): Int
}
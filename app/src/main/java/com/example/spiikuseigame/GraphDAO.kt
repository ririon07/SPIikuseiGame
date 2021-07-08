package com.example.spiikuseigame

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GraphDAO {
    @Query("select * from graphs")
    fun find(): LiveData<List<Graph>>

    @Insert
    fun insert(graph: Graph)

    @Update
    fun update(graph: Graph): Int

    @Delete
    fun delete(graph: Graph): Int
}
package com.example.spiikuseigame

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GraphDAO {
    @Query("select * from graph")
    fun getAll(): LiveData<List<Graph>>

    @Insert
    fun insert(graph: Graph)

    @Update
    fun update(task: Graph): Int

    @Delete
    fun delete(task: Graph): Int
}
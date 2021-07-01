package com.example.spiikuseigame

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GraphDAO {

    @Insert
    fun insert(graph: Graph)

    @Query("select * from graphdata")
    fun getAll(): LiveData<List<Graph>>
}
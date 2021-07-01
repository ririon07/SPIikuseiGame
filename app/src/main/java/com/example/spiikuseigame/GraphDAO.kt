package com.example.spiikuseigame

import androidx.room.*

@Dao
interface TasksDao {
    @Query("SELECT * FROM tasks WHERE name LIKE :taskName")
    fun find(taskName: String): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Update
    fun update(task: Task): Int

    @Delete
    fun delete(task: Task): Int
}
package com.example.spiikuseigame

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import android.content.Context

@Database(entities = [GraphData::class],version = 1,exportSchema = false)
abstract class GraphDataBase: RoomDatabase() {
    abstract fun gDAO(): GraphDAO
    companion object {
        private var INSTANCE: GraphDataBase? = null
        private val lock = Any()

        fun getInstance(context: Context): GraphDataBase =
            INSTANCE ?: synchronized(lock) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    GraphDataBase::class.java, "graphs.db"
                )
                    .build()
                    .also { INSTANCE = it }
            }


    }
}
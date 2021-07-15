package com.example.spiikuseigame

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Graph::class],version = 1,exportSchema = false)
abstract class GraphDataBase: RoomDatabase() {
/*    abstract fun GraphDAO(): GraphDAO
    companion object {
        private var INSTANCE: GraphDataBase? = null
        private val lock = Any()

        fun getInstance(context: Context): GraphDataBase =
            INSTANCE ?: synchronized(lock) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    GraphDataBase::class.java, "graph.db"
                )
                    .build()
                    .also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }

        //   val testDatabase = GraphDataBase.getInstance(context)
        //   val dao = testDatabase.GraphDAO()
        // Dao のメソッドをコールすることにより DB の操作を実行
        //val test = dao.find("hoge")
        //dao.insert(newtest)
        //dao.delete(deltest)
    }*/
}
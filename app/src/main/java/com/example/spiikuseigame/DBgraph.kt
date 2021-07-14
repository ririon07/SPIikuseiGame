//package com.example.spiikuseigame
//
//import android.content.ContentValues
//import android.graphics.Bitmap
//import android.util.Log
//import java.io.ByteArrayOutputStream
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//
//
//class DBgraph {
//    private val dbName: String = "SampleDB"
//    private val tableName: String = "SampleTable"
//    private val dbVersion: Int = 1
//
//    private fun insertData(id: String, name: String, type: Int, bitmap: Bitmap) {
//        try {
//            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion);
//            val database = dbHelper.writableDatabase
//
//            val values = ContentValues()
//            values.put("id", id)
//            values.put("name", name)
//            values.put("type", type)
//            val byteArrayOutputStream = ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
//            val bytes = byteArrayOutputStream.toByteArray()
//            values.put("image", bytes)
//
//            database.insertOrThrow(tableName, null, values)
//        }catch(exception: Exception) {
//            Log.e("insertData", exception.toString())
//        }
//    }
//    private fun deleteData(whereId: String) {
//        try {
//            val dbHelper = SQLiteOpen(applicationContext, dbName, null, dbVersion);
//            val database = dbHelper.writableDatabase
//
//            val whereClauses = "id = ?"
//            val whereArgs = arrayOf(whereId)
//            database.delete(tableName, whereClauses, whereArgs)
//        }catch(exception: Exception) {
//            Log.e("deleteData", exception.toString())
//        }
//    }
//}
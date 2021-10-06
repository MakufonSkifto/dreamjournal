package com.emir.dreamjournal

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE dreams (" +
                "id INTEGER NOT NULL," +
                "title TEXT NOT NULL," +
                "description TEXT NOT NULL," +
                "date TEXT NOT NULL);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(id: Int, title: String, desc: String, date: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2, title)
        contentValues.put(COL_3, desc)
        contentValues.put(COL_4, date)
        db.insert(TABLE_NAME, null, contentValues)
    }

    fun updateData(id: String, name: String, surname: String, marks: String) : Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2, name)
        contentValues.put(COL_3, surname)
        contentValues.put(COL_4, marks)
        db.update(TABLE_NAME, contentValues, "id = ?", arrayOf(id))
        return true
    }

    fun deleteData(id : String) : Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME,"id = ?", arrayOf(id))
    }

    val allData : Cursor
        get() {
            val db = this.writableDatabase
            return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        }

    fun rawQuery(query: String) : Cursor? {
        val db = this.writableDatabase
        return db.rawQuery(query, null)
    }

    companion object {
        const val DATABASE_NAME = "dreams.sqlite3"
        const val TABLE_NAME = "dreams"
        const val COL_1 = "id"
        const val COL_2 = "title"
        const val COL_3 = "description"
        const val COL_4 = "date"
    }
}
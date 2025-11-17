package com.example.gestordeclientes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "clientes.db", null, 1) {

    private val TABLE = "clientes"
    private val COL_ID = "id"
    private val COL_NOMBRE = "nombre"
    private val COL_TELEFONO = "telefono"
    private val COL_EMAIL = "email"

    override fun onCreate(db: SQLiteDatabase) {
        val sql = """
            CREATE TABLE $TABLE(
            $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COL_NOMBRE TEXT,
            $COL_TELEFONO TEXT,
            $COL_EMAIL TEXT
            )
        """
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE")
        onCreate(db)
    }

    fun insertarCliente(nombre: String, telefono: String, email: String): Long {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put(COL_NOMBRE, nombre)
            put(COL_TELEFONO, telefono)
            put(COL_EMAIL, email)
        }
        return db.insert(TABLE, null, cv)
    }

    fun obtenerClientes(): Cursor {
        return readableDatabase.rawQuery("SELECT * FROM $TABLE", null)
    }

    fun actualizarCliente(id: Int, nombre: String, telefono: String, email: String): Int {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put(COL_NOMBRE, nombre)
            put(COL_TELEFONO, telefono)
            put(COL_EMAIL, email)
        }
        return db.update(TABLE, cv, "$COL_ID=?", arrayOf(id.toString()))
    }

    fun eliminarCliente(id: Int): Int {
        return writableDatabase.delete(TABLE, "$COL_ID=?", arrayOf(id.toString()))
    }
}

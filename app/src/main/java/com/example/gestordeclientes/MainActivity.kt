package com.example.gestordeclientes

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ClienteAdapter.OnItemClickListener {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var rvClientes: RecyclerView
    private lateinit var adapter: ClienteAdapter
    private var clientes = mutableListOf<Cliente>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        rvClientes = findViewById(R.id.rvClientes)
        rvClientes.layoutManager = LinearLayoutManager(this)

        adapter = ClienteAdapter(clientes, this)
        rvClientes.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fabAgregar)
        fab.setOnClickListener {
            startActivity(Intent(this, AddEditClienteActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        cargarClientes()
    }

    private fun cargarClientes() {
        clientes.clear()
        val cursor: Cursor = dbHelper.obtenerClientes()
        if (cursor.moveToFirst()) {
            do {
                clientes.add(
                    Cliente(
                        id = cursor.getInt(0),
                        nombre = cursor.getString(1),
                        telefono = cursor.getString(2),
                        email = cursor.getString(3)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(cliente: Cliente) {
        val intent = Intent(this, AddEditClienteActivity::class.java)
        intent.putExtra("id", cliente.id)
        intent.putExtra("nombre", cliente.nombre)
        intent.putExtra("telefono", cliente.telefono)
        intent.putExtra("email", cliente.email)
        startActivity(intent)
    }

    override fun onItemLongClick(cliente: Cliente) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar cliente")
            .setMessage("¿Seguro que deseas eliminar a ${cliente.nombre}?")
            .setPositiveButton("Sí") { _, _ ->
                dbHelper.eliminarCliente(cliente.id)
                cargarClientes()
            }
            .setNegativeButton("No", null)
            .show()
    }
}

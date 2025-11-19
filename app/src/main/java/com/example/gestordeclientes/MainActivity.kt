package com.example.gestordeclientes

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ClienteAdapter.OnItemClickListener {

    private lateinit var etBusqueda: EditText
    private lateinit var tvContador: TextView
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var rvClientes: RecyclerView
    private lateinit var adapter: ClienteAdapter
    private var clientes = mutableListOf<Cliente>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBusqueda = findViewById(R.id.etBusqueda)
        tvContador = findViewById(R.id.tvContador)
        dbHelper = DatabaseHelper(this)
        rvClientes = findViewById(R.id.rvClientes)
        rvClientes.layoutManager = LinearLayoutManager(this)

        adapter = ClienteAdapter(clientes, this)
        rvClientes.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fabAgregar)
        fab.setOnClickListener {
            startActivity(Intent(this, AddEditClienteActivity::class.java))
        }

        etBusqueda.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filtrarClientes(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
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

        // Actualizar contador
        tvContador.text = "Clientes: ${clientes.size}"
    }

    private fun filtrarClientes(texto: String) {
        val filtrados = clientes.filter {
            it.nombre.contains(texto, ignoreCase = true) ||
                    it.email.contains(texto, ignoreCase = true)
        }
        adapter = ClienteAdapter(filtrados.toMutableList(), this)
        rvClientes.adapter = adapter
        tvContador.text = "Clientes: ${filtrados.size}"
    }

    // --- BOTÓN EDITAR ---
    override fun onEditClick(cliente: Cliente) {
        val intent = Intent(this, AddEditClienteActivity::class.java)
        intent.putExtra("id", cliente.id)
        intent.putExtra("nombre", cliente.nombre)
        intent.putExtra("telefono", cliente.telefono)
        intent.putExtra("email", cliente.email)
        startActivity(intent)
    }

    // --- BOTÓN ELIMINAR ---
    override fun onDeleteClick(cliente: Cliente) {
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

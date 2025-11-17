package com.example.gestordeclientes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddEditClienteActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private var clienteId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_cliente)

        dbHelper = DatabaseHelper(this)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        // Comprobar si viene un cliente para editar
        clienteId = intent.getIntExtra("id", 0)
        if (clienteId != 0) {
            etNombre.setText(intent.getStringExtra("nombre"))
            etTelefono.setText(intent.getStringExtra("telefono"))
            etEmail.setText(intent.getStringExtra("email"))
        }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val telefono = etTelefono.text.toString()
            val email = etEmail.text.toString()

            if (nombre.isBlank() || telefono.isBlank() || email.isBlank()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val exito = if (clienteId != null && clienteId != 0) {
                dbHelper.actualizarCliente(clienteId!!, nombre, telefono, email)
            } else {
                dbHelper.insertarCliente(nombre, telefono, email)
            }

            if (exito != 0L && exito != -1L) {
                Toast.makeText(this, "Cliente guardado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

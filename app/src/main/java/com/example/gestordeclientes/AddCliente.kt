package com.example.gestordeclientes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddClienteActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cliente)

        dbHelper = DatabaseHelper(this)

        val nombre = findViewById<EditText>(R.id.editNombre)
        val telefono = findViewById<EditText>(R.id.editTelefono)
        val email = findViewById<EditText>(R.id.editEmail)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val nombreTxt = nombre.text.toString()
            val telefonoTxt = telefono.text.toString()
            val emailTxt = email.text.toString()

            if (nombreTxt.isNotEmpty() && telefonoTxt.isNotEmpty() && emailTxt.isNotEmpty()) {
                val exito = dbHelper.insertarCliente(nombreTxt, telefonoTxt, emailTxt)
                if (exito) {
                    Toast.makeText(this, "Cliente añadido correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

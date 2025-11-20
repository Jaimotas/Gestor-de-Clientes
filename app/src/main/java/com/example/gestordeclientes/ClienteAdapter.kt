package com.example.gestordeclientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClienteAdapter(
    private val clientes: MutableList<Cliente>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    interface OnItemClickListener {
        fun onEditClick(cliente: Cliente)
        fun onDeleteClick(cliente: Cliente)
    }

    inner class ClienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvTelefono: TextView = itemView.findViewById(R.id.tvTelefono)
        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cliente, parent, false)
        return ClienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = clientes[position]

        holder.tvNombre.text = cliente.nombre
        holder.tvTelefono.text = cliente.telefono
        holder.tvEmail.text = cliente.email

        holder.btnEditar.setOnClickListener {
            listener.onEditClick(cliente)
        }

        holder.btnEliminar.setOnClickListener {
            listener.onDeleteClick(cliente)
        }
    }

    override fun getItemCount(): Int = clientes.size
}

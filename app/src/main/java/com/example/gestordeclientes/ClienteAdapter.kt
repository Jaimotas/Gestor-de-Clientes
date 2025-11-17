package com.example.gestordeclientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClienteAdapter(
    private val clientes: MutableList<Cliente>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(cliente: Cliente)
        fun onItemLongClick(cliente: Cliente)
    }

    inner class ClienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvTelefono: TextView = itemView.findViewById(R.id.tvTelefono)
        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(clientes[adapterPosition])
            }
            itemView.setOnLongClickListener {
                listener.onItemLongClick(clientes[adapterPosition])
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cliente, parent, false)
        return ClienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val c = clientes[position]
        holder.tvNombre.text = c.nombre
        holder.tvTelefono.text = c.telefono
        holder.tvEmail.text = c.email
    }

    override fun getItemCount(): Int = clientes.size
}


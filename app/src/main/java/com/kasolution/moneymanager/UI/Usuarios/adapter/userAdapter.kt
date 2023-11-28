package com.kasolution.moneymanager.UI.Usuarios.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kasolution.moneymanager.R
import com.kasolution.moneymanager.databinding.ItemUsuarioBinding
import com.kasolution.moneymanager.domain.model.Usuario

class userAdapter(
    private val listaRecibida: ArrayList<Usuario>,
    private val OnClickListener: (Usuario) -> Unit,
    private val OnClickUpdate: (Usuario, Int) -> Unit,
    private val OnClickDelete: (Int, Int) -> Unit
) : RecyclerView.Adapter<userAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userAdapter.ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        Log.i("datos","el tamaño de lista es: ${listaRecibida.size}")
        return listaRecibida.size
    }

    override fun onBindViewHolder(holder: userAdapter.ViewHolder, position: Int) {
        val item = listaRecibida[position]
        holder.render(item, OnClickListener, OnClickUpdate, OnClickDelete)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUsuarioBinding.bind(view)
        fun render(
            lista: Usuario,
            OnClickListener: (Usuario) -> Unit,
            OnClickUpdate: (Usuario, Int) -> Unit,
            OnClickDelete: (Int, Int) -> Unit
        ) {
            binding.tvNombre.text = lista.nombre
            binding.tvUser.text = lista.user
            binding.tvTipo.text = lista.tipo
            Log.i("datos","los datos ${lista.nombre + lista.user + lista.tipo}")
        }
    }
}

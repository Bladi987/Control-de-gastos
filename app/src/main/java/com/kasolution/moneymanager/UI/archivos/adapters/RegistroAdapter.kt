package com.kasolution.moneymanager.UI.archivos.adapters

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.kasolution.moneymanager.R
import com.kasolution.moneymanager.databinding.ItemArchivosBinding
import com.kasolution.moneymanager.domain.model.Registros

class RegistroAdapter(
    private val listaRecibida: ArrayList<Registros>,
    private val OnClickListener: (Registros) -> Unit,
    private val OnClickUpdate: (Registros, Int) -> Unit,
    private val OnClickDelete: (Int, Int) -> Unit
) : RecyclerView.Adapter<RegistroAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_registros, parent, false)
        return ViewHolder(layoutInflater)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaRecibida[position]
        holder.render(item, OnClickListener,OnClickUpdate,OnClickDelete)
    }

    override fun getItemCount(): Int {
        return listaRecibida.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemArchivosBinding.bind(view)

        fun render(
            lista: Registros,
            OnClickListener: (Registros) -> Unit,
            OnClickUpdate: (Registros, Int) -> Unit,
            OnClickDelete: (Int, Int) -> Unit
        ) {
            binding.lblNombre.text = lista.Nombre
            if (lista.selected) {
                binding.imgselected.setImageResource(R.drawable.ic_predeterminado)
            } else binding.imgselected.setImageResource(0)

            itemView.setOnLongClickListener() {
                val popupMenu = PopupMenu(binding.imgIcon.context, itemView)
                popupMenu.menuInflater.inflate(R.menu.pop_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.menu_item_predeterminado -> {
                            OnClickListener(lista)
                            for (i in 0 until listaRecibida.size) {
                                listaRecibida[i].selected = false
                                if (listaRecibida[i].Nombre == lista.Nombre) lista.selected = true
                            }
                            notifyDataSetChanged()
                            true
                        }
                        R.id.menu_item_renonbrar -> {
                            OnClickUpdate(lista,position)
                            true
                        }
                        R.id.menu_item_eliminar -> {
                            OnClickDelete(lista.id!!,position)
                            true
                        }
                        // Agregar más opciones del menú si es necesario
                        else -> false
                    }
                }

                // Agrega los listener para las opciones del menú si es necesario
                popupMenu.gravity = Gravity.END
                popupMenu.show()
                true
            }
        }
    }
}
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
import com.kasolution.moneymanager.domain.model.Archivos

class ArchivoAdapter(
    private val listaRecibida: ArrayList<Archivos>,
    private val OnClickListener: (Archivos) -> Unit,
    private val OnClickUpdate: (Archivos, Int) -> Unit,
    private val OnClickDelete: (Int, Int) -> Unit
) : RecyclerView.Adapter<ArchivoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchivoAdapter.ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_archivos, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return listaRecibida.size
    }

    override fun onBindViewHolder(holder: ArchivoAdapter.ViewHolder, position: Int) {
        val item = listaRecibida[position]
        holder.render(item, OnClickListener, OnClickUpdate, OnClickDelete)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemArchivosBinding.bind(view)

        fun render(

            lista:Archivos,
            OnClickListener: (Archivos) -> Unit,
            OnClickUpdate: (Archivos, Int) -> Unit,
            OnClickDelete: (Int, Int) -> Unit
        ) {
            itemView.setOnClickListener { OnClickListener(lista) }
            binding.lblNombre.text = lista.Nombre
            if (lista.selected == true) {
                binding.imgselected.setImageResource(R.drawable.ic_predeterminado)
            } else binding.imgselected.setImageResource(0)

            itemView.setOnLongClickListener() {
                val popupMenu = PopupMenu(binding.imgIcon.context, itemView)
                popupMenu.menuInflater.inflate(R.menu.pop_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.menu_item_predeterminado -> {
                            // Acción para la opción 1
                            val preferencesValueConexion =
                                binding.imgIcon.context.getSharedPreferences(
                                    "valuesConexion",
                                    Context.MODE_PRIVATE
                                )
                            val editor = preferencesValueConexion.edit()
                            editor.apply() {
                                putString("ARCHIVO_SELECTED", lista.Nombre)
                            }.apply()
                            for (i in 0 until listaRecibida.size) {
                                listaRecibida[i].selected = false
                                if (listaRecibida[i].Nombre == lista.Nombre) lista.selected = true
                            }
                            notifyDataSetChanged()
                            true
                        }
                        R.id.menu_item_renonbrar -> {

                            OnClickUpdate(lista, position)
                            true
                        }
                        R.id.menu_item_eliminar -> {
                            OnClickDelete(lista.id!!, position)
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
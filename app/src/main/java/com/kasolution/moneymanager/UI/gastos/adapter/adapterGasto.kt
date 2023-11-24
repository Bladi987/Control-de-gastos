package com.kasolution.moneymanager.UI.gastos.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.kasolution.moneymanager.R
import com.kasolution.moneymanager.UI.pojos.itemGastos
import com.kasolution.moneymanager.databinding.ItemGastosBinding

class adapterGasto(
    private val arrayList: ArrayList<itemGastos>,
    private val onclickListener: (itemGastos) -> Unit,
    private val OnClickUpdate: (itemGastos,Int) -> Unit,
    private val OnClickDelete: (itemGastos,Int) -> Unit
) : RecyclerView.Adapter<adapterGasto.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_gastos, parent, false)
        return viewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = arrayList[position]
        holder.render(item, onclickListener,OnClickUpdate,OnClickDelete)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    //zona del viewHolder dentro de la misma clase
    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemGastosBinding.bind(view)
        fun render(lista: itemGastos,
                   onclickListener: (itemGastos) -> Unit,
                   OnClickUpdate: (itemGastos,Int) -> Unit,
                   OnClickDelete: (itemGastos,Int) -> Unit) {

            binding.lblid.text = lista.id
            binding.lblfecha.text = lista.fecha
            binding.lblciudad.text = lista.ciudad
            binding.lbltipoDoc.text = lista.tipoDoc
            binding.lblNroDoc.text = lista.nroDoc
            binding.lblProveedor.text = lista.proveedor
            binding.lblDescripcion.text = lista.descripcion
            binding.lbltipoGastoDetalle.text=lista.tipoGasto
            binding.lbltipoGasto.text=lista.sustento
            binding.lblMonto.text =String.format("S/ %.2f", lista.monto.toDouble())

            if (lista.nroDoc.isEmpty()) binding.cvComprobante.visibility = View.GONE

            itemView.setOnClickListener() {
                //onclickListener(lista)
            }
            itemView.setOnLongClickListener(){
                val popupMenu = PopupMenu(binding.lblMonto.context, itemView)
                popupMenu.menuInflater.inflate(R.menu.pop_menu_gastos, popupMenu.menu)

                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.itVer -> {
                            // Acción para la opción 1
                            onclickListener(lista)
                            true
                        }
                        R.id.itEditar -> {
                            OnClickUpdate(lista,adapterPosition)
                            true
                        }
                        R.id.itEliminar -> {
                            OnClickDelete(lista,adapterPosition)
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
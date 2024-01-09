package com.kasolution.moneymanager.UI.archivos.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kasolution.moneymanager.R
import com.kasolution.moneymanager.UI.archivos.adapters.RegistroAdapter
import com.kasolution.moneymanager.UI.archivos.viewmodel.ArchivoViewModel
import com.kasolution.moneymanager.databinding.FragmentArchivoDetalleBinding
import com.kasolution.moneymanager.domain.model.Registros
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class ArchivoDetalleFragment : Fragment() {
    private var _binding: FragmentArchivoDetalleBinding? = null
    private val binding get() = _binding!!
    private val archivosViewModel: ArchivoViewModel by viewModels()
    private lateinit var lista: ArrayList<Registros>
    private lateinit var lmanager: LinearLayoutManager
    private lateinit var adapter: RegistroAdapter

    private var ArchivoId = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(ARG_ARCHIVO_ID)?.let { archivoId ->
            Log.i("BladiDev", "el dato recibido es:$archivoId")
            ArchivoId = archivoId
        }

        lista = ArrayList()
        initUI()

        archivosViewModel.ListarRegistro(ArchivoId)
        archivosViewModel.listaRegistro.observe(
            getViewLifecycleOwner(),
            Observer { listaRegistros ->
                lista.addAll(listaRegistros)
                adapter.notifyDataSetChanged()
            })
        binding.fbAddRegistro.setOnClickListener() {
            dialogFile()
        }
    }

    private fun initUI() {
        lmanager = LinearLayoutManager(context)
        adapter = RegistroAdapter(
            listaRecibida = lista,
            OnClickListener = { Registros -> onItemDefault(Registros) },
            OnClickUpdate = { Registros, position -> onItemUpdate(Registros, position) },
            OnClickDelete = { id, position -> onDeleteItem(id, position) })
        binding.rvListaRegistros.layoutManager = lmanager
        binding.rvListaRegistros.adapter = adapter
    }

    private fun onDeleteItem(id: Int, position: Int) {
        archivosViewModel.deleteRegistro(id)
        lista.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    private fun onItemUpdate(itemRegistro: Registros, position: Int) {
        dialogFile(itemRegistro, position)
    }

    private fun onItemDefault(itemRegistro: Registros) {
        Toast.makeText(requireContext(),"item seleccionado",Toast.LENGTH_SHORT).show()
        archivosViewModel.unselected()
        Log.i("BladiDev","${itemRegistro.id} ${itemRegistro.Nombre} ${itemRegistro.Estado},$ArchivoId")
        archivosViewModel.updateRegistro(
            itemRegistro.id,
            itemRegistro.Nombre,
            "libre",
            ArchivoId.toString(),
            true
        )
    }

    private fun dialogFile(
        itemRegistro: Registros = Registros(0, "", "", ArchivoId.toString(), false),
        position: Int = -1
    ) {
        var anim1 = AnimationUtils.loadAnimation(context, R.anim.bounce3)
        val view = View.inflate(context, R.layout.dialog_new_register, null)
        val btnSave = view.findViewById<Button>(R.id.btnCrear)
        val btnCancel = view.findViewById<Button>(R.id.btn_dialognwsCancel)
        val txtoldname = view.findViewById<TextView>(R.id.tvold_name)
        val txtnombre = view.findViewById<EditText>(R.id.tvname)
        val cbpredeterminado = view.findViewById<CheckBox>(R.id.cbPredeterminado)
        //analisis precarga
        if (position != -1) {
            txtoldname.visibility = View.VISIBLE
            txtoldname.text = itemRegistro.Nombre
            btnSave.text = "Modificar"
            txtnombre.hint = "Ingrese Nuevo Nombre"
        }

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
        view.animation = anim1
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)


        btnSave.setOnClickListener() {
            if (txtnombre.text.isEmpty()) {
                //si el campo de texto esta vacio
                txtnombre.setError("Ingrese un nombre")
            } else {
                // el campo txtnombre tiene contenido
                //validamos que accion realizaremos
                if (position == -1) {
                    // realizamos una insercion
                    archivosViewModel.insertRegistro(
                        txtnombre.text.toString(),
                        "libre",
                        ArchivoId.toString(),
                        cbpredeterminado.isChecked
                    )

                    lista.add(
                        Registros(
                            0,
                            txtnombre.text.toString(),
                            "libre",
                            ArchivoId.toString(),
                            cbpredeterminado.isChecked
                        )
                    )
                    adapter.notifyItemInserted(lista.size - 1)
                    lmanager.scrollToPositionWithOffset(lista.size - 1, 10)
                    dialog.dismiss()
                } else {
                    //realizamos una actualiacion del registro
                    archivosViewModel.updateRegistro(
                        itemRegistro.id,
                        txtnombre.text.toString(),
                        "libre",
                        ArchivoId.toString(),
                        cbpredeterminado.isChecked
                    )
                    //actualizar la lista en el recyclerView
                    lista[position] = Registros(
                        itemRegistro.id,
                        txtnombre.text.toString(),
                        "libre",
                        ArchivoId.toString(),
                        itemRegistro.selected
                    )
                    adapter.notifyItemChanged(position)
                    dialog.dismiss()
                }
            }
        }
        btnCancel.setOnClickListener() {
            dialog.dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArchivoDetalleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    companion object {
        const val ARG_ARCHIVO_ID = "archivo_id"
    }

}
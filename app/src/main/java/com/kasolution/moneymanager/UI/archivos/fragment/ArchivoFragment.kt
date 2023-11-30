package com.kasolution.moneymanager.UI.archivos.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kasolution.moneymanager.R
import com.kasolution.moneymanager.UI.Usuarios.adapter.userAdapter
import com.kasolution.moneymanager.UI.archivos.adapters.ArchivoAdapter
import com.kasolution.moneymanager.UI.archivos.viewmodel.ArchivoViewModel
import com.kasolution.moneymanager.UI.pojos.itemArchivo
import com.kasolution.moneymanager.databinding.FragmentArchivoBinding
import com.kasolution.moneymanager.domain.model.Archivos
import com.kasolution.moneymanager.domain.model.Usuario
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class ArchivoFragment : Fragment() {
    private var _binding: FragmentArchivoBinding? = null
    private val binding get() = _binding!!
    val archivosViewModel: ArchivoViewModel by viewModels()
    private lateinit var glmanager: GridLayoutManager
    private lateinit var adapter: ArchivoAdapter
    private lateinit var lista: ArrayList<Archivos>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lista = ArrayList()
        initUI()

        binding.fbAddArchivo.setOnClickListener() {
            dialogFile()
        }
        archivosViewModel.ListarArchivo()
        archivosViewModel.listaArchivo.observe(getViewLifecycleOwner(), Observer { listaArchivos ->
            lista.addAll(listaArchivos)
            adapter.notifyDataSetChanged()
        })

    }

    private fun initUI() {
        val columnWidthDp = 400
        val columns = resources.displayMetrics.widthPixels / columnWidthDp
        glmanager = GridLayoutManager(context, columns)
        adapter = ArchivoAdapter(
            listaRecibida = lista,
            OnClickListener = { itemArchivo -> onItemSelected(itemArchivo) },
            OnClickUpdate = { itemArchivo, position -> onItemUpdate(itemArchivo, position) },
            OnClickDelete = { id, position -> onDeleteItem(id, position) })
        binding.rvListaArchivos.layoutManager = glmanager
        binding.rvListaArchivos.adapter = adapter
    }

    private fun onDeleteItem(id: Int, position: Int) {
        archivosViewModel.deleteArchivo(id)
        lista.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    private fun onItemUpdate(itemArchivo: Archivos, position: Int) {
        dialogFile(itemArchivo, position)
    }

    private fun onItemSelected(itemArchivo: Archivos) {

    }

    private fun dialogFile(
        itemFileR: Archivos = Archivos(0, "", "", false),
        position: Int = -1
    ) {
        var anim1 = AnimationUtils.loadAnimation(context, R.anim.bounce3)
        val view = View.inflate(context, R.layout.dialog_add_archivos, null)
        val btnSave = view.findViewById<Button>(R.id.btnGuardar)
        val btnCancel = view.findViewById<Button>(R.id.btnCancelar)
        val txtnombre = view.findViewById<EditText>(R.id.etNombre)
        val txtDescripcion = view.findViewById<EditText>(R.id.etDescripcion)
        if (position != -1) {
            txtnombre.setText(itemFileR.Nombre)

            txtDescripcion.setText(itemFileR.Descripcion)
        }

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
        view.animation = anim1
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)


        btnSave.setOnClickListener() {
            if (txtnombre.text.toString() == "") {
                txtnombre.setError("Campo requerido")
            } else if (txtDescripcion.text.isEmpty()) {
                txtDescripcion.setError("Campo requerido")
            } else {

                if (position == -1) {
                    //insertamos un registro nuevo en room
                    archivosViewModel.insertArchivo(
                        txtnombre.text.toString(),
                        txtDescripcion.text.toString()
                    )
                    // agregamos un registro a la lista del recyclerView
                    lista.add(
                        Archivos(
                            id.toInt(),
                            txtnombre.text.toString(),
                            txtDescripcion.text.toString(),
                            false
                        )
                    )
//                Toast.makeText(context,"Registro exitoso con id $id", Toast.LENGTH_SHORT).show()
                    Log.i("tamano", lista.size.toString())

                    adapter.notifyItemInserted(lista.size - 1)
                    glmanager.scrollToPositionWithOffset(lista.size - 1, 10)
                } else {
                    //modificar el registro en room
                    archivosViewModel.updateArchivo(
                        itemFileR.id,
                        txtnombre.text.toString(),
                        txtDescripcion.text.toString()
                    )
                    //actualizar la lista en el recyclerView
                    lista[position] = Archivos(
                        itemFileR.id,
                        txtnombre.text.toString(),
                        txtDescripcion.text.toString(),
                        itemFileR.selected
                    )
                    adapter.notifyItemChanged(position)

                }

                dialog.dismiss()
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
        _binding = FragmentArchivoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
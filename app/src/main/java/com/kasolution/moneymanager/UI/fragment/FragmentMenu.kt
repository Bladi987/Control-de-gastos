package com.kasolution.moneymanager.UI.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.kasolution.moneymanager.R
import com.kasolution.moneymanager.UI.archivos.ArchivoActivity
import com.kasolution.moneymanager.UI.dashboard.adapter.adapterListMenu
import com.kasolution.moneymanager.UI.gastos.view.GastosActivity
import com.kasolution.moneymanager.UI.liquidaciones.LiquidacionesActivity
import com.kasolution.moneymanager.UI.settings.SettingsActivity
import com.kasolution.moneymanager.databinding.FragmentMenuBinding
import com.kasolution.moneymanager.domain.model.itemGrid

class FragmentMenu : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var Lista: ArrayList<itemGrid>
    private var key: String? = null

    private lateinit var glmanager: GridLayoutManager
    private lateinit var adapterlistMenu: adapterListMenu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Lista=llenarDatosMenu()
        val bundle=arguments
        if (bundle != null && bundle.containsKey("key")) {
            Lista = llenarDatosMenu()

            Log.i("recibido", bundle.getString("key").toString())
            // Ahora puedes usar listaDeItems en tu Fragment
        }else{
            Toast.makeText(context,"no se llenaron los datos",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        init()
        return view
    }

    private fun init() {
        if (Lista!=null){
            val columnWidthDp = 400
            val columns = resources.displayMetrics.widthPixels / columnWidthDp
            glmanager = GridLayoutManager(context, columns)
            adapterlistMenu = adapterListMenu(
                listaRecibida = Lista,
                OnClickListener = { itemGrid -> onItemSelected(itemGrid) })
            binding.rvMenu.layoutManager = glmanager
            binding.rvMenu.adapter = adapterlistMenu
        }
    }

    private fun onItemSelected(itemGrid: itemGrid) {
        when(itemGrid.name){
            "Registros"-> {
                val i=Intent(activity, GastosActivity::class.java)
                startActivity(i) }
            "Archivos"->{
                val i=Intent(activity, ArchivoActivity::class.java)
                startActivity(i) }
            "Liquidaciones"->{
                val i=Intent(activity, LiquidacionesActivity::class.java)
                startActivity(i) }
            "Ajustes"->{
                val i=Intent(activity, SettingsActivity::class.java)
                startActivity(i) }
        }

    }
    fun llenarDatosMenu():ArrayList<itemGrid>{
        var arrayList:ArrayList<itemGrid> = ArrayList()
        arrayList.add(itemGrid(R.drawable.register_icon,"Registros"))
        arrayList.add(itemGrid(R.drawable.archivo_icon,"Archivos"))
        arrayList.add(itemGrid(R.drawable.liquidaciones_icon,"Liquidaciones"))
        arrayList.add(itemGrid(R.drawable.sheets_icon,"Opcion2"))
        arrayList.add(itemGrid(R.drawable.sheets_icon,"Opcion3"))
        arrayList.add(itemGrid(R.drawable.settings_icon,"Ajustes"))


        return arrayList
    }

    companion object {
        const val ARG_KEY = ""
        const val ARG_PARAM2 = "param2"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentMenu().apply {
                arguments = Bundle().apply {
                    putString(ARG_KEY, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
package com.example.barberi.UserFrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barberi.BasesDatos.Productos
import com.example.barberi.BasesDatos.ProductosDAO

import androidx.room.*
import com.example.barberi.R
import com.example.barberi.SesionRoom
import kotlinx.android.synthetic.main.fragment_store.view.*


/**


 */


class StoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_store, container, false)

        root.rv_productos.layoutManager = LinearLayoutManager(
            activity!!.applicationContext,
            RecyclerView.VERTICAL,
            false
        )
        root.rv_productos.setHasFixedSize(true)

        var productosDAO:ProductosDAO = SesionRoom.database.productosDao()
        var allProductos: List<Productos> = productosDAO.getProductos()


        var productosRVAdapter = ProductosRVAdapter(
            activity!!.applicationContext,
            allProductos as ArrayList<Productos>
        )

        root.rv_productos.adapter = productosRVAdapter


        return root
    }
}
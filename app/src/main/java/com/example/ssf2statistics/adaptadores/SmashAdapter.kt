package com.example.ssf2statistics.adaptadores

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ssf2statistics.R
import com.example.ssf2statistics.config.Constantes
import com.example.ssf2statistics.databinding.ItemListBinding
import com.example.ssf2statistics.models.Personaje
import com.example.ssf2statistics.ui.FormularioActivity
import com.example.ssf2statistics.viewmodel.FormularioViewModel


class SmashAdapter(private val dataSet: List<Personaje>?) :
    RecyclerView.Adapter<SmashAdapter.ViewHolder>() {


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = dataSet?.get(position)
        viewHolder.enlazarItem(item!!)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet!!.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ItemListBinding.bind(view)
        var contexto = view.context
        fun enlazarItem(p: Personaje){


            //TODO ENLAZAR AQUI
            binding.tvNombre.text = p.nombre
            binding.tvSmashball.text = p.finalsmash
            binding.tvResume.text = p.resume
            binding.tvGame.text = p.juego
            binding.tvFranchise.text = p.saga

            binding.root.setOnClickListener{
                val intent = Intent(contexto,FormularioActivity::class.java)
                intent.putExtra(Constantes.OPERACION_KEY,Constantes.OPERACION_EDITAR)
                intent.putExtra(Constantes.ID_PERSONAJE_KEY,p.id)
                contexto.startActivity(intent)
            }
        }

    }
}

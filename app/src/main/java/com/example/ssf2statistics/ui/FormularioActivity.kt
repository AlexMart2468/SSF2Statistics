package com.example.ssf2statistics.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.ssf2statistics.MainActivity
import com.example.ssf2statistics.config.Constantes
import com.example.ssf2statistics.databinding.ActivityFormularioBinding
import com.example.ssf2statistics.dialogos.BorrarDialogo
import com.example.ssf2statistics.viewmodel.FormularioViewModel

class FormularioActivity : AppCompatActivity(), BorrarDialogo.BorrarListener {
    lateinit var binding: ActivityFormularioBinding
    lateinit var viewModel: FormularioViewModel
    lateinit var dialog :BorrarDialogo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.dialog = BorrarDialogo(this)
        viewModel = ViewModelProvider(this).get()
        viewModel.operacion = intent.getStringExtra(Constantes.OPERACION_KEY)!!
        binding.modelo = viewModel
        binding.lifecycleOwner = this

        viewModel.thisgamewinneris.observe(this, Observer {
            if(it){
                mostrarMensaje("This Game Winner Is")
                irAlInicio()
            }else{
                mostrarMensaje("No Contest")
            }
        })

        if(viewModel.operacion.equals(Constantes.OPERACION_EDITAR)){
            viewModel.id.value = intent.getLongExtra(Constantes.ID_PERSONAJE_KEY,0)
            viewModel.cargarDatos()
            binding.LinearEditar.visibility = View.VISIBLE
            binding.btnGuardar.visibility = View.GONE
        }else {
            binding.LinearEditar.visibility = View.GONE
            binding.btnGuardar.visibility = View.VISIBLE
        }

        binding.btnBorrar.setOnClickListener{
            mostrarDialogo()
        }

    }

    private fun mostrarDialogo() {
        dialog.showNow(supportFragmentManager, "BorrarDialogo")
    }

    private fun mostrarMensaje(s: String){
        Toast.makeText(applicationContext, s, Toast.LENGTH_LONG).show()
    }

    private fun irAlInicio(){
        val intent = Intent(applicationContext,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun borrarPersonaje() {
        viewModel.deletePersonaje()
    }

}
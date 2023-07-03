package com.example.ssf2statistics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ListAdapter
import android.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ssf2statistics.adaptadores.SmashAdapter
import com.example.ssf2statistics.config.Constantes
import com.example.ssf2statistics.databinding.ActivityMainBinding
import com.example.ssf2statistics.models.Personaje
import com.example.ssf2statistics.ui.FormularioActivity
import com.example.ssf2statistics.viewmodel.PersonajeViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SmashAdapter
    private lateinit var viewModel: PersonajeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Super Smash Flash 2"
            setLogo(R.drawable.smash_ball)
        }


        // TODO ENLAZAR EL MODELO CON EL LIVE DATA

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[PersonajeViewModel::class.java]
        binding.lifecycleOwner = this
        binding.modelo = viewModel

        viewModel.listarPersonajes()

        binding.miRecycler.apply {
            val layoutManager = LinearLayoutManager(applicationContext)
            this.layoutManager = layoutManager

        }

        viewModel.listaNotas.observe(this, Observer {
            binding.miRecycler.adapter = SmashAdapter(it)
        })

        binding.btnOpenForm.setOnClickListener{
            val intent = Intent(this,FormularioActivity::class.java)
            intent.putExtra(Constantes.OPERACION_KEY,Constantes.OPERACION_INSERTAR)
            startActivity(intent)
        }

        binding.etBuscar.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().isNotEmpty()){
                    viewModel.buscarPersonaje()
                }
            }
            override fun beforeTextChanged(s0: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }



        })
    }



}